import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PutanjaService } from '../putanje/putanja.service';
import { MarkaVozila } from '../models/marka-vozila';
import { KlasaVozila } from '../models/klasa-vozila';
import { TipGoriva } from '../models/tip-goriva';
import { VrstaMenjaca } from '../models/vrsta-menjaca';
import { ModelVozila } from '../models/model-vozila';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
 
 
  constructor(private http:HttpClient) { }

  public saveMarka(markaVozila:MarkaVozila){
    console.log("Marka vozila se salje ")
    return this.http.post('http://localhost:8094/marka/novaMarka',markaVozila).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }


  public saveKlasa(klasaVozila:KlasaVozila){
    console.log("Klasa vozila se salje ")
    return this.http.post('http://localhost:8094/klasa/novaKlasa',klasaVozila).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }


  public saveGorivo(tipGoriva:TipGoriva){
    console.log("TipGoriva vozila se salje ")
    return this.http.post('http://localhost:8094/gorivo/noviTipGoriva',tipGoriva).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }

  public saveMenjac(vrstaMenjaca:VrstaMenjaca){
    console.log("TipGoriva vozila se salje ")
    return this.http.post('http://localhost:8094/menjac/novaVrstaMenjaca',vrstaMenjaca).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }

  getMarkeVozila() {
    //return this.list('/http://localhost:8094/marka/markeVozila').snapshotChanges();
    console.log("Pribavljamo marke")
    //return this.http.get('http://localhost:8094/marka/markeVozila');
    return this.http.get('http://localhost:8094/marka/markeVozila');

    console.log("Stigle marke")
  }

  public saveModel(modelVozila:ModelVozila){
    console.log("ModelVozila  se salje ")
    return this.http.post('http://localhost:8094/model/sacuvajModel',modelVozila).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }


}
