import { Component, OnInit } from '@angular/core';
import { StavkaCenovnika } from 'src/app/models/stavka-cenovnika';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { CenovnikService } from 'src/app/services/cenovnik.service';
import { Router, ActivatedRoute } from '@angular/router';
import { MatDialog } from '@angular/material';
import { StavkaCenovnikaComponent } from '../stavka-cenovnika/stavka-cenovnika.component';

@Component({
  selector: 'app-stavke',
  templateUrl: './stavke.component.html',
  styleUrls: ['./stavke.component.css']
})
export class StavkeComponent implements OnInit {

  stavke:StavkaCenovnika[];
  stavkalist$;

  constructor(private authService:AuthenticationService,private cenovnikService : CenovnikService,
    private router:Router,
    private activatedRoute: ActivatedRoute, private dialog: MatDialog) {

      this.stavkalist$=cenovnikService.getStavke(parseFloat(localStorage.getItem("cenovnikId")));
     }

  ngOnInit() {
  }

  onNoviCenovnik(){
  

    const dialogRef = this.dialog.open(StavkaCenovnikaComponent);
    dialogRef.afterClosed().subscribe(
   /* result => {
      location.reload();
    }*/
  );
  }

  cenovnici(){
    this.router.navigateByUrl('cenovnici');
  }

  deleteCenovnik(stavka: StavkaCenovnika, filter:any): void {
   
    console.log("brisanje modela");
    this.cenovnikService.deleteCenovnik(stavka)
      .subscribe( data => {
        window.alert("Uspesno ste izbrisali oglas!");
        if(!filter) return this.stavke;
        
        this.stavke = this.stavke.filter(u => u !== stavka);
      },err =>{
        
        console.log(err);
       window.alert("Greska!");
 
     },
     () => {
       //window.alert("Uspesno ste obrisali klasu vozila!");
      console.log(`We're done here!`);
    });
     
  };

  izmenaCenovnika(cenovnik) {
    localStorage.setItem("cenovnik", JSON.stringify(cenovnik)); 
    this.router.navigate(["/izmenaCenovnika"]);
 }

 onOdjaviMe(){
  window.localStorage.clear();
  this.authService.logout();
}

}

