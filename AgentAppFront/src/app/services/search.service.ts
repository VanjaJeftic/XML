import { Injectable } from '@angular/core';
import { PutanjaService } from '../putanje/putanja.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Search } from '../models/search.model';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private putanja: PutanjaService, private http: HttpClient) { }

  pretrazi(search:Search){
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    return this.http.post(this.putanja.get_search_url, search, {headers: headers});
  }
}
