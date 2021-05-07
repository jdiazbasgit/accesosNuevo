import { AfterViewInit, Component, TemplateRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { Jornada, JornadaDatosService } from '../jornada-datos.service';

@Component({
  selector: 'app-jornada',
  templateUrl: './jornada.component.html',
  styleUrls: ['./jornada.component.css']
})
export class JornadaComponent implements AfterViewInit {

  @ViewChild('modalTemplate') modalTemplate: TemplateRef<any>;
  token;
  logoutText;
  descripcionValue;
  specialValue;
  option;
  modalRef;
  operation: string = "Nueva";
  service: any;
  weekArray = ['lunes', 'martes', 'miercoles', 'jueves', 'viernes', 'sabado', 'domingo'];
  url: string = "http://localhost:80/api/jornadas/";
  jornadas: Jornada[] = [];

  constructor(service: JornadaDatosService, config: NgbModalConfig, private modal: NgbModal, private router: Router) {
    this.token = sessionStorage.getItem("token");

    if (this.token == null) {
      this.router.navigate(["login"]);
      this.logoutText = `Logout: ${sessionStorage.getItem("user")}`;
    }

    this.service = service;
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngAfterViewInit(): void {
    this.getJornadas(this.jornadas);
  }

  getJornadas(jornadas: Jornada[]) {
    this.service.getDatos(this.url, this.token)
      .then((datos: any) => {
        datos.forEach((element: any) => {
          let jornada: Jornada;
          jornada = new Jornada(
            element.id,
            this.treatDay(element.lunes),
            this.treatDay(element.martes),
            this.treatDay(element.miercoles),
            this.treatDay(element.jueves),
            this.treatDay(element.viernes),
            this.treatDay(element.sabado),
            this.treatDay(element.domingo),
            element.descripcion,
            element.especial);
          jornadas.push(jornada);
        });
      })
  }

  treatDay(turnos: string): string {
    let result = "";
    let horas = turnos.split("&");
    horas.forEach((element: any) => {
      result = result + element + "\n";
    })
    return result;
  }

  treatSpecial(especial: number): string {
    let result = "";
    if (especial === 0) {
      result = "No";
    }
    else if (especial === 1) {
      result = "Si"
    } else {
      result = "N/A"
    }
    return result;
  }

  showModal(template, operation, id = 0) {
    this.modalRef = this.modal.open(template, { size: 'lg' });
    this.tableGenerator(operation, id);
    this.tableUpdater(2);

    this.modalRef.result.then((result) => {
      let tbody = <HTMLElement>result;
      let turns = Array();
      let i;
      for (i = 1; i <= this.option; i++)
        Array.from(tbody.getElementsByClassName(`t${i}`)).forEach((element) => {
          turns.push((<HTMLInputElement>element).value);
        })
      this.saveDays(this.generateWorkday(id, turns, operation));
    }, () => {
      //Evitamos errores del promise al cerrar el modal
    })
  }

  tableUpdater(opt) {
    switch (opt) {
      case 1:
        Array.from(document.getElementsByClassName("t2")).forEach(element => {
          (<HTMLInputElement>element).disabled = true;
        });
        Array.from(document.getElementsByClassName("t3")).forEach(element => {
          (<HTMLInputElement>element).disabled = true;
        });
        break;
      case 2:
        Array.from(document.getElementsByClassName("t2")).forEach(element => {
          (<HTMLInputElement>element).disabled = false;
        });
        Array.from(document.getElementsByClassName("t3")).forEach(element => {
          (<HTMLInputElement>element).disabled = true;
        });
        break;
      case 3:
        Array.from(document.getElementsByClassName("t2")).forEach(element => {
          (<HTMLInputElement>element).disabled = false;
        });
        Array.from(document.getElementsByClassName("t3")).forEach(element => {
          (<HTMLInputElement>element).disabled = false;
        });
    }
    this.option = opt;
  }

  tableGenerator(operation, id) {
    let tbody = document.getElementById("tablaJornadaBody");
    let days = ['lunes', 'martes', 'miercoles', 'jueves', 'viernes', 'sabado', 'domingo'];
    this.operation = operation;
    if (operation == 'edit') {

      this.service.getDatos(this.url + this.jornadas[id].id, sessionStorage.getItem("token")).then((datos: any) => {
        let i;
        let max = 1;
        let myEspecial = (<HTMLInputElement>document.getElementById("especial"));
        (<HTMLInputElement>document.getElementById("descripcion")).value = datos['descripcion'];
        myEspecial.checked = true;

        if (datos['especial'] == 0) 
        {
          myEspecial.checked = false;
        }

        for (i = 0; i < 7; i++) {
          let jornada = datos[days[i]].split("&");

          if (jornada == undefined)
            tbody.appendChild(this.rowGenerator(i));
          else {
            if (jornada.length > max)
              max = jornada.length;

            tbody.appendChild(this.rowGenerator(i, jornada));
          }
        }
        (<HTMLInputElement>document.getElementById(`${max}j`)).checked = true;
        this.tableUpdater(max);
      })
    } else {
      let i;
      for (i = 0; i < 7; i++) 
      {
        tbody.appendChild(this.rowGenerator(i));
      }
    }
  }

  rowGenerator(index, jornada = ["08:00-15:00", "15:00-23:00", "23:30-07:30"]) {
    let days = ['Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado', 'Domingo'];
    let tr = document.createElement('tr');
    tr.setAttribute("text-align", "left");

    let td = document.createElement('td');
    td.innerHTML = days[index];
    tr.appendChild(td);

    let len = jornada.length;

    var j;
    for (j = 0; j < 3; j++) {
      td = document.createElement('td');

      if (j >= len)
        jornada.push("00:00-00:00");

      let turno = jornada[j].split("-");

      if (turno.length == 2) {
        if (turno[0].length < 5)
          turno[0] = "0" + turno[0];
        if (turno[1].length < 5)
          turno[1] = "0" + turno[1];
      } else {
        turno = new Array();
        turno.push("00:00");
        turno.push("00:00");
      }
      td.innerHTML = `<td><input class="t${j + 1}" value="${turno[0]}" type="time"> - <input class="t${j + 1}" value="${turno[1]}" type="time">`;
      tr.appendChild(td);
    }
    return tr;
  }

  generateWorkday(id, turns, operation) {
    let days = Array();
    let day: String = "";
    let i = 0, index = turns.length / this.option;
    this.descripcionValue = (<HTMLInputElement>document.getElementById("descripcion")).value.toString();
    if ((<HTMLInputElement>document.getElementById("especial")).checked == true) {
      this.specialValue = 1;
    }
    else {
      this.specialValue = 0;
    }

    for (i; i < index; i += 2) {
      day = "";
      day = day + turns[i] + "-";
      day = day + turns[i + 1];

      if (this.option > 1) {
        day = day + "&" + turns[index + i] + "-";
        day = day + turns[index + i + 1];
      }

      if (this.option > 2) {
        day = day + "&" + turns[index * 2 + i] + "-";
        day = day + turns[index * 2 + i + 1];
      }
      days.push(day);
    }

    let myJornada: Jornada;
    if (operation == 'new') {
      myJornada = new Jornada(0, days[0], days[1], days[2], days[3], days[4], days[5], days[6], this.descripcionValue, this.specialValue);
    }

    else {
      myJornada = new Jornada(this.jornadas[id].id, days[0], days[1], days[2], days[3], days[4], days[5], days[6], this.descripcionValue, this.specialValue);
    
    }
    return myJornada;
  }

  saveDays(jornada) {  
    fetch(this.url, {
      method: 'POST',
      headers: {
        'Authorization': sessionStorage.getItem("token"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(jornada)
    })
      .then(() => {
        this.jornadas = [];
        this.getJornadas(this.jornadas);
      }, () => {
        alert("error");
      });
  }

  logout(): void {
    sessionStorage.clear();
    this.logoutText = "";
    this.router.navigate(["login"]);
  }

}
