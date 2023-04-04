import { Galerie } from './../../models/gallerie/galerie/galerie';

import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GalerieService {

  private host = environment.apiUrl;
  constructor(private http: HttpClient) {

  }

   /*On récupère la galerie du backend via l'URL /muscles */
   public getAllGaleries() : Observable<Galerie[]>{
    return this.http.get<Galerie[]>(`${this.host}/galeries`)
  }

  /* FormData injecte les données via un form Data voir dans postman (formulaire); il faut une key et une value */
  public addGalerie(formData: FormData) : Observable<Galerie>{
    return this.http.post<Galerie>(`${this.host}/galeries`, formData)
  }

  /* FormData inject les donées a modifier via un formulaire (voir dans PostMan section FormData pour les tests) Attention il est possible de rencontrer une erreur plusieurs champs sont required pour le update() */
  public updateGalerie(formData: FormData) : Observable<Galerie>{
    return this.http.post<Galerie>(`${this.host}/galeries`, formData)
  }

  /* I delete une photo de la galerie */
  public deleteGalerie(galerieId: number) : Observable<Galerie>{
    console.log("log delGalerie : "+galerieId);

    return this.http.delete<Galerie>(`${this.host}/galeries/${galerieId}`);
  }
  public findAllPhotosByDate(){
    return this.http.get<Galerie[]>(`${this.host}/galeries/date`)
  }
}
