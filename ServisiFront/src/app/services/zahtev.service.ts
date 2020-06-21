import { ZahtevBundleViewDTO } from './../models/zahtev-bundle-view-dto';
import { Observable } from 'rxjs';
import { PutanjaService } from './../putanje/putanja.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Zahtev } from '../models/zahtev';

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
    return this.http.get<ZahtevBundleViewDTO[]>('https://localhost:8662/zahtev/' + parseInt(localStorage.getItem('userId')));
  }

  prihvatiZahtev(bundleID: number){
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    return this.http.post('https://localhost:8662/zahtev/accept/' + bundleID + '/' + parseInt(localStorage.getItem('userId')), {headers: headers});
  }

  getZahteviIzvestaj():Observable<ZahtevBundleViewDTO[]>{
    return this.http.get<ZahtevBundleViewDTO[]>('https://localhost:8662/zahtev/izvestaj/' + parseInt(localStorage.getItem('userId')));
  }

  getZahteviPodnosioca():Observable<Zahtev[]>{
    return this.http.get<Zahtev[]>('https://localhost:8662/zahtev/zahtevi-podnosioca/' + parseInt(localStorage.getItem('userId')));
  }
}
