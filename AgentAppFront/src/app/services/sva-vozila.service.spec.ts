import { TestBed } from '@angular/core/testing';

import { SvaVozilaService } from './sva-vozila.service';

describe('SvaVozilaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SvaVozilaService = TestBed.get(SvaVozilaService);
    expect(service).toBeTruthy();
  });
});
