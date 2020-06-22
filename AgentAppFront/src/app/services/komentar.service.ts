import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Komentar } from '../models/komentar';

@Injectable({
  providedIn: 'root'
})
export class KomentarService {

  constructor(private http: HttpClient) { }

  public saveKomentar(komentar:Komentar){
    return this.http.post('http://localhost:8088/komentar',komentar).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }

}
