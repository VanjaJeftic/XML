import { Injectable } from '@angular/core';
import { PutanjaService } from '../putanje/putanja.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Oglas } from '../models/oglas';
import { Search } from '../models/search.model';
import { Observable } from 'rxjs';
import { SearchView } from '../models/search-view.model';
import { UserView } from '../models/user-view.model';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private putanja: PutanjaService, private http: HttpClient) { }

  pretrazi(search:SearchView): Observable<Search[]>{
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    return this.http.post<Search[]>(this.putanja.get_search_url+'/getSearched', search, {headers: headers});
  }

  getAll(): Observable<Search[]>{
    return this.http.get<Search[]>(this.putanja.get_search_url+'/getAll');
  }

  getUserById(id:number): Observable<UserView>{
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    return this.http.post<UserView>(this.putanja.get_search_url+'/userById',id, {headers: headers});
  }
}
