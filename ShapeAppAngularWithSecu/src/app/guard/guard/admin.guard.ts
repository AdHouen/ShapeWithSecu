import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(
    private router : Router,
    private notifier: NotifierService,

  )
  {

  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if(localStorage.getItem('secretAuth') == "shape@a@&3ea^$aid%53(^pp_l-98(-!&!smg#b+ha644bnsb_w7o*rshape"){
        this.notifier.notify('success', "Bienvenue sur Shape en tant que ADMIN");
        return true
      } else{
        this.router.navigate(['/contact']);
        this.notifier.notify('error', "Cette page est réservée aux admins");
        this.notifier.notify('warning', "Si tu veux me contacter, tu peux m'envoyer un message");

        return false
      }
  }



}
