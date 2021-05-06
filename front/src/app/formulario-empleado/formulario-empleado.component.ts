import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Empleado } from '../empleadosservice.service';
import { EmpleadosServiceService } from '../empleadosservice.service';

@Component({
  selector: 'app-formulario-empleado',
  templateUrl: './formulario-empleado.component.html',
})
export class FormularioEmpleadoComponent {
  @Input() empleado: Empleado;

  constructor(
    public activeModal: NgbActiveModal,
    private service: EmpleadosServiceService
  ) { }

  guardarEmpleado() {
    this.service.postDatosEmpleado(this.empleado).subscribe(
      (newEmpleado) => {
        newEmpleado = this.empleado;
        console.log(newEmpleado)
        this.activeModal.close();
      }
    );
  }
}
