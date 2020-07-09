import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Vozilo } from '../models/vozilo';

@Injectable({
  providedIn: 'root'
})
export class SvaVozilaService {
 

  constructor(private http:HttpClient) { }

  public getVozila() {
    console.log("Pribavljamo vozila"+localStorage.getItem('userId'));
    return this.http.get('https://localhost:8662/oglas/vozilo/vozila/'+localStorage.getItem('userId'));
  }

  public izmenaVozila(vozilo:Vozilo){
    console.log("Menjam vozilo");
    return this.http.put('https://localhost:8662/oglas/vozilo',vozilo,{responseType: 'text'}).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
    
  }

  
  public deleteVozilo(vozilo) {
    console.log("brisanje vozila servis");
    window.location.reload();
    return this.http.delete('https://localhost:8662/oglas/vozilo' + "/"+ vozilo.id);
  }

}
