import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Oglas } from '../models/oglas';

@Injectable({
  providedIn: 'root'
})
export class PraviOglasService {

  constructor(private http:HttpClient) { }

  public saveOglas(oglas:Oglas){
    return this.http.post('http://localhost:8092/create',oglas);
  }
}
