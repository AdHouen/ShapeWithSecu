import { AppSettings } from './../../settings/app.setting';
import { Exercice } from './../../models/exercice/exercice';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ExerciceService {

  private host = environment.apiUrl;
  constructor(private http: HttpClient) {


  }

   /*On récupère les muscles du backend via l'URL /muscles */
   public getAllExercices() : Observable<Exercice[]>{
    return this.http.get<Exercice[]>(`${this.host}/exercices`)
  }

  /* FormData injecte les données via un form Data voir dans postman (formulaire); il faut une key et une value */
  public addExercice(formData: FormData) : Observable<Exercice>{
    return this.http.post<Exercice>(`${this.host}/exercices`, formData)
  }

  /* FormData inject les donées a modifier via un formulaire (voir dans PostMan section FormData pour les tests) Attention il est possible de rencontrer une erreur plusieurs champs sont required pour le update() */
  public updateExercice(formData: FormData) : Observable<Exercice>{
    return this.http.post<Exercice>(`${this.host}/exercices`, formData)
  }

  /* I delete un muscle */
  public deleteExercice(muscleId: number) : Observable< Exercice >{
    return this.http.delete<Exercice>(`${this.host}/exercices/${muscleId}`);
  }

  // httpOptions = {
  //   headers : new HttpHeaders ({'Content-Type' :'application/json'})
  // }

  // constructor(private http:HttpClient) { }

  // findAllExercices(){
  //   return this.http.get(AppSettings.APP_URL+'/exercices')
  // }

  // saveExercice(exercice:Exercice){
  //   return this.http.post(AppSettings.APP_URL+'/exercices', JSON.stringify(exercice),this.httpOptions);

  // }

  // editExercice(exerciceId:number) {
  //   return this.http.get(AppSettings.APP_URL+'/exercices/'+exerciceId)

  // }

  // updateExercice(exercice:Exercice){
  //   return this.http.put(AppSettings.APP_URL+'/exercices/'+ exercice.exerciceId, JSON.stringify(exercice),this.httpOptions);
  // }

  // deleteExercice(exerciceId : number){
  //   return this.http.delete(AppSettings.APP_URL+'/exercices/'+exerciceId)

  // }
}
