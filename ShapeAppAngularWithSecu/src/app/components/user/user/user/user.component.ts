import { Router } from '@angular/router';

import { HttpErrorResponse, HttpEvent } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, Subscription } from 'rxjs';
import { User } from 'src/app/models/user/user';
import { NgForm } from '@angular/forms';
import { AppSettings } from 'src/app/settings/app.setting';
import { FileUploadStatus } from 'src/app/models/file-upload-status/file-upload.status';
import { UserService } from 'src/app/services/user/user.service';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { NotifierService } from 'angular-notifier';
import { Role } from 'src/app/enum/role.enum';
import { CustomHttpResponse } from 'src/app/models/custom-http-response/custom-http-response';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  urlPict = AppSettings.IMG_PROFIL;

  private titleSubject = new BehaviorSubject<string>('Users');
  public titleAction$ = this.titleSubject.asObservable();
  declare public refreshing: boolean;
  declare public users : User[];
  private subscription : Subscription[]=[];
  declare public selectedUser: User | null;
  declare public fileName: string;
  declare public profileImage: File;
  public editUser = new User() ;
  declare public currentUsername: string;
  declare public user : User;
  public fileStatus = new FileUploadStatus();

  constructor(
    private userService : UserService,
    private authenticationService:AuthenticationService,
    private notifier : NotifierService,
    private router : Router
    ) {

  }
  ngOnInit(): void {
    this.user = this.authenticationService.getUserFromLocalCache();
    this.getUsers(true);
  }

  public changeTitle(title : string) : void {
    this.titleSubject.next(title);
  }

  // Méthode pour récupérole le rôle et les authorisations
  public get isAdmin():boolean{
    return this.getUserRole() === Role.ADMIN || this.getUserRole() === Role.SUPER_ADMIN;
  }
  public get isManager():boolean{
    return this.isAdmin || this.getUserRole() === Role.MANAGER
  }
  public getUserRole(): string {
    return this.authenticationService.getUserFromLocalCache().role;
  }
  public get isAdminManager():boolean{
    return this.isAdmin || this.isManager;
  }

  // Méthode pour récupérer les Users et les mettrent dans le cache
  public getUsers(showNotification : boolean) : void {
    this.refreshing = true;
    this.subscription.push(
      this.userService.getUsers().subscribe(
        (data : User[]) => {
          this.userService.addUsersToLocalCache(data); // Ajouter la liste des utilisateurs au local storage
          this.users = data;
          this.refreshing = false;
          if (showNotification) {
            this.notifier.notify('success', `${data.length} Utilisateur(s) chargés avec succés`);

          }
        },
        (errorResponse: HttpErrorResponse) => {
          this.notifier.notify('error', `Une erreur est survenue`);
          this.refreshing = false;
      }

      )
    );
  }

  // Button qui permet de voir les infos de l'utilisateur
  public onSelectUser(selectedUser :User) : void {
    this.selectedUser = selectedUser;
    this.clickButton('openUserInfo');

  }
  // Méthode pour récupérer le nom et l'image
  // public onProfileImageChange(fileName : string, profileImage : File ):void{
  //   // console.log(fileName, profileImage);
  //   this.fileName = fileName;
  //   this.profileImage = profileImage;

  // }

  // Méthode pour récupérer le nom et l'image
  public onProfileImageChange(event:any):void{


    const files : File[] = event.target.files;
    let file: File = event.target.files[event.target.files.length-1] as File;

    this.fileName = file.name;
    this.profileImage = file;
    console.log(this.fileName);

  }


  //Méthode pour update la photo de profile
  public updateProfileImage() : void {
    this.clickButton('profile-image-input');


  }
  public onUpdateProfileImage() : void {
    const formData = new FormData();
    formData.append('username', this.user.username);
    formData.append('profileImage', this.profileImage);
    this.subscription.push(
      this.userService.updateProfileImage(formData).subscribe(
        (event : HttpEvent<any>) => {
          // this.reportUploadProgress(event);
          this.notifier.notify('success', "Image changée avec succes");


        },
        (errorResponse: HttpErrorResponse) => {
          this.notifier.notify('error', `Une erreur est survenue`);
          this.fileStatus.status = 'done';

      }

      )
    );
  }




  reportUploadProgress(event: HttpEvent<any>) {
    throw new Error('Method not implemented.');
  }

  // Méthode MAJ du current User
  onUpdateCurrentUser(_t140: NgForm) {
    throw new Error('Method not implemented.');
  }


  // Méthode pour ajouter un nouvel utilisateur
  public onAddNewUser(userForm:NgForm):void{
   const formData = this.userService.createUserFormData(null as any,userForm.value,this.profileImage);
   console.log("Avant push : "+userForm);

   this.subscription.push(
    this.userService.addUser(formData).subscribe(
      (data : User) => {
        console.log("Apres push : "+data);
        this.clickButton('new-user-close');
        this.getUsers(false);
        this.fileName = null as any;
        this.profileImage = null as any;
        userForm.reset();
        this.notifier.notify('success', `${data.firstname} ${data.lastname} a été ajouté avec succes`);

      },
      (errorResponse: HttpErrorResponse) => {
        this.notifier.notify('error', `Une erreur est survenue`);
        this.profileImage= null as any;
    }
    )
   )
  }
