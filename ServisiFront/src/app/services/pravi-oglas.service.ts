import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Oglas } from '../models/oglas';

@Injectable({
  providedIn: 'root'
})
export class PraviOglasService {

  constructor(private http:HttpClient) { }

  public saveOglas(oglas:Oglas){
    console.log("Saljel "+oglas.mesto+oglas.cena+oglas.slobodanod)
    return this.http.post('https://localhost:8662/oglas/create',oglas).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }
}
