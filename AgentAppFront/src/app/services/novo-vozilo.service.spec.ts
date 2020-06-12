import { TestBed } from '@angular/core/testing';

import { NovoVoziloService } from './novo-vozilo.service';

describe('NovoVoziloService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NovoVoziloService = TestBed.get(NovoVoziloService);
    expect(service).toBeTruthy();
  });
});
