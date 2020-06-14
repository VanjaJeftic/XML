import { TestBed } from '@angular/core/testing';

import { VoziloService } from './vozilo.service';

describe('VoziloService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VoziloService = TestBed.get(VoziloService);
    expect(service).toBeTruthy();
  });
});
