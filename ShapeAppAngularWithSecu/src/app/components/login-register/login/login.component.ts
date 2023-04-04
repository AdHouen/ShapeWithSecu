import { NotifierService } from 'angular-notifier';
import { HeaderType } from './../../../enum/header-type.enum';
import { User } from './../../../models/user/user';
import { AuthenticationService } from './../../../services/authentication/authentication.service';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit,OnDestroy {
  public showLoading: boolean = false;
  private subscriptions: Subscription [] = [];


  constructor(
    private router: Router,
    private AuthenticationService: AuthenticationService,
    private notifier :NotifierService) { }

  ngOnInit(): void {
    if(this.AuthenticationService.isUserLoggedIn()) {
      this.router.navigateByUrl('/user/management');
      this.notifier.notify('success', 'Vous êtes connecté');
    } else {
      this.router.navigateByUrl('/login');
      this.notifier.notify('success', 'Bienvenue sur Shape');

    }

  }

  public onLogin(user: User): void{
    this.showLoading = true;
    // console.log(user);

    this.subscriptions.push(this.AuthenticationService.login(user).subscribe(
      (response: HttpResponse<User>)=>  {
       const token = response.headers.get(HeaderType.JWT_TOKEN);
       this.AuthenticationService.saveToken(token!);
       this.AuthenticationService.addUserToLocalCache(response.body!);
       this.router.navigateByUrl('/accueil');
       this.notifier.notify('success', 'Vous êtes connecté');
       this.showLoading = false;

      },
      (errorResponse: HttpErrorResponse) => {
          console.log(errorResponse);
          this.notifier.notify('error', 'Mot de passe ou Identifiant incorect. Veuillez réessayer.');
          this.showLoading = false;
      }
      )
    )

  }


  ngOnDestroy(): void {
    //cleanup logic goes there
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }
}
