import { Component, OnInit } from '@angular/core';
import { ModelVozila } from 'src/app/models/model-vozila';
import { AdminService } from 'src/app/services/admin.service';
import { MarkaVozila } from 'src/app/models/marka-vozila';
import { Router } from '@angular/router';

@Component({
  selector: 'app-model-vozila',
  templateUrl: './model-vozila.component.html',
  styleUrls: ['./model-vozila.component.css']
})
export class ModelVozilaComponent implements OnInit {

 
  marke$;

  constructor(private modelService:AdminService,private router: Router) { 
    console.log("marke");
    this.marke$= modelService.getMarkeVozila();
  }

  public modelVozila:ModelVozila=new ModelVozila();
  public markaVozila:MarkaVozila=new MarkaVozila();
  
  ngOnInit() {
  }


  public onSubmit(): void{
    window.alert("Uspesno ste dodali model vozila");
    event.preventDefault();
    console.log("Usao u onsubmit modela vozila"+ this.markaVozila.naziv );
    let res=this.modelService.saveModel(this.modelVozila);
    console.log("poslato");
    this.router.navigateByUrl('/listaModelaVozila');
    
  }
 


}
