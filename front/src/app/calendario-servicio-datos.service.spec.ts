import { TestBed } from '@angular/core/testing';

import { CalendarioServicioDatosService } from './calendario-servicio-datos.service';

describe('CalendarioServicioDatosService', () => {
  let service: CalendarioServicioDatosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CalendarioServicioDatosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
