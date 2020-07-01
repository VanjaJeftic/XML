import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';
import { Router } from '@angular/router';
import { TipGoriva } from 'src/app/models/tip-goriva';
import { VrstaMenjaca } from 'src/app/models/vrsta-menjaca';
import { ModelVozila } from 'src/app/models/model-vozila';
import { MarkaVozila } from 'src/app/models/marka-vozila';
import { KlasaVozila } from 'src/app/models/klasa-vozila';
import { Vozilo } from 'src/app/models/vozilo';
import { NovoVoziloService } from 'src/app/services/novo-vozilo.service';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-novo-vozilo',
  templateUrl: './novo-vozilo.component.html',
  styleUrls: ['./novo-vozilo.component.css']
})
export class NovoVoziloComponent implements OnInit {

  marke$;
  model$;
  klasa$;
  gorivo$;
  menjac$;
  public vozilo:Vozilo=new Vozilo();
  

  constructor(private modelService:AdminService,private novoVoziloService:NovoVoziloService,private authService: AuthenticationService,private router: Router) { 
    console.log("marke");
    this.marke$= modelService.getMarkeVozila();
    this.model$=modelService.getModelVozila();
    this.klasa$=modelService.getKlaseVozila();
    this.menjac$=modelService.getVrsteMenjacaVozila();
    this.gorivo$=modelService.getTipoviGorivaVozila();
  }

  public Vozilo

  imageName: any;
  selectedFile: File;
  retrievedImage: any;
  message: string;
  urls=[];
  selectedFiles = [];
  
  ngOnInit() {
  }

  onSelect(e){
  //  window.alert("Uspesno ste dodali novo vozilo");
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
    let res = this.novoVoziloService.saveVozilo(this.vozilo, this.selectedFiles);
    console.log();
  }

  onOdjaviMe(){
    window.localStorage.clear();
    this.authService.logout();
  }

  shopCart(){
    this.router.navigateByUrl('cart');
  }

  novo(){
    this.router.navigateByUrl('novoVozilo');
  }
}
