import { Injectable } from '@angular/core';
import { PutanjaService } from '../putanje/putanja.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Oglas } from '../models/oglas';
import { Search } from '../models/search.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private putanja: PutanjaService, private http: HttpClient) { }

  pretrazi(mesto:string,minCena: string, maxCena:string,datum:string,model:string,marka:string){
    const fd = new FormData();
    fd.append('mesto', mesto);
    fd.append('datumi', datum);
    fd.append('marka', marka);
    fd.append('model', model);
    fd.append('mincena', minCena);
    fd.append('maxcena', maxCena);
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    return this.http.post(this.putanja.get_search_url, fd, {headers: headers});
  }

  getAll(): Observable<Search[]>{
    return this.http.get<Search[]>(this.putanja.get_search_url);
  }
}
