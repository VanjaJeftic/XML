import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { SvaVozilaService } from 'src/app/services/sva-vozila.service';
import { Vozilo } from 'src/app/models/vozilo';
import { Router } from '@angular/router';
import { NoviOglasComponent } from '../novi-oglas/novi-oglas.component';

@Component({
  selector: 'app-sva-vozila',
  templateUrl: './sva-vozila.component.html',
  styleUrls: ['./sva-vozila.component.css']
})
export class SvaVozilaComponent implements OnInit {
  vozila:Vozilo[];
  vozilolist$;
  constructor(private vozilaServis:SvaVozilaService,public dialog: MatDialog,private authService: AuthenticationService, private router: Router) {

    this.vozilolist$=vozilaServis.getVozila();
   }

  ngOnInit() {
  }


  public openDialogOglas(id: number) {

    const dialogRef = this.dialog.open(NoviOglasComponent, {
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

   
  novo(){
    this.router.navigateByUrl('novoVozilo');
  }

  deleteVozilo(vozilo: Vozilo, filter:any): void {
   
    console.log("brisanje modela");
    this.vozilaServis.deleteVozilo(vozilo)
      .subscribe( data => {
        window.alert("Uspesno ste izbrisali oglas!");
        if(!filter) return this.vozila;
        
        this.vozila = this.vozila.filter(u => u !== vozilo);
      },err =>{
        
        console.log(err);
       window.alert("Greska!");
 
     },
     () => {
       //window.alert("Uspesno ste obrisali klasu vozila!");
      console.log(`We're done here!`);
    });
     
  };
}
