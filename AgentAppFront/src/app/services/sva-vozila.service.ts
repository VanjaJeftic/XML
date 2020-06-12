import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SvaVozilaService {

  constructor(private http:HttpClient) { }

  public getVozila() {
    console.log("Pribavljamo vozila");
    return this.http.get('https://localhost:8088/vozilo/vozila/');
  }
}

