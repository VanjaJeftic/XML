import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikazSvihTipovaGorivaComponent } from './prikaz-svih-tipova-goriva.component';

describe('PrikazSvihTipovaGorivaComponent', () => {
  let component: PrikazSvihTipovaGorivaComponent;
  let fixture: ComponentFixture<PrikazSvihTipovaGorivaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrikazSvihTipovaGorivaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrikazSvihTipovaGorivaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
