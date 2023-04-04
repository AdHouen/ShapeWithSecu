import { NotifierService } from 'angular-notifier';
import { Router, ActivatedRoute } from '@angular/router';
import { MuscleService } from './../../../services/muscle/muscle.service';
import { ExerciceService } from './../../../services/exercice/exercice.service';
import { Muscle } from './../../../models/muscle/muscle';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-exercice',
  templateUrl: './exercice.component.html',
  styleUrls: ['./exercice.component.css']
})
export class ExerciceComponent implements OnInit {



  declare exercices : any;
  declare muscles:Muscle[];

  constructor(
    private exerciceService : ExerciceService,
    private muscleService : MuscleService,
    private router : Router,
    private route : ActivatedRoute,
    private notifier: NotifierService,




  ){


  }


  ngOnInit(): void {
    this.getExercices();
    this.getMuscles();
    this.notifier.notify('success', 'Chargement des exercices avec succes');





  }

  getMuscles() {
    this.muscleService.getAllMuscles().subscribe(
      data => {
        console.log(data);
        this.muscles=data as Muscle[];

      }
    )
  }
  getExercices() {
    this.exerciceService.getAllExercices().subscribe(
      data => {
        console.log(data);
        this.exercices=data;


      }
    )
  }

}
