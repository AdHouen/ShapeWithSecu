export class Mensuration {

  public mensurationId: number;
	public date: Date;
	public valeur: number;
	public muscleId: number;
  constructor(){
    this.mensurationId= 0;
    this.date= new Date();
    this.valeur= 0;
    this.muscleId= 0;
  }
}
