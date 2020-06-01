import { OglasView } from './../models/oglas-view';
import { Observable } from 'rxjs';
import { PutanjaService } from './../putanje/putanja.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OglasService {

  constructor(private http: HttpClient, private putanja: PutanjaService) { }

  getOneOglas(id: number): Observable<OglasView>{
    return this.http.get<OglasView>(this.putanja.get_oglas_url + '/' + id);
  }
}
