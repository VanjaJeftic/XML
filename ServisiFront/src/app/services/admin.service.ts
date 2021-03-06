import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PutanjaService } from '../putanje/putanja.service';
import { MarkaVozila } from '../models/marka-vozila';
import { KlasaVozila } from '../models/klasa-vozila';
import { TipGoriva } from '../models/tip-goriva';
import { VrstaMenjaca } from '../models/vrsta-menjaca';
import { ModelVozila } from '../models/model-vozila';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { Agent } from '../models/agent';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

 
 
  constructor(private http:HttpClient) { }

  public saveMarka(markaVozila:MarkaVozila){
    return this.http.post('https://localhost:8662/admin/marka',markaVozila).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
    
  }


  public izmenaMarke(markaVozila:MarkaVozila){
    return this.http.put('https://localhost:8662/admin/marka',markaVozila,{responseType: 'text'}).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
    
  }

  public izmenaKlase(klasaVozila:KlasaVozila){
    return this.http.put('https://localhost:8662/admin/klasa',klasaVozila,{responseType: 'text'}).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
    
  }


  public izmenaTipaGoriva(tipGoriva:TipGoriva){
    return this.http.put('https://localhost:8662/admin/gorivo',tipGoriva,{responseType: 'text'}).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }

  public izmenaVrsteMenjaca(vrstaMenjaca:VrstaMenjaca){
    return this.http.put('https://localhost:8662/admin/menjac',vrstaMenjaca,{responseType: 'text'}).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }

  public izmenaModelaVozila(modelVozila:ModelVozila){
    console.log("id marke je "+modelVozila.id_marke)
    return this.http.put('https://localhost:8662/admin/model',modelVozila,{responseType: 'text'}).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }

  public saveKlasa(klasaVozila:KlasaVozila){
    return this.http.post('https://localhost:8662/admin/klasa',klasaVozila).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }


  public saveGorivo(tipGoriva:TipGoriva){
    return this.http.post('https://localhost:8662/admin/gorivo',tipGoriva).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }

  public saveMenjac(vrstaMenjaca:VrstaMenjaca){
    return this.http.post('https://localhost:8662/admin/menjac',vrstaMenjaca).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }

 public getMarkeVozila() {
    console.log("Pribavljamo marke");
    return this.http.get('https://localhost:8662/admin/marka');
  }


  public getKlaseVozila() {
    console.log("Pribavljamo klase vozila");
    return this.http.get('https://localhost:8662/admin/klasa');
  }

  public getVrsteMenjacaVozila() {
    console.log("Pribavljamo menjace vozila");
    return this.http.get('https://localhost:8662/admin/menjac');
  }

  public getTipoviGorivaVozila() {
    console.log("Pribavljamo tipove goriva vozila");
    return this.http.get('https://localhost:8662/admin/gorivo');
  }

  public getModelVozila() {
    console.log("Pribavljamo modele");
    return this.http.get('https://localhost:8662/admin/model');
  }

  public deleteModel(model) {
    console.log("brisanje modela servis");
    window.location.reload();
    return this.http.delete('https://localhost:8662/admin/model' + "/"+ model.id);
  }

  public deleteMarka(marka) {
    console.log("brisanje marke servis");
    window.location.reload();
    return this.http.delete('https://localhost:8662/admin/marka' + "/"+ marka.id);
  }

 
  public deleteTipGoriva(gorivo) {
    console.log("brisanje goriva servis");
    window.location.reload();
    return this.http.delete('https://localhost:8662/admin/gorivo' + "/"+ gorivo.id);
  }

  public deleteVrsteMenjaca(menjac) {
    console.log("brisanje menjaca servis");
    window.location.reload();
    return this.http.delete('https://localhost:8662/admin/menjac' + "/"+ menjac.id);
  }

  public deleteKlaseVozila(klasa) {
    console.log("brisanje klase vozila servis");
    window.location.reload();
    return this.http.delete('https://localhost:8662/admin/klasa' + "/"+ klasa.id);
  }

  public saveModel(modelVozila:ModelVozila){
    console.log("ModelVozila  se salje ")
    return this.http.post('https://localhost:8662/admin/model',modelVozila).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }

//odavde su za komentar
  public getUserPoId(idkorisnik:number):Observable<User>{
    console.log("Pribavljamo korisnika id je : " + idkorisnik);
    return this.http.get<User>('https://localhost:8662/auth/korisnik/korisnik/'+idkorisnik);
  }
  
  public izmenaPoljaOdobrenKomentar(id:number){
    window.location.reload();
    return this.http.put('https://localhost:8662/komentar/komentar/odobren/'+id,{responseType: 'text'}).subscribe(
   
      data=>{console.log('Vratio je '+data)});
  }

  public registrujAgenta(agent: Agent){
    console.log("registrovanje agenta: "+ agent.username);
    return this.http.post('https://localhost:8662/auth/korisnik/noviAgent', agent);
  }

  
  public registrujFirmu(agent: Agent){
    console.log("registrovanje firme: "+ agent.username);
    return this.http.post('https://localhost:8662/auth/korisnik/novaFirma', agent);
  }

  public izmenaPoljaOdbijenKomentar(id:number){
    window.location.reload();
    return this.http.put('https://localhost:8662/komentar/komentar/odbijen/'+id,{responseType: 'text'}).subscribe(
   
      data=>{console.log('Vratio je '+data)});
  }

  public getKomentari() {
    console.log("Pribavljamo komentare");
    return this.http.get('https://localhost:8662/komentar/komentar');
  }
  

  
  public getOdobreniKomZaovajOglas(oglasid:number) {
    console.log("Pribavljamo komentare za oglas");
    return this.http.get('https://localhost:8662/komentar/komentar/odobreniKomentariZaOglas/'+oglasid);
  }
  
  public getKomentariOdbijeni() {
    console.log("Pribavljamo odbijene komentare");
    return this.http.get('https://localhost:8662/komentar/komentar/sviOdbijeni');
  }

  public getKomentariOdobreni() {
    console.log("Pribavljamo odobrene komentare");
    return this.http.get('https://localhost:8662/komentar/komentar/sviOdobreni');
  }

  
  public getKorisnici() {
    console.log("Pribavljamo korisnike");
    return this.http.get('https://localhost:8662/auth/korisnik/korisnici');
  }

  
  public deleteKorisnik(user) {
    console.log("brisanje korisnika servis");
    window.location.reload();
    return this.http.delete('https://localhost:8662/admin/user/delete' + "/"+ user.id);
  }

  public blok(user) {
    console.log("blokitanje korisnika servis");
    window.location.reload();
    return this.http.get('https://localhost:8662/auth/korisnik/blokirajUsera' + "/"+ user.id);
  }

  public unblok(user) {
    console.log("odblokiranje korisnika servis");
    window.location.reload();
    return this.http.get('https://localhost:8662/auth/korisnik/odblokirajUsera' + "/"+ user.id);
  }




}
