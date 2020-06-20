import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Komentar } from '../models/komentar.model';
import { PutanjaService } from '../putanje/putanja.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class KomentarService {

  constructor(private http: HttpClient, private url : PutanjaService) { }

  public saveKomentar(komentar:Komentar): Observable<Komentar>{
    return this.http.post<Komentar>(this.url.get_komentar_url,komentar);
  }
}
