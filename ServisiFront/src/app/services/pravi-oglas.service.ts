import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Oglas } from '../models/oglas';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class PraviOglasService {

  constructor(private http:HttpClient,public snackBar: MatSnackBar, private rout: Router) { }

  public saveOglas(oglas:Oglas){
    console.log("Saljel "+oglas.mesto+oglas.cena+oglas.slobodanod)
    return this.http.post('https://localhost:8662/oglas/create',oglas).subscribe(
      data => {
        this.snackBar.open('Oglas je uspesno kreiran', 'U redu', { duration: 10000 });
        window.location.href = this.rout.url;
      },
      error => {
        this.snackBar.open('Maksimalno Vam je dozvoljeno kreiranje tri oglasa', 'U redu', { duration: 10000 });
      }
    );
  }

  
  public getOglasi() {
    console.log("Pribavljamo oglase");
    return this.http.get('https://localhost:8662/oglas/oglasi'+"/"+localStorage.getItem("userId"));
  }

  public deleteOglas(oglas) {
    console.log("brisanje oglasa servis");
    window.location.reload();
    return this.http.delete('https://localhost:8662/oglas/oglas' + "/"+ oglas.id);
  }

  public izmenaOglasa(oglas:Oglas){
    return this.http.put('https://localhost:8662/oglas/oglas',oglas,{responseType: 'text'}).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
    
  }
}