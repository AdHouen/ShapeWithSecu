export class Muscle {

  public muscleId: number;
	public nom: string;
	public uid: number;
	public listMensuration: [];
	public listExercice: [];

  constructor(){
    this.muscleId= 0;
	  this.nom= "";
	  this.uid= 0;
	  this.listMensuration= [];
	  this.listExercice= [];
  }
}
