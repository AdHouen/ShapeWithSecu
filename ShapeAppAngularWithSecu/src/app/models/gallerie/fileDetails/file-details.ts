export class FileDetails {

  public galerieId: number;
	public galeriefilename: string;
	public date: Date;
	public uid: number;
  constructor(){
    this.galerieId = 0;
    this.galeriefilename = "";
    this.date = new Date ();
    this.uid = 0;
  }
}




