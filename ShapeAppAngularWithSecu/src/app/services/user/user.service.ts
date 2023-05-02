import { HttpClient, HttpErrorResponse, HttpResponse, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/user/user';
import { environment } from 'src/environments/environment';
import { JwtHelperService } from "@auth0/angular-jwt";
import { CustomHttpResponse } from 'src/app/models/custom-http-response/custom-http-response';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private host = environment.apiUrl;
  constructor(private http: HttpClient) {


  }

  /*On récupère les utilisateurs du backend via l'URL /user/list */
  public getUsers() : Observable<User[]>{
    return this.http.get<User[]>(`${this.host}/user/list`)
  }

  /* FormData injecte les données via un form Data voir dans postman (formulaire); il faut une key et une value */
  public addUser(formData: FormData) : Observable<User>{
    console.log("je suis dans le service : "+formData);
    return this.http.post<User>(`${this.host}/user/add`, formData)
  }

    /* FormData inject les donées a modifier via un formulaire (voir dans PostMan section FormData pour les tests) Attention il est possible de rencontrer une erreur plusieurs champs sont required pour le update() */
    public updateUser(formData: FormData) : Observable<User>{
      return this.http.post<User>(`${this.host}/user/update`, formData)
    }

    /* I reset the password here */
    public resetPassword(email: string) : Observable< CustomHttpResponse >{
      return this.http.get<CustomHttpResponse>(`${this.host}/resetPassword/${email}`)
    }

    /* I update my profil image here */
    public updateProfileImage(formData: FormData) : Observable< HttpEvent <User> >{
      return this.http.post<User>(`${this.host}/updateProfileImage/`, formData, {
        reportProgress : true,
        observe : 'events'

      });
    }

    /* I delete the user */
    public deleteUser(userId: number) : Observable< CustomHttpResponse >{
      return this.http.delete<CustomHttpResponse>(`${this.host}/delete/${userId}`);
    }

    /* I add users in cache */
    public addUsersToLocalCache(users: User[]) : void {
      localStorage.setItem('users', JSON.stringify(users));
    }

       /* Si je vais dans mon local storage et que je recupere un item de users sinon je retourne un tableau vide --   if (localStorage.getItem('users')!=null) */
      public getUsersFromLocalCache() : User[] {

        if (localStorage.getItem('users')) {
          return JSON.parse(localStorage.getItem('users')!); /* || '{}' */

        }
        else {
          return [];
        }
      }


       /* Si je vais dans mon local storage et que je recupere un item de users sinon je retourne un tableau vide --   if (localStorage.getItem('users')!=null) */
      public createUserFormData(loggedInUsername: string, user: User, profileImage: File) : FormData {

        const formData = new FormData();
        formData.append('currentUsername', loggedInUsername);
        // formData.append('currentUsername', user.currentUsername);

        formData.append('firstname', user.firstname);
        formData.append('lastname', user.lastname);
        formData.append('username', user.username);
        formData.append('email', user.email);
        formData.append('password', user.password);
        formData.append('role', user.role);
        formData.append('profileImageURL', profileImage);
        formData.append('isActive', JSON.stringify(user.active));
        formData.append('isNonLocked', JSON.stringify(user.notLocked));

        return formData;
      }



}
