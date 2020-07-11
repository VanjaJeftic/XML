import { Component, OnInit } from '@angular/core';
import { StavkaCenovnikaComponent } from '../cenovnici/stavka-cenovnika/stavka-cenovnika.component';
import { NoviOglasService } from 'src/app/services/novi-oglas.service';
import { MatDialog } from '@angular/material';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Oglas } from 'src/app/models/oglas';
import { PraviOglasService } from 'src/app/services/pravi-oglas.service';
import { StavkaCenovnika } from 'src/app/models/stavka-cenovnika';
import { CenovnikService } from 'src/app/services/cenovnik.service';

@Component({
  selector: 'app-svi-oglasi',
  templateUrl: './svi-oglasi.component.html',
  styleUrls: ['./svi-oglasi.component.css']
})
export class SviOglasiComponent implements OnInit {

  oglasi: Oglas[];
  oglaslist$: Observable<any>;
  constructor(private cenovnikServis:CenovnikService,private oglasServis:PraviOglasService,public dialog: MatDialog,private authService: AuthenticationService, private router: Router) {

    this.oglaslist$=oglasServis.getOglasi();
   }

  ngOnInit() {
  }


  public openDialogOglas(id: number) {

    console.log("Stavka dodaj");
    const dialogRef = this.dialog.open(StavkaCenovnikaComponent, {
      data: { id: id }
      
      
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(id);

    });

  }

  onOdjaviMe(){
    window.localStorage.clear();
    this.authService.logout();
  }

  shopCart(){
    this.router.navigateByUrl('cart');
  }

  onVozilo(){
    this.router.navigateByUrl('vozilo/novoVozilo');
  }

  onMojaVozila(){
    this.router.navigateByUrl('svavozila');
  }

  deleteOglas(oglas: Oglas, filter:any): void {
   
    console.log("brisanje oglasa");
    this.oglasServis.deleteOglas(oglas)
      .subscribe( data => {
        window.alert("Uspesno ste izbrisali oglas!");
        if(!filter) return this.oglasi;
        
        this.oglasi = this.oglasi.filter(u => u !== oglas);
      },err =>{
        
        console.log(err);
       window.alert("Greska!");
 
     },
     () => {
       //window.alert("Uspesno ste obrisali klasu vozila!");
      console.log(`We're done here!`);
    });
     
  };

  izmenaOglasa(oglas) {
    localStorage.setItem("oglas", JSON.stringify(oglas)); 
    this.router.navigate(["/izmenaOglasa"]);
 }

 public stavka:StavkaCenovnika=new StavkaCenovnika();

 izmenaStavke(idoglasa) {
  this.cenovnikServis.getStavka(idoglasa).subscribe(
    data => {
      console.log(data);
      this.stavka = data;
      localStorage.setItem("stavkaCenovnika", JSON.stringify(this.stavka)); 
    }
  );
  
  this.router.navigate(["/izmenaStavke"]);
}

}
