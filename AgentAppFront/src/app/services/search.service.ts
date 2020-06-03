import { Injectable } from '@angular/core';
import { PutanjaService } from '../putanje/putanja.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Oglas } from '../models/oglas';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private putanja: PutanjaService, private http: HttpClient) { }

  pretrazi(oglas:Oglas){
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    return this.http.post(this.putanja.get_search_url, oglas, {headers: headers});
  }
}
