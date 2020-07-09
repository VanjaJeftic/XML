import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Vozilo } from '../models/vozilo';

@Injectable({
  providedIn: 'root'
})
export class SvaVozilaService {

  constructor(private http:HttpClient) { }

  public getVozila() {
    console.log("Pribavljamo vozila");
    return this.http.get('http://localhost:8088/vozilo');
  }

  
  public deleteVozilo(vozilo) {
    console.log("brisanje vozila servis");
    window.location.reload();
    return this.http.delete('http://localhost:8088/vozilo' + "/"+ vozilo.id);
  }

  public izmenaVozila(vozilo:Vozilo){
    return this.http.put('http://localhost:8088/vozilo',vozilo,{responseType: 'text'}).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
    
  }
}

