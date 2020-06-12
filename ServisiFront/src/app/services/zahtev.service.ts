import { ZahtevBundleViewDTO } from './../models/zahtev-bundle-view-dto';
import { Observable } from 'rxjs';
import { PutanjaService } from './../putanje/putanja.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ZahtevService {

  constructor(private http: HttpClient, private putanje: PutanjaService) { }

  rezervisi(shoppingCart){
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    return this.http.post(this.putanje.get_zahtev_url, shoppingCart, {headers: headers});
  }

  getZahtevi():Observable<ZahtevBundleViewDTO[]>{
    return this.http.get<ZahtevBundleViewDTO[]>('http://localhost:8662/zahtev/' + parseInt(localStorage.getItem('userId')));
  }

  prihvatiZahtev(bundleID: number){
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    return this.http.post('http://localhost:8662/zahtev/accept/' + bundleID + '/' + parseInt(localStorage.getItem('userId')), {headers: headers});
  }
}
