import { Router, ActivatedRoute } from '@angular/router';
import { EntrainementService } from './../../../../services/entrainement/entrainement.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Entrainement } from './../../../../models/entrainement/entrainement';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-entrainement',
  templateUrl: './edit-entrainement.component.html',
  styleUrls: ['./edit-entrainement.component.css']
})
export class EditEntrainementComponent implements OnInit{
  declare editForm : FormGroup;
  declare entrainement : Entrainement;

  constructor(
    private entrainementService : EntrainementService,
    private router : Router,
    private route : ActivatedRoute,
    private formBuilder : FormBuilder,

  ){

  }



  ngOnInit(): void {
    this.editForm = this.formBuilder.group({
      entrainementId: ['', Validators.required],
      jour: ['', Validators.required],
      muscle: [''],
      exercice: ['', Validators.required],
      serie: [''],
      repetition: [''],
      poids: [''],
      recup: [''],
      temps: [''],
      distance: [''],
      uid: [''],
    })
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.entrainementService.editEntrainement(id).subscribe(
      data => {
        this.editForm.setValue(data);
      }
    )

  }
  // Edit Entrainement
  update(){

    if(this.editForm.valid) {

      this.entrainementService.updateEntrainement2(this.editForm.value).subscribe(
        () =>{
          this.router.navigate(['/entrainement'])
        }
      )
    }
  }

}
