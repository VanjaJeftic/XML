import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DijalogRegistracijaAgentaComponent } from './dijalog-registracija-agenta.component';

describe('DijalogRegistracijaAgentaComponent', () => {
  let component: DijalogRegistracijaAgentaComponent;
  let fixture: ComponentFixture<DijalogRegistracijaAgentaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DijalogRegistracijaAgentaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DijalogRegistracijaAgentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
