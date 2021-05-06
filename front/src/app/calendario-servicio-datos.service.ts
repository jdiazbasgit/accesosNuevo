import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CalendarioServicioDatosService {
  urlCalendario= "http://localhost/api/calendarios?year=";
  urlCalendarioMes= "http://localhost/api/calendarios/";

  constructor() { }

  public cargarMes(anio:number, mes:number){
   return  this.getDatos(this.urlCalendarioMes + anio + "/" + mes);
  }

  public cargarAnio(anio:number){
    return this.getDatos(this.urlCalendario + anio)
   // .then((datos:any) =>{
      //console.log(datos)
  //  })
    
  }

   getDatos(url: string): Promise<string> {
    return  new Promise((resolve, reject) => {
      fetch(url).then(response => response.json()).then((data) => resolve(data))
    });
  }

  public updateDescription(dia:any) {
    console.log("estoy en el servicio, todo saldra bien")
    console.log(dia)
     alert(JSON.stringify(dia))
      fetch(this.urlCalendario, { headers:{"Content-Type":"application/json"}, method: "post", body: JSON.stringify(dia)})
      
    
  }


}
