import { AppSettings } from './../../settings/app.setting';
import { Muscle } from './../../models/muscle/muscle';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MuscleService {

  private host = environment.apiUrl;
  constructor(private http: HttpClient) {


  }

   /*On récupère les muscles du backend via l'URL /muscles */
   public getAllMuscles() : Observable<Muscle[]>{
    return this.http.get<Muscle[]>(`${this.host}/muscles`)
  }

  /* FormData injecte les données via un form Data voir dans postman (formulaire); il faut une key et une value */
  public addMuscle(formData: FormData) : Observable<Muscle>{
    return this.http.post<Muscle>(`${this.host}/muscles`, formData)
  }

  /* FormData inject les donées a modifier via un formulaire (voir dans PostMan section FormData pour les tests) Attention il est possible de rencontrer une erreur plusieurs champs sont required pour le update() */
  public updateMuscle(formData: FormData) : Observable<Muscle>{
    return this.http.post<Muscle>(`${this.host}/muscles`, formData)
  }

  /* I delete un muscle */
  public deleteMuscle(muscleId: number) : Observable< Muscle >{
    return this.http.delete<Muscle>(`${this.host}/delete/${muscleId}`);
  }
  // httpOptions = {
  //   headers : new HttpHeaders ({'Content-Type' :'application/json'})
  // }

  // constructor(private http:HttpClient) { }

  // findAllMuscles(){
  //   return this.http.get(AppSettings.APP_URL+'/muscles')
  // }

  // saveMuscle(muscle:Muscle){
  //   return this.http.post(AppSettings.APP_URL+'/muscles', JSON.stringify(muscle),this.httpOptions);

  // }

  // editMuscle(muscleId:number) {
  //   return this.http.get(AppSettings.APP_URL+'/muscles/'+muscleId)

  // }

  // updateMuscle(muscle:Muscle){
  //   return this.http.put(AppSettings.APP_URL+'/muscles/'+ muscle.muscleId, JSON.stringify(muscle),this.httpOptions);
  // }

  // deleteMuscle(muscleId : number){
  //   return this.http.delete(AppSettings.APP_URL+'/muscles/'+muscleId)

  // }

}
