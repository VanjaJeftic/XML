import { TestBed } from '@angular/core/testing';

import { PutanjaService } from './putanja.service';

describe('PutanjaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PutanjaService = TestBed.get(PutanjaService);
    expect(service).toBeTruthy();
  });
});
