import { TerminZauzeca } from './../models/termin-zauzeca';
import { Vozilo } from './../models/vozilo';
import { Observable } from 'rxjs';
import { PutanjaService } from './../putanje/putanja.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class VoziloService {

  constructor(private http: HttpClient, private putanja: PutanjaService) { }

  getVozilaAgenta():Observable<Vozilo[]>{
    return this.http.get<Vozilo[]>(this.putanja.get_vozilo_url + '/vozila');
  }

  zauzmiTerminZauzecaVozila(termin: TerminZauzeca){
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    return this.http.put(this.putanja.get_termin_url, termin, {headers: headers});
  }
}
