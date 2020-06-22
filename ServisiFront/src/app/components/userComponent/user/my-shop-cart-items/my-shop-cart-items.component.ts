import { AuthenticationService } from './../../../../services/authentication.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { ZahtevService } from './../../../../services/zahtev.service';
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

  constructor(private zahtevService: ZahtevService, private router: Router, private snackBar: MatSnackBar, 
                  private authService: AuthenticationService) { }

  ngOnInit() {
    this.shopCartItems = JSON.parse(window.localStorage.getItem('ShopCartItem'));
  
    if(this.shopCartItems != null || this.shopCartItems != undefined){
      this.cartEmpty = false;
      for(let z of this.shopCartItems){
        let preuzimanje = z.preuzimanje;
        let preuzimanjeArray = preuzimanje.split('T');
        z.preuzimanje = preuzimanjeArray[0].concat(' ', preuzimanjeArray[1]);
  
        let povratak = z.povratak;
        let povratakArray = povratak.split('T');
        z.povratak = povratakArray[0].concat(' ', povratakArray[1]);
        
      }
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

    this.sendShopCart.podnosilac = parseInt(localStorage.getItem('userId'));

    console.log(this.sendShopCart);
    for(let z of this.sendShopCart.zahtevi){
      let preuzimanje = z.preuzimanje;
      let povratak = z.povratak;

      let preuzimanjeArray = preuzimanje.split(' ');
      z.preuzimanje = preuzimanjeArray[0].concat('T', preuzimanjeArray[1]);

      let povratakArray = povratak.split(' ');
      z.povratak = povratakArray[0].concat('T', povratakArray[1]);
    }
    this.zahtevService.rezervisi(this.sendShopCart).subscribe(
      data => {
        this.snackBar.open('Zahtevi su kreirani! Sacekajte odgovor vlasnika', 'U redu', { duration: 10000 });
        window.localStorage.removeItem('ShopCartItem');
        this.isButtonDisabled = true;
      }
    );
    
  }

  onZahtevi(){
    this.router.navigateByUrl('user/zahtev');
  }

  onDetaljnije(zahtev){
    this.router.navigateByUrl('vozilo/' + zahtev.oglas.vozilo.id);
  }

  onOdjaviMe(){
    window.localStorage.clear();
    this.authService.logout();
  }
}
