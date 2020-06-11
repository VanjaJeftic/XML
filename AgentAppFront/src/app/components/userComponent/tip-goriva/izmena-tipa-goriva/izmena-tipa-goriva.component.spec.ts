import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzmenaTipaGorivaComponent } from './izmena-tipa-goriva.component';

describe('IzmenaTipaGorivaComponent', () => {
  let component: IzmenaTipaGorivaComponent;
  let fixture: ComponentFixture<IzmenaTipaGorivaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzmenaTipaGorivaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzmenaTipaGorivaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
