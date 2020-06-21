import { VoziloView } from './../models/vozilo-view';
import { Observable } from 'rxjs';
import { PutanjaService } from './../putanje/putanja.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TerminZauzeca } from '../models/termin-zauzeca';

@Injectable({
  providedIn: 'root'
})
export class VoziloService {
  getVozilaAgentaOglas() :Observable<VoziloView[]>{
    return this.http.get<VoziloView[]>(this.putanja.get_vozilo_url + '/agent/oglas/' + parseInt(localStorage.getItem('userId')));
  }

  constructor(private http: HttpClient, private putanja: PutanjaService) { }

  getVozilaAgenta():Observable<VoziloView[]>{
    return this.http.get<VoziloView[]>(this.putanja.get_vozilo_url + '/agent/' + parseInt(localStorage.getItem('userId')));
  }

  zauzmiTerminZauzecaVozila(termin: TerminZauzeca){
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    return this.http.post(this.putanja.get_termin_url, termin, {headers: headers});
  }
}