// Button qui permet de sauvgarder
  saveNewUser() {
    this.clickButton('new-user-save');
    }

// Méthode qui permet de rechercher parmis les utilisateurs
  public searchUsers(searchTerm:string): void {
    // console.log(searchTerm);

    const results : User[] = [];
    for(const user of this.userService.getUsersFromLocalCache()){
      if (user.firstname.toLocaleLowerCase().indexOf(searchTerm.toLowerCase())!== -1 ||
          user.lastname.toLocaleLowerCase().indexOf(searchTerm.toLowerCase())!== -1 ||
          user.username.toLocaleLowerCase().indexOf(searchTerm.toLowerCase())!== -1 ||
          user.userId.toLocaleLowerCase().indexOf(searchTerm.toLowerCase())!== -1
      ) {
        results.push(user);
      }
    }
    this.users  = results;
    if (results.length === 0 || !searchTerm) {
      this.users = this.userService.getUsersFromLocalCache()
    }

  }

  // Méthode pour éditer un utilisateur
  public onEditUser(editUser : User) {
    this.editUser = editUser;
    this.currentUsername =editUser.username;
    this.clickButton('openUserEdit');
  }

  // Méthode pour sauvegarder l'édit  d'un utilisateur
  public onUpdateUser(): void {
    const formData = this.userService.createUserFormData(this.currentUsername,this.editUser,this.profileImage);
   this.subscription.push(
    this.userService.updateUser(formData).subscribe(
      (data : User) => {
        this.clickButton('closeEditUserModalButton');
        this.getUsers(false);
        this.fileName = null as any;
        this.profileImage = null as any;
        this.notifier.notify('success', `${data.firstname} ${data.lastname} a été édité avec succes`);

      },
      (errorResponse: HttpErrorResponse) => {
        this.notifier.notify('error', `Une erreur est survenue`);
        this.profileImage= null as any;
      }
    )
   )
  }

  // Méthode pour delete un User
  public onDeleteUser(userId : number): void {
    this.subscription.push(
      this.userService.deleteUser(userId).subscribe(
        (data :CustomHttpResponse) => {
          this.notifier.notify('success', `L'utilisateur a bien été supprimé avec succes`);
          this.getUsers(true);
        },
        (errorResponse: HttpErrorResponse) => {
          this.notifier.notify('error', `Une erreur est survenue`);
        }
      )
    )

  }

  // Méthode pour se déconecter
  onLogout() {
    this.authenticationService.logOut();
    this.router.navigate(['/login']);
    this.notifier.notify('success', `Vous vous êtes bien déconnecté avec succes`);

  }

  // Méthode pour reset le password
  public onResetPassword(emailForm:NgForm): void {
    this.refreshing = true;
    const emailAdresse = emailForm.value['reset-password-email'];
    this.subscription.push(
      this.userService.resetPassword(emailAdresse).subscribe(
        (data :CustomHttpResponse) => {
          this.notifier.notify('success', `Le password a bien été réinitialisé`);
          this.refreshing = false;
        },
        (errorResponse: HttpErrorResponse) => {
          this.notifier.notify('error', `Une erreur est survenue`);
          this.refreshing = false;
        },
        () => emailForm.reset()
      )

    );

  }

  // Méthode générale pour le click des boutons
  private clickButton(buttonId : string): void{
    document.getElementById(buttonId)?.click();
  }

  // Méthode pour les notifications
  // private sendNotification(notificationType: NotificationType, message: string) {
  //   if(message){
  //     this.notificationService.notify(notificationType, message);
  //   } else {
  //     this.notificationService.notify(notificationType, 'Une erreur est survenue. Veuillez réessayer.');
  //   }
  // }






}
