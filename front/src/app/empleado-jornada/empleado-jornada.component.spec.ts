import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpleadoJornadaComponent } from './empleado-jornada.component';

describe('EmpleadoJornadaComponent', () => {
  let component: EmpleadoJornadaComponent;
  let fixture: ComponentFixture<EmpleadoJornadaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmpleadoJornadaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmpleadoJornadaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
