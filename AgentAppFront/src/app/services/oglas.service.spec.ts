import { TestBed } from '@angular/core/testing';

import { OglasService } from './oglas.service';

describe('OglasService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OglasService = TestBed.get(OglasService);
    expect(service).toBeTruthy();
  });
});
