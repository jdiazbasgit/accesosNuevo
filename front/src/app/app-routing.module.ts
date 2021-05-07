import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JornadaComponent } from './jornada/jornada.component';
import { LoginComponentComponent } from './login-component/login-component.component';
import { EmpleadosComponent } from "./empleados/empleados.component";

const routes: Routes = [

  {"path": "","component":LoginComponentComponent},
  {"path": "jornadas","component":JornadaComponent},
  {"path": "login","component":LoginComponentComponent},
  {path: 'empleados', component: EmpleadosComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
