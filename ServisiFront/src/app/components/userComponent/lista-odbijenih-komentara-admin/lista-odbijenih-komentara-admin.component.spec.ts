import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaOdbijenihKomentaraAdminComponent } from './lista-odbijenih-komentara-admin.component';

describe('ListaOdbijenihKomentaraAdminComponent', () => {
  let component: ListaOdbijenihKomentaraAdminComponent;
  let fixture: ComponentFixture<ListaOdbijenihKomentaraAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaOdbijenihKomentaraAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaOdbijenihKomentaraAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
