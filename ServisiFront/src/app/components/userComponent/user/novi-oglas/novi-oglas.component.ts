import { Component, OnInit } from '@angular/core';
import {Vozilo} from 'src/app/models/vozilo';
import { NoviOglasService } from 'src/app/services/novi-oglas.service';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';


@Component({
  selector: 'novi-oglas',
  templateUrl: './novi-oglas.component.html',
  styleUrls: ['./novi-oglas.component.css']
})
export class NoviOglasComponent implements OnInit {

  public vozilo:Vozilo=new Vozilo();
  
  constructor(private noviOglasService:NoviOglasService,private authService: AuthenticationService, private router: Router) { }
  
  imageName: any;
  selectedFile: File;
  retrievedImage: any;
  message: string;
  urls=[];
  selectedFiles = [];

  ngOnInit() {
  }

  onSelect(e){
    window.alert("Uspesno ste kreirali vozilo");
    if(e.target.files){
      for(let i=0; i<e.target.files.length; i++){
        let file = e.target.files[i];
        this.selectedFiles.push(file);
        let reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = (events:any) => {
          this.urls.push(events.target.result)
        }
        
      }
    }
  }


  public onSubmit(): void{
    event.preventDefault();
    console.log("Usao u onsubmit"+ this.vozilo.modelVozila );
    console.log(this.selectedFiles);
    // const uploadImageData = new FormData();
    // uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
    let res = this.noviOglasService.saveVozilo(this.vozilo, this.selectedFiles);
    console.log();
  }

  onOdjaviMe(){
    window.localStorage.clear();
    this.authService.logout();
  }

  shopCart(){
    this.router.navigateByUrl('cart');
  }

  onVozilo(){
    this.router.navigateByUrl('vozilo/novoVozilo');
  }

  onMojaVozila(){
    this.router.navigateByUrl('svavozila');
  }


}
