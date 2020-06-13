import { OglasView } from './../models/oglas-view';
import { Observable } from 'rxjs';
import { PutanjaService } from './../putanje/putanja.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OglasService {

  constructor(private http: HttpClient, private putanja: PutanjaService) { }

  getOneOglas(id: number): Observable<OglasView>{
    console.log(this.putanja.get_oglas_url + '/' + id)
    return this.http.get<OglasView>(this.putanja.get_oglas_url + '/' + id);
  }

  dodajIzvestaj(izvestaj){
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    return this.http.put('http://localhost:8662/oglas/izvestaj', izvestaj, {headers: headers});
   }
}
