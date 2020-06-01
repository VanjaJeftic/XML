import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PutanjaService } from './../putanje/putanja.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ZahtevService {

  constructor(private putanja: PutanjaService, private http: HttpClient) { }

  rezervisi(shoppingCart){
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    return this.http.post(this.putanja.get_zahtev_url, shoppingCart, {headers: headers});
  }
}
