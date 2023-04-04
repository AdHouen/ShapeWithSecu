import { HttpErrorResponse } from '@angular/common/http';
import { User } from './../../../models/user/user';
import { AuthenticationService } from './../../../services/authentication/authentication.service';
import { Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy {

  public showLoading: boolean = false;
  private subscriptions: Subscription [] = [];

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private notifier: NotifierService,
    )
    { }

    ngOnInit(): void {
      if(this.authenticationService.isUserLoggedIn()) {
        this.router.navigateByUrl('/user/management');
        console.log("Ca fonctionne login");
      }



    }
    ngOnDestroy(): void {

    }
    public onRegister(user: User): void{
      this.showLoading = true;
      console.log(user);

      this.subscriptions.push(this.authenticationService.register(user).subscribe(
        (response: User)=>  {

          console.log("Je suis dans Register", response.firstname);

          this.showLoading = false;
          this.notifier.notify('success',`Votre compte a bien été crée ${response.username}`);

        },
        (errorResponse: HttpErrorResponse) => {
            console.log(errorResponse);
            this.notifier.notify('error','Identifiant ou Email déjà utilisé');
            this.showLoading = false;
        }
        )
      )

    }

}
