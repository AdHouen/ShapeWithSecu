import { FileDetails } from './../../models/gallerie/fileDetails/file-details';
import { Observable } from 'rxjs';
import { AppSettings } from './../../settings/app.setting';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FileUploadServiceService {

  private baseUrl = "http://localhost:8090"
  httpOptions = {
    headers : new HttpHeaders ({'Content-Type' :'application/json'})
  }

  constructor(private http: HttpClient) { }

  upload(file: File) {

    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.post(AppSettings.APP_URL+'/galeries', JSON.stringify(file),this.httpOptions);
  }



  upload2(file: File): Observable<FileDetails> {

    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.post<FileDetails>(`${this.baseUrl}/simple-form-upload-mvc`, formData);
  }
}

