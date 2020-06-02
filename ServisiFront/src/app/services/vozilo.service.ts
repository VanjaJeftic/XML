import { VoziloView } from './../models/vozilo-view';
import { Observable } from 'rxjs';
import { PutanjaService } from './../putanje/putanja.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class VoziloService {

  constructor(private http: HttpClient, private putanja: PutanjaService) { }

  getVozilaAgenta():Observable<VoziloView[]>{
    return this.http.get<VoziloView[]>(this.putanja.get_vozilo_url);
  }
}
