import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PutanjaService } from '../putanje/putanja.service';
import { ZahtevBundleViewDTO } from '../models/zahtev-bundle-view-dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PorukaService {


  constructor(private http: HttpClient, private putanje: PutanjaService) { }

  dodajPoruku(poruka){
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    return this.http.post(this.putanje+  poruka, {headers: headers});
  }

  getAllPoruke(zahtevId: any) {
    console.log("zahtevId="+zahtevId);
    return this.http.get('https://localhost:8662/message/poruka/' + zahtevId);
  }

  getZahteviPoruke():Observable<ZahtevBundleViewDTO[]>{
    return this.http.get<ZahtevBundleViewDTO[]>('https://localhost:8662/zahtev/poruke/' + parseInt(localStorage.getItem('userId')));
  }
}
