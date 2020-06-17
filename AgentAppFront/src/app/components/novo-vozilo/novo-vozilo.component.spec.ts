import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NovoVoziloComponent } from './novo-vozilo.component';

describe('NovoVoziloComponent', () => {
  let component: NovoVoziloComponent;
  let fixture: ComponentFixture<NovoVoziloComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NovoVoziloComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NovoVoziloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
