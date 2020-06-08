import { Injectable } from '@angular/core';
import {Vozilo} from './../models/vozilo'
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class NoviOglasService {
  
  message: string;
  vozilo2:Vozilo=new Vozilo();
  constructor(private http:HttpClient) { }

  public saveVozilo(vozilo, selectedFiles){
    console.log("save"+ vozilo.modelVozila +vozilo.tipGoriva + vozilo.vrstaMenjaca);
    console.log(selectedFiles);
    const fd = new FormData();
    console.log("Pre metode");
    selectedFiles.forEach(image => {
      fd.append('image', image, image.name);
    });
    console.log("Pre slanja vozila");
    fd.append('vozilomarka', vozilo.markaVozila);
    fd.append('vozilomodel', vozilo.modelVozila);
    fd.append('voziloklasa', vozilo.klasaVozila);
    fd.append('vozilomenjac', vozilo.vrstaMenjaca);
    fd.append('vozilogorivo', vozilo.tipGoriva);
    fd.append('vozilokm', vozilo.predjeniKm);
    fd.append('vozilosedista', vozilo.brsedistadeca);
    fd.append("username",localStorage.getItem("username"));
    fd.append("userId",localStorage.getItem("userId"));
    console.log("Pre slanja");
    return this.http.post('http://localhost:8662/oglas/vozilo/novoVozilo',fd,{ observe: 'response' }).subscribe((response) => {
              if (response.status === 200) {
    
                this.message = 'Image uploaded successfully';
                
              } else {
      
                this.message = 'Image not uploaded successfully';
      
              }
      
            }
      
            );
    
  }
}
