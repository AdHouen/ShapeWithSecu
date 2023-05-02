import { ListDataComponent } from './components/user/mensuration/data/list-data/list-data.component';
import { UploadFileComponent } from './components/user/gallerie/upload-file/upload-file.component';
import { ExerciceComponent } from './components/user/exercice/exercice.component';
import { AddEntrainementComponent } from './components/user/entrainement/add-entrainement/add-entrainement.component';
import { ListEntrainementComponent } from './components/user/entrainement/list-entrainement/list-entrainement.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CalculBmiComponent } from './components/user/calcul-bmi/calcul-bmi.component';
import { GraphiqueComponent } from './components/user/graphique/graphique.component';

import { NotifierModule, NotifierOptions } from 'angular-notifier';
import { CommonModule } from '@angular/common';
import { ListGallerieComponent } from './components/user/gallerie/list-gallerie/list-gallerie.component';
import { GallerieImageComponent } from './components/user/gallerie/gallerie-image/gallerie-image.component';
import { EditEntrainementComponent } from './components/user/entrainement/edit-entrainement/edit-entrainement.component';
import { AddDataComponent } from './components/user/mensuration/data/add-data/add-data.component';
import { EditDataComponent } from './components/user/mensuration/data/edit-data/edit-data.component';
import { NousChoisirComponent } from './components/annexe/nous-choisir/nous-choisir.component';
import { CarouselAccueilComponent } from './components/annexe/carousel-accueil/carousel-accueil.component';
import { AccueilComponent } from './components/annexe/accueil/accueil.component';
import { DonationComponent } from './components/annexe/donation/donation.component';
import { LoginComponent } from './components/login-register/login/login.component';
import { HeaderComponent } from './components/annexe/header/header.component';
import { FooterComponent } from './components/annexe/footer/footer.component';
import { ContactComponent } from './components/annexe/contact/contact.component';
import { CounterComponent } from './components/annexe/counter/counter.component';
import { TeamComponent } from './components/annexe/team/team.component';
import { RegisterComponent } from './components/login-register/register/register.component';
import { UserComponent } from './components/user/user/user/user.component';






/**
 * Custom angular notifier options
 */
const customNotifierOptions: NotifierOptions = {
  position: {
		horizontal: {
			position: 'left',
			distance: 100
		},
		vertical: {
			position: 'bottom',
			distance: 12,
			gap: 10
		}
	},
  theme: 'material',
  behaviour: {
    autoHide: 5000,
    onClick: 'hide',
    onMouseover: 'pauseAutoHide',
    showDismissButton: true,
    stacking: 4
  },
  animations: {
    enabled: true,
    show: {
      preset: 'slide',
      speed: 300,
      easing: 'ease'
    },
    hide: {
      preset: 'fade',
      speed: 300,
      easing: 'ease',
      offset: 50
    },
    shift: {
      speed: 300,
      easing: 'ease'
    },
    overlap: 150
  }
};




@NgModule({
  declarations: [
    AppComponent,
    CalculBmiComponent,
    GraphiqueComponent,
    ExerciceComponent,
    ListEntrainementComponent,
    AddEntrainementComponent,
    ListGallerieComponent,
    UploadFileComponent,
    GallerieImageComponent,
    EditEntrainementComponent,
    AddDataComponent,
    EditDataComponent,
    ListDataComponent,
    NousChoisirComponent,
    CarouselAccueilComponent,
    AccueilComponent,
    DonationComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    ContactComponent,
    CounterComponent,
    TeamComponent,
    RegisterComponent,
    UserComponent,

  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NotifierModule.withConfig(customNotifierOptions),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
