import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { JornadaComponent } from './jornada/jornada.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { LoginComponentComponent } from './login-component/login-component.component';
import { HeaderComponent } from './header/header.component';
import { JornadaDatosService } from './jornada-datos.service';

@NgModule({
  declarations: [
    AppComponent,
    JornadaComponent,
    LoginComponentComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
