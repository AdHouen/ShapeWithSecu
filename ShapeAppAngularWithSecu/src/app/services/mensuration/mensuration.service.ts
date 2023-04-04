import { AppSettings } from './../../settings/app.setting';
import { Mensuration } from './../../models/mensuration/mensuration';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MensurationService {

  httpOptions = {
    headers : new HttpHeaders ({'Content-Type' :'application/json'})
  }

  private host = environment.apiUrl;
  constructor(private http: HttpClient) {


  }

   /*On récupère les mensurations du backend via l'URL /mensurations */
   public getAllMensurations() : Observable<Mensuration[]>{
    return this.http.get<Mensuration[]>(`${this.host}/mensurations`)
  }

  /* FormData injecte les données via un form Data voir dans postman (formulaire); il faut une key et une value */
  public addMensuration(formData: FormData) : Observable<Mensuration>{
    return this.http.post<Mensuration>(`${this.host}/mensurations`, formData)
  }

  /* I delete une mensuration */
  public deleteMensuration(mensurationId: number) : Observable<Mensuration>{
    return this.http.delete<Mensuration>(`${this.host}/mensurations/${mensurationId}`);
  }

  public findAllMensurationsByDate(){
    return this.http.get<Mensuration[]>(`${this.host}/mensurations/date`)
  }

  // Editer
  editMensuration(mensurationId:number) {
    return this.http.get(AppSettings.APP_URL+'/mensurations/'+mensurationId)

  }

  updateMensuration(mensuration:Mensuration){
    return this.http.put(AppSettings.APP_URL+'/mensurations/'+ mensuration.mensurationId, JSON.stringify(mensuration),this.httpOptions);
  }
}
