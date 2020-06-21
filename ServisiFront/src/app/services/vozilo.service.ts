import { VoziloView } from './../models/vozilo-view';
import { Observable } from 'rxjs';
import { PutanjaService } from './../putanje/putanja.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TerminZauzeca } from '../models/termin-zauzeca';
import { Oglas } from '../models/oglas';

@Injectable({
  providedIn: 'root'
})
export class VoziloService {

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

  getOglasiByVoziloId(id: number): Observable<Oglas[]>{
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    return this.http.get<Oglas[]>(this.putanja.get_oglas_url+"/oglas/vozilo/"+id, {headers: headers});
  }
}
