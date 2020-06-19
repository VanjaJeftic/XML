import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PutanjaService } from '../putanje/putanja.service';

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
    return this.http.post(this.putanje.get_poruka_url, poruka, {headers: headers});
  }

  getAllPoruke(zahtevId: any) {
    return this.http.get(this.putanje.get_poruka_url + "/" + zahtevId);
  }
}
