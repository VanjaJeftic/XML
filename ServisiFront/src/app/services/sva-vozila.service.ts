import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SvaVozilaService {
 

  constructor(private http:HttpClient) { }

  public getVozila() {
    console.log("Pribavljamo vozila"+localStorage.getItem('userId'));
    return this.http.get('http://localhost:8092/vozilo/vozila/'+localStorage.getItem('userId'));
  }
}
