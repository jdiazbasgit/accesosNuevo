import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { JornadaComponent } from './jornada/jornada.component';
import { LoginComponentComponent } from './login-component-jornadas/login-component-jornadas.component';
import { EmpleadosComponent } from "./empleados/empleados.component";
import { EmpleadoJornadaComponent } from './empleado-jornada/empleado-jornada.component';

const routes: Routes = [

  { path: "", component: LoginComponentComponent },
  { path: "jornadas", component: JornadaComponent },
  { path: 'empleados', component: EmpleadosComponent },
  { path: "login", component: LoginComponentComponent },
  { path: "empleados-jornada", component:EmpleadoJornadaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})


export class AppRoutingModule { }

