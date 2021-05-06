import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-semana',
  templateUrl: './semana.component.html',
  styleUrls: ['./semana.component.css']
})
export class SemanaComponent implements OnInit {

  @Input() datasource: [];
  
  @Output()
  dayChange = new EventEmitter<any>();

  constructor() { }

  ngOnInit(): void {
  }

  public dayChangeHandler(dia) {
    
    this.dayChange.emit(dia);
  }



}
