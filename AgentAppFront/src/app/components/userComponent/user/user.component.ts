import { Router } from '@angular/router';
import { OglasService } from './../../../services/oglas.service';
import { Oglas } from './../../../models/oglas';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AuthenticationService } from './../../../services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { stringify } from 'querystring';
import { SearchService } from 'src/app/services/search.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  displayedColumns: string[] = ['Vlasnik', 'Mesto', 'Klasa', 'Model' ,'zakazi']
  sviOglasi: Oglas[] = [];
  oglasiSource: Oglas[] = [];

  constructor(private authService: AuthenticationService, private oglasService: OglasService, private router: Router, private searchService: SearchService) { }

  ngOnInit() {

    this.oglasService.getAllOglasi().subscribe(
      data => {
        this.oglasiSource = data;
        this.sviOglasi = data;
      }
    );

  }

  onRezervisi(selectedOglas){
    console.log(selectedOglas);
    this.router.navigateByUrl('vozilo/' + selectedOglas.id);
  }

  onOdjaviMe(){
    window.localStorage.clear();
    this.authService.logout();
  }

  shopCart(){
    this.router.navigateByUrl('cart');
  }

  resetuj(){
    this.oglasiSource = this.sviOglasi;
  }

  onPretrazi(mesto : string){
    let pomocniOglas : Oglas = new Oglas();
    console.log(mesto);
    pomocniOglas.mesto = mesto;
    this.searchService.pretrazi(pomocniOglas).subscribe(res=>{
      console.log(res);
      this.oglasiSource = res as Oglas[];
    });
  }
}
