import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { User } from './../../models/user/user';
import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';
import { JwtHelperService } from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  public host = environment.apiUrl;
  declare private token: string;
  declare private loggedInUsername: string;
  private jwtHelper = new JwtHelperService();
  declare private tok : string;

  constructor(
private http:HttpClient,
  ) {

    /*this.token='';
    this.loggedInUsername='';*/
  }

  public login(user: User) : Observable<HttpResponse<User>>{
    return this.http.post<User>(`${this.host}/user/login`, user, {observe: 'response'});

  }

  public register(user: User) : Observable<User>{
    return this.http.post<User>(`${this.host}/user/register`, user);

  }

  public logOut() : void {
    this.token = '';
    this.loggedInUsername= '';
    this.tok ='';
    localStorage.removeItem('user');
    localStorage.removeItem('token');
    localStorage.removeItem('users');


  }

  public saveToken(token: string): void {
    this.token = token;
    localStorage.setItem('token', token);
  }


  public addUserToLocalCache(user: User): void {
    localStorage.setItem('user', JSON.stringify(user));
  }


  public getUserFromLocalCache() : User {
    return JSON.parse(localStorage.getItem('user')!); /* || '{}' */
  }


  /* Récupère le token  dans le local storage */
  public loadToken() : void {
    this.token != localStorage.getItem('token'); /* || '{}' */
  }

  /* Récupère le TOKEN de la méthode loadToken() pour pouvoir l'utiliser */
  public getToken() : string {
    return this.token;
  }

  /* Vérifie si l'utilisateur est connecté  */
  public isUserLoggedIn() : boolean {
    // this.loadToken();
    const tok =this.token;
    //  const tok = this.loadToken();
    // console.log("Résultat de isLoggedIn() en ajoutant la réponse de loadToken() dans une variable : [ " + tok + " ]  Authentication > isLoggedIn() - Voir l'autre méthode en commentaire sous la méthode en cas d'erreur");

    if(tok!=null && tok !=='') {
      if(this.jwtHelper.decodeToken(tok).sub != null || '') {
        if(!this.jwtHelper.isTokenExpired(tok)) {
          this.loggedInUsername = this.jwtHelper.decodeToken(tok).sub;
          console.log("Résultat this.loogedInUSername (decoded token) : [ " + this.loggedInUsername + " ] Authentication > isLoggedIn()");
          return true;
        }
      }
    } else {
      this.logOut();
      return false ;
    }
    return false;
  }

}
