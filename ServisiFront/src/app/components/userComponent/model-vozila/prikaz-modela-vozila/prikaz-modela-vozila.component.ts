import { Component, OnInit } from '@angular/core';
import { ModelVozila } from 'src/app/models/model-vozila';
import { AdminService } from 'src/app/services/admin.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-prikaz-modela-vozila',
  templateUrl: './prikaz-modela-vozila.component.html',
  styleUrls: ['./prikaz-modela-vozila.component.css']
})
export class PrikazModelaVozilaComponent implements OnInit {
  modeli: ModelVozila[];
  modellist$;
  
  constructor(private  modelServis:AdminService,private router: Router) { 
    this.modellist$= modelServis.getModelVozila();
  }

  ngOnInit() {
  }

  deleteModel(model: ModelVozila, filter:any): void {
    console.log("brisanje modela");
    this.modelServis.deleteModel(model)
      .subscribe( data => {
        if(!filter) return this.modeli;
        this.modeli = this.modeli.filter(u => u !== model);
      })
  };
}
