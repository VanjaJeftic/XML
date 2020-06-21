import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SviKomentariOglasaDijalogComponent } from './svi-komentari-oglasa-dijalog.component';

describe('SviKomentariOglasaDijalogComponent', () => {
  let component: SviKomentariOglasaDijalogComponent;
  let fixture: ComponentFixture<SviKomentariOglasaDijalogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SviKomentariOglasaDijalogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SviKomentariOglasaDijalogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
