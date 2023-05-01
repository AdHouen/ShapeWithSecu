import { AddDataComponent } from './components/user/mensuration/data/add-data/add-data.component';
import { RegisterComponent } from './components/login-register/register/register.component';
import { ContactComponent } from './components/annexe/contact/contact.component';
import { LoginComponent } from './components/login-register/login/login.component';
import { AccueilComponent } from './components/annexe/accueil/accueil.component';
import { ListDataComponent } from './components/user/mensuration/data/list-data/list-data.component';
import { EditEntrainementComponent } from './components/user/entrainement/edit-entrainement/edit-entrainement.component';
import { UploadFileComponent } from './components/user/gallerie/upload-file/upload-file.component';
import { Routes, RouterModule } from '@angular/router';
import { AddEntrainementComponent } from './components/user/entrainement/add-entrainement/add-entrainement.component';
import { ListEntrainementComponent } from './components/user/entrainement/list-entrainement/list-entrainement.component';
import { ExerciceComponent } from './components/user/exercice/exercice.component';
import { GraphiqueComponent } from './components/user/graphique/graphique.component';
import { CalculBmiComponent } from './components/user/calcul-bmi/calcul-bmi.component';
import { NgModule } from '@angular/core';
import { AuthenticationGuard } from './guard/authentification.guard';



const routes: Routes = [

  {path: 'accueil',component:AccueilComponent},// Accueil
  {path: '',component:AccueilComponent},// Accueil
  {path: 'login',component:LoginComponent},// Login
  {path: 'register',component:RegisterComponent},// Register
  {path: 'contact',component:ContactComponent},// Contact
  {path: 'bmi',component:CalculBmiComponent, canActivate:[AuthenticationGuard] },// IMC
  {path: 'graphique',component:GraphiqueComponent,canActivate:[AuthenticationGuard]},// Graphique
  {path: 'exercice',component:ExerciceComponent,canActivate:[AuthenticationGuard]},// Exercice
  {path: 'entrainement' ,component:ListEntrainementComponent,canActivate:[AuthenticationGuard]},// Entrainement
  {path: 'entrainements/:id' ,component:ListEntrainementComponent,canActivate:[AuthenticationGuard]},
  {path: 'addEntrainement' ,component:AddEntrainementComponent,canActivate:[AuthenticationGuard] },// Ajouter un entrainement
  {path: 'editEntrainement/:id' ,component:EditEntrainementComponent,canActivate:[AuthenticationGuard] },// Edit un entrainement
  {path: 'galerie' ,component:UploadFileComponent,canActivate:[AuthenticationGuard]},// Galerie
  {path: 'data' ,component: ListDataComponent,canActivate:[AuthenticationGuard] },// Mensuration
  {path: 'data/:id' ,component: ListDataComponent,canActivate:[AuthenticationGuard]},
  {path: 'addData' ,component: AddDataComponent,canActivate:[AuthenticationGuard]},



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
