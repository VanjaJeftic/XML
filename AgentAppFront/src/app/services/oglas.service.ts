import { PutanjaService } from './../putanje/putanja.service';
import { Oglas } from './../models/oglas';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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

  nadjiCeoOglas(oglas: Oglas): Observable<Oglas>{
    return this.http.get<Oglas>(this.putanje.get_oglas_url + '/oglas/' + oglas);
  }

  dodajIzvestaj(izvestaj){
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    return this.http.put(this.putanje.get_izvestaj_url, izvestaj, {headers: headers});
  }

  getSlika(id: number){
    return this.http.get(this.putanje.get_vozilo_url + '/image/' + id);
  }

}
