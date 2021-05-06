import { Component, OnInit } from '@angular/core';
import { CalendarioServicioDatosService } from '../calendario-servicio-datos.service';

@Component({
  selector: 'app-mes',
  templateUrl: './mes.component.html',
  styleUrls: ['./mes.component.css']
})
export class MesComponent implements OnInit {

  mes: Array<any>
  isDataLoaded: Boolean;
  constructor(private servicioDatos: CalendarioServicioDatosService) {
   // this.mes = this.dataMock();

    // servicioDatos.cargarMes(2021, 4).then((datos: any) => {
    //   this.mes = datos;
    //   this.generarSemanas();
    // })
  }

  ngOnInit(): void {
    this.servicioDatos.cargarMes(2021, 1).then((datos: any) => {
      this.mes = datos;
      this.generarSemanas();
      console.log(this.mes);
      
      this.isDataLoaded= true;
    })
  }

  dayChanges(day:any){
    this.servicioDatos.updateDescription(day);
    console.log(day)
  }

  private dataMock(){
    let month = [];
    let week = [];

    for (let i = 1; i < 5; i++) {
      for (let k = 1; k <= 7; k++) {
        let dia = {fecha: "fecha " + i + " " + k};
        week.push(dia);
      }
      month.push(week);
      week = [];
    }

    console.log(month);
    
    return month;
  }

  private generarSemanas() {
    semanas: [] = [];
    let diasNull = this.mes[0].diaSemana - 1;
    console.log(this.mes[0].diaSemana)
    console.log(diasNull)
    let primeraSemana = []
    for (let i = 1; i <= diasNull; i++) {
      primeraSemana.push({fecha: "",estado:{id:1}})
    }
    let j = 0;
    while (this.mes[j].diaSemana != 1) {
      primeraSemana.push(this.mes[j])
      j++;
    }
    console.log(primeraSemana)
    let month = []

    if (primeraSemana.length > 0) {
      month.push(primeraSemana)
    }
    let semanaActual = []

    for (; j <= this.mes.length - 1; j++) {
      // if (j == 31) {
        
      //   break;
      // }
      // console.log("el valor de j es: " + j)
      // console.log("longitud es: " + this.mes.length)
      semanaActual.push(this.mes[j])
      if (this.mes[j].diaSemana == 7) {
        month.push(semanaActual)
        semanaActual = []
      }

    }
    if (semanaActual.length > 0) {
      month.push(semanaActual);
    }
    
    // let ultimaSemana = []
    console.log(j)
    // for (; j <= this.mes.length - 1; j++) {
    //   console.log("pepito")
    //   ultimaSemana.push(this.mes[j])
    // }
    // month.push(ultimaSemana);
    console.log(month)
    this.mes = month;
  }


}




