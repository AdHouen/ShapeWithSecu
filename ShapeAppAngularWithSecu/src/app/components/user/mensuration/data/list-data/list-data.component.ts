import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { NotifierService } from 'angular-notifier';
import { Mensuration } from './../../../../../models/mensuration/mensuration';
import { Router, ActivatedRoute } from '@angular/router';
import { MuscleService } from './../../../../../services/muscle/muscle.service';
import { MensurationService } from './../../../../../services/mensuration/mensuration.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Muscle } from './../../../../../models/muscle/muscle';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-data',
  templateUrl: './list-data.component.html',
  styleUrls: ['./list-data.component.css']
})
export class ListDataComponent implements OnInit{

  declare mensurations : any;
  declare muscles : Muscle[];
  public editMensuration = new Mensuration() ;
  closeResult = '';
  declare editForm : FormGroup;


  constructor(

    private mensurationService : MensurationService,
    private muscleService : MuscleService,
    private router : Router,
    private route : ActivatedRoute,
    private formBuilder : FormBuilder,
    private notifier: NotifierService,
    private modalService: NgbModal,
  ){

  }
  ngOnInit(): void {
    this.getMensurations();
    this.getMuscles();

    if (this.route.snapshot.paramMap.get('id') != null) {
      this.removeMensuration();
      this.router.navigate(['/data']);
    }
  }

  getMensurations() {
    this.mensurationService.findAllMensurationsByDate().subscribe(
      data => {
        this.mensurations=data as Mensuration[];

      }
    )
  }

  getMuscles() {
    this.muscleService.getAllMuscles().subscribe(
      data => {
        this.muscles=data as Muscle[];

      }
    )
  }

  // Méthode Delete
  removeMensuration() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.mensurationService.deleteMensuration(id).subscribe(
      () => {

      this.notifier.notify('success', 'Votre mensuration à été supprimé avec succès');

      }
    )
  }



  // Méthode pour modal
  open(content: any, mensuration : Mensuration) {

    this.editMensuration=mensuration;

		this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then(
			(result) => {
				this.closeResult = `Closed with: ${result}`;
			},
			(reason) => {
				this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
			},

		);
    // (document.getElementById("serie") as HTMLInputElement).value = this.editEntrainement.serie.toString();
    // (document.getElementById("serie") as HTMLInputElement).style.borderColor = "red";
    this.chargeValueEdit();

	}

	private getDismissReason(reason: any): string {
		if (reason === ModalDismissReasons.ESC) {
			return 'by pressing ESC';
		} else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
			return 'by clicking on a backdrop';
		} else {
			return `with: ${reason}`;
		}
	}
  //Fin Modal

  // Edit Mensuration
  update(){
    if(this.editForm.valid) {
      this.mensurationService.updateMensuration(this.editForm.value).subscribe(
        () =>{
          this.getMensurations();
          this.notifier.notify('success', 'Votre mensuration à été modifié avec succès');
        }
      )
    }

  }

  chargeValueEdit(){
    this.editForm = this.formBuilder.group({
      mensurationId: [this.editMensuration.mensurationId, Validators.required],
      date: [this.editMensuration.date],
      valeur: [this.editMensuration.valeur],
      muscleId: [this.editMensuration.muscleId],
    })
  }

}
