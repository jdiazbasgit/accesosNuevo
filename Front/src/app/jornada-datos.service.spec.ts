import { TestBed } from '@angular/core/testing';

import { JornadaDatosService } from './jornada-datos.service';

describe('JornadaDatosService', () => {
  let service: JornadaDatosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JornadaDatosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
