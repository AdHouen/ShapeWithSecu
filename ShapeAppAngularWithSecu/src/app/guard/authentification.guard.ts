import { NotifierService } from 'angular-notifier';
import { AuthenticationService } from './../services/authentication/authentication.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {

  constructor(private AuthenticationService:AuthenticationService,
    private router:Router,
    private notifier:NotifierService){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean{
    return this.isUserLoggedIn();
  }

  private isUserLoggedIn():boolean{
    if(this.AuthenticationService.isUserLoggedIn()){
      return true;
    }
    this.router.navigate(['/login']);
    //TODO send notification to user
    this.notifier.notify('error', 'Vous devez vous logger pour accèder à cette page');
    return false;


  }

}

