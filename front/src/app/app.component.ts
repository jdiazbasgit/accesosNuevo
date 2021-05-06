import { Component } from '@angular/core';
import { ServiceService } from "../app/service.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'accesosFront';

  constructor(
    private service: ServiceService
  ) { }

  ngOnInit(): void {
    
    
  }

  login(){
    this.service.postLogin().subscribe (
      (data: any) => {
        console.log(data);
        sessionStorage.setItem('token', data.token)
      }
    )
  }
}
