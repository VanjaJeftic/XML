import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';
import { KlasaVozila } from 'src/app/models/klasa-vozila';

@Component({
  selector: 'app-prikaz-svih-klasa-vozila',
  templateUrl: './prikaz-svih-klasa-vozila.component.html',
  styleUrls: ['./prikaz-svih-klasa-vozila.component.css']
})
export class PrikazSvihKlasaVozilaComponent implements OnInit {
  klaseVozila: KlasaVozila[];
  klasaVozilalist$;
  constructor(private  klasaServis:AdminService) { 
    this.klasaVozilalist$= klasaServis.getKlaseVozila();
  }
  public klasaVozila:KlasaVozila=new KlasaVozila();
  ngOnInit() {
  }

  deleteKlaseVozila(klasa: KlasaVozila): void {
    console.log("brisanje klase");
    this.klasaServis.deleteKlaseVozila(klasa)
      .subscribe( data => {
        this.klaseVozila = this.klaseVozila.filter(u => u !== klasa);
      })
  };

}
