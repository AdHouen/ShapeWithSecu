import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-calcul-bmi',
  templateUrl: './calcul-bmi.component.html',
  styleUrls: ['./calcul-bmi.component.css']
})
export class CalculBmiComponent implements OnInit{
  constructor(){



  }

    ngOnInit(): void {




    }


     calculateImc() {

      let form : any = document.getElementById('formBMI');

      let imcBar : any = document.getElementById('IMCbar');
      let imcData :any = document.getElementById('imcData');
      let imc : any = form.weight.value / ((form.height.value / 100) * (form.height.value / 100));
      let imcText;
      if (imc <= 18.5) {
          imcText = 'Sous-pondération';
      } else if (imc > 18.5 && imc <= 25) {
          imcText = 'En bonne santé';
      } else if (imc > 25 && imc <= 30) {
          imcText = 'Surcharge pondérale';
      } else if (imc > 30 && imc <= 35) {
          imcText = 'Obèsité Niveau 1';
      } else if (imc > 35 && imc <= 40) {
          imcText = 'Obèsité Niveau 2';
      } else if (imc > 40 && imc <= 50) {
          imcText = 'Obèsité Niveau 3';
      } else if (imc > 50) {
          imcText = 'Obèsité Niveau 4';
      }
      if (imc <= 15) {
          imcBar.style.width = '0%';
      } else if (imc >= 50) { // si es 50 o mayor es 100%
          imcBar.style.width = '100%';
      } else { // If IMC wis between 15 and 50
          imcBar.style.width = (((imc - 15) * 100) / 35) + '%';
      }
      imcData.innerHTML = `IMC: ${parseFloat(imc).toFixed(2)}  ${imcText}`;
      return false;
    }


}
