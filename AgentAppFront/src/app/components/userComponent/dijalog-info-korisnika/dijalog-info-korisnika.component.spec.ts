import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DijalogInfoKorisnikaComponent } from './dijalog-info-korisnika.component';

describe('DijalogInfoKorisnikaComponent', () => {
  let component: DijalogInfoKorisnikaComponent;
  let fixture: ComponentFixture<DijalogInfoKorisnikaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DijalogInfoKorisnikaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DijalogInfoKorisnikaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
