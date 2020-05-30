import { Injectable } from '@angular/core';
import {Vozilo} from './../models/vozilo'
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class NoviOglasService {

  constructor(private http:HttpClient) { }

  public saveVozilo(vozilo:Vozilo){
    console.log("save"+ vozilo.modelVozila);
    return this.http.post('http://localhost:8092/novoVozilo',vozilo).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }
}
