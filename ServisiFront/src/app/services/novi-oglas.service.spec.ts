import { TestBed } from '@angular/core/testing';

import { NoviOglasService } from './novi-oglas.service';

describe('NoviOglasService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NoviOglasService = TestBed.get(NoviOglasService);
    expect(service).toBeTruthy();
  });
});
