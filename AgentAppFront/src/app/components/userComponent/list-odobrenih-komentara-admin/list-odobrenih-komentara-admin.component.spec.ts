import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOdobrenihKomentaraAdminComponent } from './list-odobrenih-komentara-admin.component';

describe('ListOdobrenihKomentaraAdminComponent', () => {
  let component: ListOdobrenihKomentaraAdminComponent;
  let fixture: ComponentFixture<ListOdobrenihKomentaraAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListOdobrenihKomentaraAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListOdobrenihKomentaraAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
