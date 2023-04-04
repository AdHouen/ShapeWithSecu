export class Entrainement {

  public entrainementId : number;
	public jour: string;
	public muscle: string;
	public exercice: string;
	public serie: number;
	public repetition: number;
	public poids: number;
	public recup: number;
	public temps: number;
	public distance: number;
	public uid: number;
  constructor(){
    this.entrainementId= 0;
    this.muscle= "";
    this.exercice= "";
    this.jour= "";
    this.serie= 0;
    this.repetition= 0;
    this.poids= 0;
    this.recup= 0;
    this.temps= 0;
    this.distance= 0;
    this.uid= 0;
  }
}
