import { Observable } from 'rxjs';
import { ZahtevBundleViewDTO } from './../models/zahtev-bundle-view-dto';
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
    return this.http.put(this.putanja.get_zahtev_url, shoppingCart, {headers: headers});
  }

  getZahtevi():Observable<ZahtevBundleViewDTO[]>{
    return this.http.get<ZahtevBundleViewDTO[]>(this.putanja.get_zahtev_url);
  }

  prihvatiZahtev(bundleID: number){
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    return this.http.post(this.putanja.get_zahtev_url + '/' + bundleID, {headers: headers});
  }

  getZahteviIzvestaj():Observable<ZahtevBundleViewDTO[]>{
    return this.http.get<ZahtevBundleViewDTO[]>(this.putanja.get_izvestaj_url);
  }
}
