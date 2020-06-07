import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';
import { MarkaVozila } from 'src/app/models/marka-vozila';

@Component({
  selector: 'app-prikaz-svih-marki-vozila',
  templateUrl: './prikaz-svih-marki-vozila.component.html',
  styleUrls: ['./prikaz-svih-marki-vozila.component.css']
})
export class PrikazSvihMarkiVozilaComponent implements OnInit {

  marke: MarkaVozila[];
  markelist$;
  constructor(private  markaServis:AdminService) { 
    this.markelist$= markaServis.getMarkeVozila();

  }
  public markaVozila:MarkaVozila=new MarkaVozila();
  
  ngOnInit() {
  }

  deleteMarka(marka: MarkaVozila): void {
    console.log("brisanje marke");
    this.markaServis.deleteMarka(marka)
      .subscribe( data => {
        this.marke = this.marke.filter(u => u !== marka);
      })
  };
}
