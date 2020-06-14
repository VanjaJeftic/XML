import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SvaVozilaService {
 

  constructor(private http:HttpClient) { }

  public getVozila() {
    console.log("Pribavljamo vozila"+localStorage.getItem('userId'));
    return this.http.get('https://localhost:8662/oglas/vozilo/vozila/'+localStorage.getItem('userId'));
  }
}
