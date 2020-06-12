import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Oglas } from '../models/oglas';

@Injectable({
  providedIn: 'root'
})
export class NoviOglasService {

  constructor(private http:HttpClient) { }

  public saveOglas(oglas:Oglas){
    console.log("Saljel "+oglas.mesto+oglas.cena+oglas.slobodanOd)
    return this.http.post('https://localhost:8088/oglas/novi',oglas).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }
}