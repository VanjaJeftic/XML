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
}