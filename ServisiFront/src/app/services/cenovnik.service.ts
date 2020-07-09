import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { Cenovnik } from '../models/cenovnik';
import { StavkaCenovnika } from '../models/stavka-cenovnika';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CenovnikService {

 
  constructor(private http:HttpClient,public snackBar: MatSnackBar, private rout: Router) { }

  public saveCenovnik(cenovnik:Cenovnik){
    console.log("Salje li "+cenovnik.naziv+cenovnik.vaziod);
    cenovnik.owner=parseFloat(localStorage.getItem("userId"));
    return this.http.post('https://localhost:8662/oglas/cenovnik',cenovnik).subscribe(
      data => {
        this.snackBar.open('Cenovnik je uspesno kreiran', 'U redu', { duration: 10000 });
        window.location.href = this.rout.url;
      },
      error => {
        this.snackBar.open('nesto je poslo po zlu', 'U redu', { duration: 10000 });
      }
    );
  }

  public getCenovnici() {
    console.log("Pribavljamo vozila");

    return this.http.get('https://localhost:8662/oglas/cenovnik/moji'+"/"+ localStorage.getItem("userId"));
  }

  public saveStavka(stavkaCenovnika:StavkaCenovnika){
    console.log("Saljel "+stavkaCenovnika.cenovnik+stavkaCenovnika.br_dana_popust);
    
    return this.http.post('https://localhost:8662/oglas/stavka',stavkaCenovnika).subscribe(
      data => {
        this.snackBar.open('Cenovnik je uspesno kreiran', 'U redu', { duration: 10000 });
        window.location.href = this.rout.url;
      },
      error => {
        this.snackBar.open('nesto je poslo po zlu', 'U redu', { duration: 10000 });
      }
    );
  }

  public deleteCenovnik(cenovnik) {
    console.log("brisanje cenovnika servis");
    window.location.reload();
    return this.http.delete('https://localhost:8662/oglas/cenovnik' + "/"+ cenovnik.id);
  }

  public izmenaCenovnika(cenovnik:Cenovnik){
    console.log("Menjam cenovnik");
    return this.http.put('https://localhost:8662/oglas/cenovnik',cenovnik,{responseType: 'text'}).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
    
  }

  public deleteStavka(stavka) {
    console.log("brisanje stavke servis");
    window.location.reload();
    return this.http.delete('https://localhost:8662/oglas/stavka' + "/"+ stavka.id);
  }

  public izmenaStavke(stavka:StavkaCenovnika){
    return this.http.put('https://localhost:8662/oglas/stavka',stavka,{responseType: 'text'}).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
    
  }

  public getStavka(oglas:number):  Observable<StavkaCenovnika>{
    console.log("Pribavljamo stavku");

    return this.http.get<StavkaCenovnika>('https://localhost:8662/oglas/stavka/oglas'+"/"+ oglas);
  }

  public getStavke(cenovnik:number):  Observable<StavkaCenovnika>{
    console.log("Pribavljamo stavku");

    return this.http.get<StavkaCenovnika>('https://localhost:8662/oglas/stavka'+"/"+ cenovnik);
  }




}