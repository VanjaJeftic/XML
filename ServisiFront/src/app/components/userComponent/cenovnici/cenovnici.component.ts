import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CenovnikComponent } from './cenovnik/cenovnik.component';
import { MatDialog } from '@angular/material';
import { CenovnikService } from 'src/app/services/cenovnik.service';
import { Cenovnik } from 'src/app/models/cenovnik';

@Component({
  selector: 'app-cenovnici',
  templateUrl: './cenovnici.component.html',
  styleUrls: ['./cenovnici.component.css']
})
export class CenovniciComponent implements OnInit {

  cenovnicima:Cenovnik[];
  cenovniklist$;

  constructor(private cenovnikService : CenovnikService,
    private router:Router,
    private activatedRoute: ActivatedRoute, private dialog: MatDialog) {

      this.cenovniklist$=cenovnikService.getCenovnici();
     }

  ngOnInit() {
  }

  onNoviCenovnik(){
  

    const dialogRef = this.dialog.open(CenovnikComponent);
    dialogRef.afterClosed().subscribe(
   /* result => {
      location.reload();
    }*/
  );
  }

  cenovnici(){
    this.router.navigateByUrl('cenovnici');
  }

  deleteCenovnik(cenovnik: Cenovnik, filter:any): void {
   
    console.log("brisanje modela");
    this.cenovnikService.deleteCenovnik(cenovnik)
      .subscribe( data => {
        window.alert("Uspesno ste izbrisali oglas!");
        if(!filter) return this.cenovnicima;
        
        this.cenovnicima = this.cenovnicima.filter(u => u !== cenovnik);
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

}
