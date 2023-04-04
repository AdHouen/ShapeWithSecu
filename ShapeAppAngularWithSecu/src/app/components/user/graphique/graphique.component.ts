import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-graphique',
  templateUrl: './graphique.component.html',
  styleUrls: ['./graphique.component.css']
})
export class GraphiqueComponent implements OnInit{

  // Define data set for all charts
// declare dataBaby : number = .dataBaby [1, 10, 5, 2, 20, 30, 45];
// declare moreDataBaby : [20, 30, 15, 12, 21, 30, 40];

// myData = {
//   labels: ["January", "February", "March", "April", "May", "June", "July"],
//   datasets: [
//     {
//       label: "Data, baby!",
//       fill: false,
//       backgroundColor: 'rgb(190, 99, 255, 0.25)',
//       borderColor: 'rgb(190, 99, 255)',
//       data: this.dataBaby,
//     },
//       {
//       label: "More data, baby!",
//       fill: true,
//       backgroundColor: 'rgba(255, 99, 132, 0.25)',
//       borderColor: 'rgb(255, 99, 132)',
//       data: moreDataBaby,
//   }]
// };
declare data:any;
constructor(){

}
  ngOnInit(): void {

  // }
  // public config: any = {
  //   type: 'line',
  //   data: data,
  //   options: {
  //     responsive: true,
  //     plugins: {
  //       title: {
  //         display: true,
  //         text: (ctx: { chart: { data: { datasets: { pointStyle: string; }[]; }; }; }) => 'Point Style: ' + ctx.chart.data.datasets[0].pointStyle,
  //       }
  //     }
  //   }
  // };
  }

}
