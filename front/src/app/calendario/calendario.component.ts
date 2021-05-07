import { Component, OnInit } from '@angular/core';
import { CalendarioServicioDatosService } from '../calendario-servicio-datos.service';

@Component({
  selector: 'app-calendario',
  templateUrl: './calendario.component.html',
  styleUrls: ['./calendario.component.css']
})
export class CalendarioComponent implements OnInit {
  
  anio: Array<any>
  
  constructor(private servicioDatos: CalendarioServicioDatosService) {
    // servicioDatos.cargarAnio(2021).then((datos: any) => {
    //   this.anio = datos;
    //   console.log(this.anio)
    // })
      // servicioDatos.cargarMes(2021, 3);
  }

  ngOnInit(): void {
  }

}
