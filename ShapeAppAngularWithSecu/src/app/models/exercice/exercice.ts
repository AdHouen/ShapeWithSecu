export class Exercice {

  public exerciceId: number;
	public nom: string;
	public photo: string;
	public position: string;
	public description: string;
	public muscleId: number;
  public listEntrainement:[];
  constructor(){
    this.exerciceId= 0;
    this.nom= "";
    this.photo= "";
    this.position= "";
    this.description= "";
    this.muscleId= 0;
    this.listEntrainement=[];
  }
}
