import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ModelVozila } from '../models/model-vozila';
import { KlasaVozila } from '../models/klasa-vozila';
import { TipGoriva } from '../models/tip-goriva';
import { VrstaMenjaca } from '../models/vrsta-menjaca';
import { MarkaVozila } from '../models/marka-vozila';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }


  public aktivirajNalog(id: number){
    return this.http.post('https://localhost:8088/auth/aktivirajNalog', id);
  }

  public getUserPoId(id:number):Observable<User>{
    console.log("Pribavljamo korisnika");
    return this.http.get<User>('https://localhost:8088/auth/korisnik/'+id);
  }
  
  public izmenaPoljaOdobrenKomentar(id:number){
    window.location.reload();
    return this.http.put('https://localhost:8088/komentar/odobren/'+id,{responseType: 'text'}).subscribe(
   
      data=>{console.log('Vratio je '+data)});
  }


  public izmenaPoljaOdbijenKomentar(id:number){
    window.location.reload();
    return this.http.put('https://localhost:8088/komentar/odbijen/'+id,{responseType: 'text'}).subscribe(
   
      data=>{console.log('Vratio je '+data)});
  }
  




  public saveMarka(markaVozila:MarkaVozila){
    return this.http.post('https://localhost:8088/marka',markaVozila).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
    
  }


  public izmenaMarke(markaVozila:MarkaVozila){
    return this.http.put('https://localhost:8088/marka',markaVozila,{responseType: 'text'}).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
    
  }

  public izmenaKlase(klasaVozila:KlasaVozila){
    return this.http.put('https://localhost:8088/klasa',klasaVozila,{responseType: 'text'}).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
    
  }


  public izmenaTipaGoriva(tipGoriva:TipGoriva){
    return this.http.put('https://localhost:8088/gorivo',tipGoriva,{responseType: 'text'}).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }

  public izmenaVrsteMenjaca(vrstaMenjaca:VrstaMenjaca){
    return this.http.put('https://localhost:8088/menjac',vrstaMenjaca,{responseType: 'text'}).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }

  public izmenaModelaVozila(modelVozila:ModelVozila){
    console.log("id marke je "+modelVozila.id_marke)
    return this.http.put('https://localhost:8088/model',modelVozila,{responseType: 'text'}).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }

  public saveKlasa(klasaVozila:KlasaVozila){
    return this.http.post('https://localhost:8088/klasa',klasaVozila).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }


  public saveGorivo(tipGoriva:TipGoriva){
    return this.http.post('https://localhost:8088/gorivo',tipGoriva).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }

  public saveMenjac(vrstaMenjaca:VrstaMenjaca){
    return this.http.post('https://localhost:8088/menjac',vrstaMenjaca).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }

 public getMarkeVozila() {
    console.log("Pribavljamo marke");
    return this.http.get('https://localhost:8088/marka');
  }


  public getKomentari() {
    console.log("Pribavljamo komentare");
    return this.http.get('https://localhost:8088/komentar');
  }
  
  
  public getKomentariOdbijeni() {
    console.log("Pribavljamo odbijene komentare");
    return this.http.get('https://localhost:8088/komentar/sviOdbijeni');
  }

  public getKomentariOdobreni() {
    console.log("Pribavljamo odobrene komentare");
    return this.http.get('https://localhost:8088/komentar/sviOdobreni');
  }

  public getKlaseVozila() {
    console.log("Pribavljamo klase vozila");
    return this.http.get('https://localhost:8088/klasa');
  }

  public getVrsteMenjacaVozila() {
    console.log("Pribavljamo menjace vozila");
    return this.http.get('https://localhost:8088/menjac');
  }

  public getTipoviGorivaVozila() {
    console.log("Pribavljamo tipove goriva vozila");
    return this.http.get('https://localhost:8088/gorivo');
  }

  public getModelVozila() {
    console.log("Pribavljamo modele");
    return this.http.get('https://localhost:8088/model');
  }

  public deleteModel(model) {
    console.log("brisanje modela servis");
    window.location.reload();
    return this.http.delete('https://localhost:8088/model' + "/"+ model.id);
  }

  public deleteMarka(marka) {
    console.log("brisanje marke servis");
    window.location.reload();
    return this.http.delete('https://localhost:8088/marka' + "/"+ marka.id);
  }

 
  public deleteTipGoriva(gorivo) {
    console.log("brisanje goriva servis");
    window.location.reload();
    return this.http.delete('https://localhost:8088/gorivo' + "/"+ gorivo.id);
  }

  public deleteVrsteMenjaca(menjac) {
    console.log("brisanje menjaca servis");
    window.location.reload();
    return this.http.delete('https://localhost:8088/menjac' + "/"+ menjac.id);
  }

  public deleteKlaseVozila(klasa) {
    console.log("brisanje klase vozila servis");
    window.location.reload();
    return this.http.delete('https://localhost:8088/klasa' + "/"+ klasa.id);
  }

  public saveModel(modelVozila:ModelVozila){
    console.log("ModelVozila  se salje ")
    return this.http.post('https://localhost:8088/model',modelVozila).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }



}
