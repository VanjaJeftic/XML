import { Injectable } from '@angular/core';
import { PutanjaService } from '../putanje/putanja.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Oglas } from '../models/oglas';
import { Search } from '../models/search.model';
import { Observable } from 'rxjs';
import { SearchView } from '../models/search-view.model';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private putanja: PutanjaService, private http: HttpClient) { }

  pretrazi(search:SearchView): Observable<Search[]>{
    // const fd = new FormData();
    // fd.append('mesto', mesto);
    // fd.append('datumi', datum);
    // fd.append('marka', marka);
    // fd.append('model', model);
    // fd.append('mincena', minCena);
    // fd.append('maxcena', maxCena);
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    return this.http.post<Search[]>(this.putanja.get_search_url+'/getSearched', search, {headers: headers});
  }

  getAll(): Observable<Search[]>{
    return this.http.get<Search[]>(this.putanja.get_search_url+'/getAll');
  }
}
