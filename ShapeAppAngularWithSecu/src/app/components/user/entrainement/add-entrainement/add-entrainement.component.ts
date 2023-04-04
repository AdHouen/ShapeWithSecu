import { NotifierService } from 'angular-notifier';
import { MuscleService } from './../../../../services/muscle/muscle.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ExerciceService } from './../../../../services/exercice/exercice.service';
import { EntrainementService } from './../../../../services/entrainement/entrainement.service';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-add-entrainement',
  templateUrl: './add-entrainement.component.html',
  styleUrls: ['./add-entrainement.component.css']
})
export class AddEntrainementComponent implements OnInit{

  declare form : FormGroup;

  declare exercices :any;

  declare muscles :any [];

  typeMuscle:number = 0;


  constructor(
    private entrainementService: EntrainementService,
    private exerciceService:ExerciceService,
    private muscleService : MuscleService,
    private router : Router,
    private formBuilder: FormBuilder,
    private notifier: NotifierService,
  ){

  }
  ngOnInit(): void {
    this.form = this.formBuilder.group({
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

    this.exerciceService.getAllExercices().subscribe(
      data => {
        // console.log(data);
        this.exercices = data;

      }
    );

    this.muscleService.getAllMuscles().subscribe(
      data => {
        // console.log(data);
        this.muscles = data;

      }
    );


  }
  create(){
    console.log(this.form.value);
    this.entrainementService.addEntrainement(this.form.value).subscribe(
      ()=> {
        this.router.navigate(['/entrainement'])
        this.notifier.notify('success', 'Votre entrainement à été ajouté avec succès');

      }
    )

  }

  afficherExo(muscleId:number){
    this.typeMuscle=muscleId;
  }




}

