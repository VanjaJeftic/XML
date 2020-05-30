import { TestBed } from '@angular/core/testing';

import { PraviOglasService } from './pravi-oglas.service';

describe('PraviOglasService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PraviOglasService = TestBed.get(PraviOglasService);
    expect(service).toBeTruthy();
  });
});
