import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PorukaZahtevComponent } from './poruka-zahtev.component';

describe('PorukaZahtevComponent', () => {
  let component: PorukaZahtevComponent;
  let fixture: ComponentFixture<PorukaZahtevComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PorukaZahtevComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PorukaZahtevComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
