import { PutanjaService } from './../putanje/putanja.service';
import { Oglas } from './../models/oglas';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OglasService {

  constructor(private http: HttpClient, private putanje: PutanjaService) { }

  getAllOglasi():Observable<Oglas[]>{
    return this.http.get<Oglas[]>(this.putanje.get_oglas_url);
  }

  getOneOglas(id: number): Observable<Oglas>{
    return this.http.get<Oglas>(this.putanje.get_oglas_url + '/' + id);
  }
}
