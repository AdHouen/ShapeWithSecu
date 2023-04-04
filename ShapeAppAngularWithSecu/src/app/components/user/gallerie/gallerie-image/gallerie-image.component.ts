import { GalerieService } from './../../../../services/galerie/galerie.service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-gallerie-image',
  templateUrl: './gallerie-image.component.html',
  styleUrls: ['./gallerie-image.component.css']
})
export class GallerieImageComponent implements OnInit{

  @Input()
  declare photo:any;

  @Output()
  delPic:EventEmitter<number>=new EventEmitter<number>();


  constructor(
    private galerieService : GalerieService
  ){

  }

  ngOnInit(): void {




  }

  deletePhoto() :void {
    console.log(" Fonction galerie-image Del : "+this.photo.galerieId);
    this.galerieService.deleteGalerie(this.photo.galerieId).subscribe(
      () => {

        this.delPic.emit(this.photo.galerieId);
      }
    );


  }

}
