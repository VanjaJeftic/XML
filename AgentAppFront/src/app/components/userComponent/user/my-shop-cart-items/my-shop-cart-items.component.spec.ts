import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyShopCartItemsComponent } from './my-shop-cart-items.component';

describe('MyShopCartItemsComponent', () => {
  let component: MyShopCartItemsComponent;
  let fixture: ComponentFixture<MyShopCartItemsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyShopCartItemsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyShopCartItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
