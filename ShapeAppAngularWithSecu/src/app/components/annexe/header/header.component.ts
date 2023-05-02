import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private notifier:NotifierService,
  ){

  }
  onLogout() {
    this.authenticationService.logOut();
    localStorage.clear();
    this.router.navigate(['/login']);
    this.notifier.notify('success', `Vous vous êtes bien déconnecté avec succes`);

  }

}
