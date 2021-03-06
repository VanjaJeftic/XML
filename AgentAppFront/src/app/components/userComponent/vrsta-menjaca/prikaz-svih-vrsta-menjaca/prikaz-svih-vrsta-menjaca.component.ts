import { Component, OnInit } from '@angular/core';
import { VrstaMenjaca } from 'src/app/models/vrsta-menjaca';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-prikaz-svih-vrsta-menjaca',
  templateUrl: './prikaz-svih-vrsta-menjaca.component.html',
  styleUrls: ['./prikaz-svih-vrsta-menjaca.component.css']
})
export class PrikazSvihVrstaMenjacaComponent implements OnInit {

  menjaci:VrstaMenjaca[];
  vrstaMenjacalist$;

  constructor(private  vrstaMenjacaServis:AdminService,private router: Router) { 
    this.vrstaMenjacalist$= vrstaMenjacaServis.getVrsteMenjacaVozila();
  }

  public vrstaMenjaca:VrstaMenjaca=new VrstaMenjaca();

  ngOnInit() {
  }

  deleteVrsteMenjaca(menjac: VrstaMenjaca): void {
   
    
    this.vrstaMenjacaServis.deleteVrsteMenjaca(menjac)
      .subscribe( 
        data => {
        window.alert("Uspesno ste izbrisali vrstu menjaca!");
        this.menjaci = this.menjaci.filter(u => u !== menjac);
      },err =>{
        
       // window.alert("greska");
        console.log(err)
        window.alert("Brisanje menjaca nije moguce!");
     },
     () => {
      console.log(`We're done here!`);
    });
     
   
  };


  izmenaVrsteMenjaca(vrstaMenjaca) {
    localStorage.setItem("vrstaMenjaca", JSON.stringify(vrstaMenjaca)); 
    this.router.navigate(["/izmenaVrsteMenjacaVozila"]);
 }
}
