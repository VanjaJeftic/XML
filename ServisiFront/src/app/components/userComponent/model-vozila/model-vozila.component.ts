import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';
import { ModelVozila } from 'src/app/models/model-vozila';
import { MarkaVozila } from 'src/app/models/marka-vozila';
import {Router} from '@angular/router';

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
    event.preventDefault();
    console.log("Usao u onsubmit modela vozila"+ this.markaVozila.naziv );
    let res=this.modelService.saveModel(this.modelVozila);
    console.log("poslato");
    this.router.navigateByUrl('/listaModelaVozila');
    
  }
 


}
