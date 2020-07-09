import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { Cenovnik } from '../models/cenovnik';
import { StavkaCenovnika } from '../models/stavka-cenovnika';

@Injectable({
  providedIn: 'root'
})
export class CenovnikService {

 
  constructor(private http:HttpClient,public snackBar: MatSnackBar, private rout: Router) { }

  public saveCenovnik(cenovnik:Cenovnik){
    console.log("Saljel "+cenovnik.naziv+cenovnik.vaziod)
    return this.http.post('http://localhost:8088/cenovnik',cenovnik).subscribe(
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
    return this.http.get('http://localhost:8088/cenovnik');
  }

  public saveStavka(stavkaCenovnika:StavkaCenovnika){
    console.log("Saljel "+stavkaCenovnika.cenovnik+stavkaCenovnika.br_dana_popust)
    return this.http.post('http://localhost:8088/stavka',stavkaCenovnika).subscribe(
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
    return this.http.delete('http://localhost:8088/cenovnik' + "/"+ cenovnik.id);
  }

  public izmenaOglasa(cenovnik:Cenovnik){
    return this.http.put('http://localhost:8088/cenovnik',cenovnik,{responseType: 'text'}).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
    
  }

  public deleteStavka(stavka) {
    console.log("brisanje stavke servis");
    window.location.reload();
    return this.http.delete('http://localhost:8088/stavka' + "/"+ stavka.id);
  }

  public izmenaStavke(stavka:StavkaCenovnika){
    return this.http.put('http://localhost:8088/stavka',stavka,{responseType: 'text'}).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
    
  }


}