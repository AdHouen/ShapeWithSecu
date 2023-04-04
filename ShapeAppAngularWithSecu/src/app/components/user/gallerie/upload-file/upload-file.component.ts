import { GalerieService } from './../../../../services/galerie/galerie.service';
import { Router } from '@angular/router';
import { FileUploadServiceService } from './../../../../services/fileUploadService/file-upload-service.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit{

  file!: File ; // valeur defini qui ne peut pas etre nul

  fileDetails!: any;
  fileUris: Array<string> = []; // stock les urls des images
  declare form : FormGroup;
  galerie : any [][] = [];

  constructor(
    private fileUploadService: FileUploadServiceService,
    private router: Router,
    private formBuilder : FormBuilder,
    private galerieService : GalerieService
    ) {

    }

    ngOnInit(): void {
      this.form = this.formBuilder.group({
        galerieId:  ['', Validators.required],
        galeriefilename:  ['', Validators.required],
        date:[new Date(), Validators.required],
        uid :  ['', Validators.required],

      })
      this.getGaleries();

    }

    selectFile(event: any) {
      this.file = event.target.files.item(0);
    }

    uploadFile() {
      this.form.value.galeriefilename =  this.file.name
      this.fileUploadService.upload(this.form.value).subscribe({
        next: (data) => {
          console.log("Data : "+data);
          console.log("File : "+this.file);


        },
        error: (e) => {
          console.log(e);
        }
      });
      this.fileUploadService.upload2(this.file).subscribe({
        next: (data) => {
          this.fileDetails = data;
          this.fileUris.push(this.fileDetails.fileUri);
          alert("File Uploaded Successfully")
          location.reload();
        },
        error: (e) => {
          console.log(e);
        }
      })

    }
    getGaleries() {
      return this.galerieService.getAllGaleries().subscribe(
        (data=>{
          let dateTemp= "";
          let i :number = -1;
          console.log(data as any[]);

          (data as any[]).forEach((element) => {
            if (element.date != dateTemp) {
              i++;
              dateTemp = element.date;
              this.galerie[i]=[];
            }
            this.galerie[i].push(element);
          });
          // this.galerie = data as any [];
          console.log(this.galerie);
        }
          )

      )
    }

    delPhoto(id:number){

     window.location.reload();


    }

}
