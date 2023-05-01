import { MuscleService } from './../../../../../services/muscle/muscle.service';
import { MensurationService } from './../../../../../services/mensuration/mensuration.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-add-data',
  templateUrl: './add-data.component.html',
  styleUrls: ['./add-data.component.css']
})
export class AddDataComponent implements OnInit{
  declare form : FormGroup;
  declare muscles :any [];

  constructor(
    private mensurationService : MensurationService,
    private muscleService : MuscleService,
    private router : Router,
    private formBuilder : FormBuilder,
    private notifier: NotifierService
  ){

  }
  ngOnInit(): void {
    this.form = this.formBuilder.group({

      mensurationId: ['', Validators.required],
	    date: ['', Validators.required],
	    valeur: ['', Validators.required],
	    muscleId: ['', Validators.required],

    })
    this.muscleService.getAllMuscles().subscribe(
      data => {
        this.muscles = data;

      }
    );

}
create(){
  console.log(this.form.value);

  this.mensurationService.addMensuration(this.form.value).subscribe(

    ()=> {
      this.router.navigate(['/data'])
      this.notifier.notify('success', 'Votre mensuration à été ajouté avec succès');
    }
  )

}
}
