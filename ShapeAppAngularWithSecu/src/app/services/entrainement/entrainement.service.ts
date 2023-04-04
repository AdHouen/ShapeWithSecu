import { AppSettings } from './../../settings/app.setting';
import { Observable } from 'rxjs';
import { Entrainement } from './../../models/entrainement/entrainement';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EntrainementService {

  httpOptions = {
    headers : new HttpHeaders ({'Content-Type' :'application/json'})
  }

  private host = environment.apiUrl;
  constructor(private http: HttpClient) {
  }

   /*On récupère les entrainements du backend via l'URL /entrainements */
   public getAllEntrainements() : Observable<Entrainement[]>{
    return this.http.get<Entrainement[]>(`${this.host}/entrainements`)
  }

  /* FormData injecte les données via un form Data voir dans postman (formulaire); il faut une key et une value */
  public addEntrainement(formData: FormData) : Observable<Entrainement>{
    return this.http.post<Entrainement>(`${this.host}/entrainements`, formData)
  }

  /* FormData inject les donées a modifier via un formulaire (voir dans PostMan section FormData pour les tests) Attention il est possible de rencontrer une erreur plusieurs champs sont required pour le update() */
  public updateEntrainement(formData: FormData) : Observable<Entrainement>{
    return this.http.post<Entrainement>(`${this.host}/entrainements`, formData)
  }

  /* I delete un entrainement */
  public deleteEntrainement(entrainementId: number) : Observable< Entrainement >{
    return this.http.delete<Entrainement>(`${this.host}/entrainements/${entrainementId}`);
  }

  public editEntrainement(id:number) {
    return this.http.get(AppSettings.APP_URL+'/entrainements/'+id)

  }
  public updateEntrainement2(entrainement:Entrainement){
    return this.http.put(AppSettings.APP_URL+'/entrainements/'+ entrainement.entrainementId, JSON.stringify(entrainement),this.httpOptions);
  }

}
