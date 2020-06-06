import { Router } from '@angular/router';
import { OglasService } from './../../../services/oglas.service';
import { Oglas } from './../../../models/oglas';
import { AuthenticationService } from './../../../services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/services/search.service';
import { DateTimeAdapter } from 'ng-pick-datetime';
import { Search } from 'src/app/models/search.model';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  displayedColumns: string[] = ['Vlasnik', 'Mesto', 'Klasa', 'Model' ,'zakazi']
  sviOglasi: Oglas[] = [];
  oglasiSource: Oglas[] = [];
  startAt: Date = new Date();

  constructor(dateTimeAdapter: DateTimeAdapter<any>, private authService: AuthenticationService, private oglasService: OglasService, private router: Router, private searchService: SearchService) {
    dateTimeAdapter.setLocale('en-GB');
   }

  ngOnInit() {

    this.oglasService.getAllOglasi().subscribe(
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

  shopCart(){
    this.router.navigateByUrl('cart');
  }

  resetuj(){
    this.oglasiSource = this.sviOglasi;
  }

  onPretrazi(mesto : string, datum : string, marka : string, model : string, maksimalnaCena : string, minimalnaCena : string){
    let pomocniSearch : Search = new Search();
    pomocniSearch.mesto = mesto;
    pomocniSearch.datumi = datum;
    pomocniSearch.marka = marka;
    pomocniSearch.model = model;
    pomocniSearch.minimalnaCena = minimalnaCena;
    pomocniSearch.maksimalnaCena = maksimalnaCena;
    console.log(pomocniSearch);
    
    this.searchService.pretrazi(pomocniSearch).subscribe(res=>{
      this.oglasiSource = res as Oglas[];
    });
  }

  getDatum(d : Date){
    let datum: string = (d[2]>=10?d[2]:"0"+d[2]) + "-" + (d[1]>=10?d[1]:"0"+d[1]) + "-" + d[0] + ", " + (d[3]>=10?d[3]:"0"+d[3]) + ":" + (d[4]>=10?d[4]:"0"+d[4]);
    return datum;
  }
}
