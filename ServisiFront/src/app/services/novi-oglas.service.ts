import { Injectable } from '@angular/core';
import {Vozilo} from './../models/vozilo'
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class NoviOglasService {

  vozilo2:Vozilo=new Vozilo();
  constructor(private http:HttpClient) { }

  public saveVozilo(vozilo:Vozilo){
    console.log("save"+ vozilo.modelVozila +vozilo.tipGoriva + vozilo.vrstaMenjaca);
    return this.http.post('http://localhost:8092/vozilo/novoVozilo',vozilo).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }
}
