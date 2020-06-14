import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { ZahtevService } from './../../../../services/zahtev.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ShopCartItems } from './../../../../models/shop-cart-items';
import { Zahtev } from './../../../../models/zahtev';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-my-shop-cart-items',
  templateUrl: './my-shop-cart-items.component.html',
  styleUrls: ['./my-shop-cart-items.component.css']
})
export class MyShopCartItemsComponent implements OnInit {

  selectedOptions = []; //Selektovane opcije koje ce biti grupisane u BUNDLE

  shopCartItems: Zahtev[] = [];
  selektovaniZaBundle: Zahtev[] = [];

  sendShopCart: ShopCartItems = new ShopCartItems();

  isButtonDisabled = false;
  cartEmpty = true;

  constructor(private zahtevService: ZahtevService, private router: Router, private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.shopCartItems = JSON.parse(window.localStorage.getItem('ShopCartItem'));
    if(this.shopCartItems != null || this.shopCartItems != undefined){
      this.cartEmpty = false;
    }
  }

  onNgModelChange(event){
    this.selektovaniZaBundle = event;
  }
  
  rezervisi(){
    for(let z of this.shopCartItems){
      if(this.selektovaniZaBundle.indexOf(z) > -1){
        z.bundle = true;
      }else {
        z.bundle = false;
      }
    }
    console.log(this.shopCartItems);
    for(let z of this.shopCartItems){
      this.sendShopCart.zahtevi.push(z);
    }

    for(let z of this.sendShopCart.zahtevi){
      z.oglas.vozilo.klasaVozila = null;
      z.oglas.vozilo.markaVozila = null;
      z.oglas.vozilo.modelVozila = null;
      z.oglas.vozilo.tipgoriva = null;
      z.oglas.vozilo.vrstamenjaca = null;
    }

    console.log(this.sendShopCart);
    this.zahtevService.rezervisi(this.sendShopCart).subscribe(
      data => {
        this.snackBar.open('Zahtevi su kreirani! Sacekajte odgovor vlasnika', 'U redu', { duration: 10000 });
        window.localStorage.clear();
        this.isButtonDisabled = true;
      }
    );
    
  }

  onDetaljnije(zahtev){
    this.router.navigateByUrl('vozilo/' + zahtev.oglas.vozilo.id);
  }
  
}
