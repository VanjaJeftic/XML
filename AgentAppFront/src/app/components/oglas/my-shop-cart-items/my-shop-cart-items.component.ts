import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-my-shop-cart-items',
  templateUrl: './my-shop-cart-items.component.html',
  styleUrls: ['./my-shop-cart-items.component.css']
})
export class MyShopCartItemsComponent implements OnInit {

  displayedColumns: string[] = ['vozilo', 'mesto', 'obrisi']
  oglasiSource;
  myShopCartItems: any[] = [];

  constructor() { }

  ngOnInit() {
    this.myShopCartItems = JSON.parse(window.localStorage.getItem('ShopCartItem'));
    this.oglasiSource = new MatTableDataSource(this.myShopCartItems);
  }

}
