import { Router } from '@angular/router';
import { GalerieService } from './../../../../services/galerie/galerie.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-gallerie',
  templateUrl: './list-gallerie.component.html',
  styleUrls: ['./list-gallerie.component.css']
})
export class ListGallerieComponent implements OnInit {
  declare galerie : any [];
  declare form: FormGroup;


  constructor(
    private galerieService : GalerieService,
    private router : Router,
    private formBuilder : FormBuilder
  ){
  }
  ngOnInit(): void {
    this.form = this.formBuilder.group({
      gallerieId:  ['', Validators.required],
	    galleriefilename:  ['', Validators.required],
	    date:  ['', Validators.required],
	    uid :  [''],

    })
    this.getGalleries();

  }
  getGalleries() {
    return this.galerieService.findAllPhotosByDate().subscribe(
      (data=>{

        this.galerie = data as any [];



      }
        )

    )
  }


}
