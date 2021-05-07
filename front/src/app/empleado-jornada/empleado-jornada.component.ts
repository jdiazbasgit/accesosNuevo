import { VariableBinding } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Empleado, Jornada, ServiceService } from '../empleados-jornadas.service';

@Component({
  selector: 'app-empleado-jornada',
  templateUrl: './empleado-jornada.component.html',
  styleUrls: ['./empleado-jornada.component.css']
})
export class EmpleadoJornadaComponent implements OnInit {

  public datosEmp: Array<Empleado>
  public datosJor: Array<Jornada> = new Array()
  public datosJorEmp: Array<Jornada> = new Array()

  constructor(private dataService: ServiceService) {


    //this.datosJor = this.dataService.getDatosJornadas();
    this.datosEmp = this.dataService.getDatosEmpleados();
    this.datosJor = this.dataService.getDatosJornadas();
  }

  ngOnInit(): void {
  }


  public jornadasChangeManejador(id, empleado) {
    alert("ha seleccionado " + id + " del empleado " + empleado.nombre)

    // empleado.jornada.id = id;
    // console.log(empleado)
    this.dataService.actualizarJornada(id,empleado)

  }

  

}


