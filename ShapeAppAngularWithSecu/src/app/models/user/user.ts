export class User {

  public uid: number; // id de la base de données DB

public userId: string; // id de l utilisateur
public  email: string;
public  password: string;
public  username: string;
public  authorities : string[]; // [] = tableau de String // Authorities = permissions (read, edit, delete)
public  firstname : string;
public  lastname: string;
public currentUsername: string;
public  profileImageURL : string;
public  lastLoginDate: Date;
public  lastLoginDateDisplay : Date;
public  joinDate : Date;
public  role: string; // ROLE_USER(read,edit), ROLE_ADMIN(delete)
public  active : boolean;// Pour activer les rôles
public  notLocked : boolean;

  constructor(){
      this.uid = 0,
      this.userId = '',
      this.email = '',
      this.password = '',
      this.username = '',
      this.authorities = [],
      this.firstname = '',
      this.lastname = '',
      this.currentUsername = '',
      this.profileImageURL = '',
      this.lastLoginDate = new Date(),
      this.lastLoginDateDisplay = new Date(),
      this.joinDate = new Date(),
      this.role = '',
      this.active = false,
      this.notLocked = false;
    }


}
