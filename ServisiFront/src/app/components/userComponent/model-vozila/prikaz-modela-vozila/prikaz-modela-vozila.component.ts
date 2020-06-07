import { Component, OnInit } from '@angular/core';
import { ModelVozila } from 'src/app/models/model-vozila';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-prikaz-modela-vozila',
  templateUrl: './prikaz-modela-vozila.component.html',
  styleUrls: ['./prikaz-modela-vozila.component.css']
})
export class PrikazModelaVozilaComponent implements OnInit {
  modeli: ModelVozila[];
  modellist$;
  
  constructor(private  modelServis:AdminService) { 
    this.modellist$= modelServis.getModelVozila();
  }

  ngOnInit() {
  }

  deleteModel(model: ModelVozila): void {
    console.log("brisanje modela");
    this.modelServis.deleteModel(model)
      .subscribe( data => {
        this.modeli = this.modeli.filter(u => u !== model);
      })
  };

}
