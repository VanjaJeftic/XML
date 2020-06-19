import { Injectable } from '@angular/core';
import { ZahtevBundleViewDTO } from '../models/zahtev-bundle-view-dto';
import { Observable } from 'rxjs';
import { PutanjaService } from '../putanje/putanja.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PorukaZahtevService {

  constructor(private putanja: PutanjaService, private http: HttpClient) { }

  getZahtevi():Observable<ZahtevBundleViewDTO[]>{
    return this.http.get<ZahtevBundleViewDTO[]>(this.putanja.get_poruke_zahtevi_url);
  }

  getZahteviI():Observable<ZahtevBundleViewDTO[]>{
    return this.http.get<ZahtevBundleViewDTO[]>(this.putanja.get_poruke_zahtevi_poruke_url);
  }
}
