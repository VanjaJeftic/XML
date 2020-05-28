import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-vozilo-details-dialog',
  templateUrl: './vozilo-details-dialog.component.html',
  styleUrls: ['./vozilo-details-dialog.component.css']
})
export class VoziloDetailsDialogComponent implements OnInit {

  oglas;
  shopCartItem = [];

  constructor(public dialogRef : MatDialogRef<VoziloDetailsDialogComponent>, @Inject(MAT_DIALOG_DATA) public model : any) {
    this.oglas = model;
   }

  ngOnInit() {
  }

  onRezervisi(){

    this.shopCartItem = JSON.parse(window.localStorage.getItem('ShopCartItem'));
    if(this.shopCartItem){
       this.shopCartItem.push(this.oglas);
      window.localStorage.setItem('ShopCartItem', JSON.stringify(this.shopCartItem));
    }else{
      this.shopCartItem = [];
      this.shopCartItem.push(this.oglas);
       window.localStorage.setItem('ShopCartItem', JSON.stringify(this.shopCartItem));
    }
  }

}
