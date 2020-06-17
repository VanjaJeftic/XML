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
import { Search } from 'src/app/models/search.model';
import { DateTimeAdapter } from 'ng-pick-datetime';
import { SearchView } from 'src/app/models/search-view.model';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  sviOglasi: Search[] = [];
  oglasiSource: Search[] = [];
  startAt: Date = new Date();

  constructor(dateTimeAdapter: DateTimeAdapter<any>,private authService: AuthenticationService, private oglasService: OglasService, private router: Router, private searchService: SearchService) {
    dateTimeAdapter.setLocale('en-GB');
  }

  ngOnInit() {

    this.searchService.getAll().subscribe(
      data => {
        this.oglasiSource = data;
        this.sviOglasi = data;
      }
    );
    this.startAt.setHours(this.startAt.getHours() + 48);

  }

  onRezervisi(selectedOglas){
    console.log(selectedOglas);
    this.router.navigateByUrl('vozilo/' + selectedOglas.id);
  }

  onOdjaviMe(){
    window.localStorage.clear();
    this.authService.logout();
  }

  onUnesiIzvestaj(){
    this.router.navigateByUrl('user/izvestaj');
  }

  onZahtevi(){
    this.router.navigateByUrl('user/zahtev');
  }

  onShoppingCart(){
    this.router.navigateByUrl('user/cart');
  }

  onVozilo(){
    this.router.navigateByUrl('vozilo/novoVozilo');
  }

  onMojaVozila(){
    this.router.navigateByUrl('svavozila');
  }

  resetuj(){
    this.oglasiSource = this.sviOglasi;
  }

  onPretrazi(mesto : string, datum : string, marka : string, model : string, maksimalnaCena : string, minimalnaCena : string){
    let pomocniSearch : SearchView = new SearchView();
    pomocniSearch.datumi = datum;
    pomocniSearch.mesto = mesto;
    pomocniSearch.marka = marka;
    pomocniSearch.model = model;
    pomocniSearch.maksimalnaCena = maksimalnaCena;
    pomocniSearch.minimalnaCena = minimalnaCena;

    this.searchService.pretrazi(pomocniSearch).subscribe(res=>{
      console.log(res);
      this.oglasiSource = res as Search[];
    });
  }

  getDatum(da : Date){
    let d : Date = new Date(da.toString());
    console.log(d.getFullYear());
    let datum: string = (d.getDay()>=10?d.getDay():"0"+d.getDay()) + "-" + (d.getMonth()>=10?d.getMonth():"0"+d.getMonth()) + "-" + d.getFullYear() + ", " + (d.getHours()>=10?d.getHours():"0"+d.getHours()) + ":" + (d.getMinutes()>=10?d.getMinutes():"0"+d.getMinutes());
    return datum;
  }
}
