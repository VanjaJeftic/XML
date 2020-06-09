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
    return this.http.post('http://localhost:8094/klasa/sacuvajKlasu',klasaVozila).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }


  public saveGorivo(tipGoriva:TipGoriva){
    console.log("TipGoriva vozila se salje ")
    return this.http.post('http://localhost:8094/gorivo/sacuvajTipGoriva',tipGoriva).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }

  public saveMenjac(vrstaMenjaca:VrstaMenjaca){
    console.log("TipGoriva vozila se salje ")
    return this.http.post('http://localhost:8094/menjac/sacuvajVrstuMenjaca',vrstaMenjaca).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }

 public getMarkeVozila() {
    console.log("Pribavljamo marke");
    return this.http.get('http://localhost:8094/marka/markeVozila');
  }


  public getKlaseVozila() {
    console.log("Pribavljamo klase vozila");
    return this.http.get('http://localhost:8094/klasa/klaseVozila');
  }

  public getVrsteMenjacaVozila() {
    console.log("Pribavljamo menjace vozila");
    return this.http.get('http://localhost:8094/menjac/vrsteMenjacaVozila');
  }

  public getTipoviGorivaVozila() {
    console.log("Pribavljamo tipove goriva vozila");
    return this.http.get('http://localhost:8094/gorivo/tipoviGorivaVozila');
  }

  public getModelVozila() {
    console.log("Pribavljamo modele");
    return this.http.get('http://localhost:8094/model/modeliVozila');
  }

  public deleteModel(model) {
    console.log("brisanje modela servis");
    window.location.reload();
    return this.http.delete('http://localhost:8094/model/brisanjeModela' + "/"+ model.id);
  }

  public deleteMarka(marka) {
    console.log("brisanje marke servis");
    window.location.reload();
    return this.http.delete('http://localhost:8094/marka/brisanjeMarke' + "/"+ marka.id);
  }

  public deleteTipGoriva(gorivo) {
    console.log("brisanje goriva servis");
    window.location.reload();
    return this.http.delete('http://localhost:8094/gorivo/brisanjeGoriva' + "/"+ gorivo.id);
  }

  public deleteVrsteMenjaca(menjac) {
    console.log("brisanje menjaca servis");
    window.location.reload();
    return this.http.delete('http://localhost:8094/menjac/brisanjeVrsteMenjaca' + "/"+ menjac.id);
  }

  public deleteKlaseVozila(klasa) {
    console.log("brisanje klase vozila servis");
    window.location.reload();
    return this.http.delete('http://localhost:8094/klasa/brisanjeKlase' + "/"+ klasa.id);
  }

  public saveModel(modelVozila:ModelVozila){
    console.log("ModelVozila  se salje ")
    return this.http.post('http://localhost:8094/model/sacuvajModel',modelVozila).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }


}
