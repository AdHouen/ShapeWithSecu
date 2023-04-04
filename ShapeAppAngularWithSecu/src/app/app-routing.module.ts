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


const routes: Routes = [

  {path: 'accueil',component:AccueilComponent},// Accueil
  {path: 'login',component:LoginComponent},// Login
  {path: 'register',component:RegisterComponent},// Register
  {path: 'contact',component:ContactComponent},// Cobntact
  {path: 'bmi',component:CalculBmiComponent, },// IMC
  {path: 'graphique',component:GraphiqueComponent},// Graphique
  {path: 'exercice',component:ExerciceComponent},// Exercice
  {path: 'entrainement' ,component:ListEntrainementComponent },// Entrainement
  {path: 'entrainements/:id' ,component:ListEntrainementComponent },
  {path: 'addEntrainement' ,component:AddEntrainementComponent },// Ajouter un entrainement
  {path: 'editEntrainement/:id' ,component:EditEntrainementComponent },// Edit un entrainement
  {path: 'galerie' ,component:UploadFileComponent },// Galerie
  {path: 'data' ,component: ListDataComponent },// Mensuration
  {path: 'data/:id' ,component: ListDataComponent },



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
