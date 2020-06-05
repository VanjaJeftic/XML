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

  shopCart(){
    this.router.navigateByUrl('cart');
  }

  resetuj(){
    this.oglasiSource = this.sviOglasi;
  }

  onPretrazi(mesto : string, datum : string, marka : string, model : string, maksimalnaCena : string, minimalnaCena : string){
    this.searchService.pretrazi(mesto, minimalnaCena, maksimalnaCena, datum, model, marka).subscribe(res=>{
      console.log(res);
      this.oglasiSource = res as Search[];
    });
  }

  getDatum(d : Date){
    let datum: string = (d[2]>=10?d[2]:"0"+d[2]) + "-" + (d[1]>=10?d[1]:"0"+d[1]) + "-" + d[0] + ", " + (d[3]>=10?d[3]:"0"+d[3]) + ":" + (d[4]>=10?d[4]:"0"+d[4]);
    return datum;
  }
}
