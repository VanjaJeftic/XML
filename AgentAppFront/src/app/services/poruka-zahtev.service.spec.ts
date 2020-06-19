import { TestBed } from '@angular/core/testing';

import { PorukaZahtevService } from './poruka-zahtev.service';

describe('PorukaZahtevService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PorukaZahtevService = TestBed.get(PorukaZahtevService);
    expect(service).toBeTruthy();
  });
});
