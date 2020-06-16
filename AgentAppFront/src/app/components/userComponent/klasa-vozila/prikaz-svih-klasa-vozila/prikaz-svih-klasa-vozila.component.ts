import { Component, OnInit } from '@angular/core';
import { KlasaVozila } from 'src/app/models/klasa-vozila';
import { AdminService } from 'src/app/services/admin.service';
import { Router } from '@angular/router';
import { BootstrapAlert } from 'ngx-bootstrap-alert';


@Component({
  selector: 'app-prikaz-svih-klasa-vozila',
  templateUrl: './prikaz-svih-klasa-vozila.component.html',
  styleUrls: ['./prikaz-svih-klasa-vozila.component.css']
})
export class PrikazSvihKlasaVozilaComponent implements OnInit {

  klaseVozila: KlasaVozila[];
  klasaVozilalist$;
  constructor(private  klasaServis:AdminService,private router: Router) { 
    this.klasaVozilalist$= klasaServis.getKlaseVozila();
  }
  public klasaVozila:KlasaVozila=new KlasaVozila();
  ngOnInit() {
  }

  deleteKlaseVozila(klasa: KlasaVozila): void {
    //console.log("brisanje klase");
    this.klasaServis.deleteKlaseVozila(klasa)
      .subscribe( data => {
        window.alert("Uspesno!");
        this.klaseVozila = this.klaseVozila.filter(u => u !== klasa);
      },err =>{
        
         console.log(err);
        window.alert("Greska!");
  
      },
      () => {
        //window.alert("Uspesno ste obrisali klasu vozila!");
       console.log(`We're done here!`);
     });
      
    
   };
 


  izmenaKlase(klasaVozila) {
      localStorage.setItem("klasaVozila", JSON.stringify(klasaVozila)); 
      this.router.navigate(["/izmenaKlaseVozila"]);
   }


}
