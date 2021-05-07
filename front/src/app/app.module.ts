import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { EmpleadosComponent } from './empleados/empleados.component';
import { JornadaComponent } from './jornada/jornada.component';
import { LoginComponentComponent } from './login-component/login-component.component';
import { HeaderComponent } from './header/header.component';
import { JornadaDatosService } from './jornada-datos.service';

import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FormularioEmpleadoComponent } from './formulario-empleado/formulario-empleado.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,

    JornadaComponent,
    LoginComponentComponent,
    HeaderComponent,
     EmpleadosComponent,
    FormularioEmpleadoComponent
  ],
  
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
