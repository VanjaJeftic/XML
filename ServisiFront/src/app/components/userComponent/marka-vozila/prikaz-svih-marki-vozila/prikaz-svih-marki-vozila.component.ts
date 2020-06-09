import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';
import { MarkaVozila } from 'src/app/models/marka-vozila';
import { Router, NavigationExtras } from '@angular/router';
import {  ViewChild } from '@angular/core';
import { MatDialog, MatTable } from '@angular/material';
import { analyzeAndValidateNgModules } from '@angular/compiler';
import { filter } from 'rxjs/operators';


@Component({
  selector: 'app-prikaz-svih-marki-vozila',
  templateUrl: './prikaz-svih-marki-vozila.component.html',
  styleUrls: ['./prikaz-svih-marki-vozila.component.css']
})
export class PrikazSvihMarkiVozilaComponent implements OnInit {

  marke: MarkaVozila[];
  markelist$;
  constructor(public dialog: MatDialog,private  markaServis:AdminService,private router: Router) { 
    this.markelist$= markaServis.getMarkeVozila();

  }
  public markaVozila:MarkaVozila=new MarkaVozila();
  
  ngOnInit() {
  }

  izmena(marka) {
  console.log("marka ispis");
   /* let navigationExtras: NavigationExtras = {
      queryParams: {
          "markaVozila": JSON.stringify(marka)
      }
    };
    this.router.navigate(["/izmenaMarkeVozila"],  navigationExtras);*/
    localStorage.setItem("markaVozila", JSON.stringify(marka)); 
    this.router.navigate(["/izmenaMarkeVozila"]);
  }



  deleteMarka(marka: MarkaVozila): void {
    console.log("brisanje marke");
    this.markaServis.deleteMarka(marka)
      .subscribe( data => {
        this.marke = this.marke.filter(u => u !== marka);
      })
  };




}
