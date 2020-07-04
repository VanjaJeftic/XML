import { Component, OnInit } from '@angular/core';
import { Oglas } from 'src/app/models/oglas';
import { NoviOglasService } from 'src/app/services/novi-oglas.service';
import { MatDialog } from '@angular/material';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { StavkaCenovnikaComponent } from '../userComponent/cenovnici/stavka-cenovnika/stavka-cenovnika.component';

@Component({
  selector: 'app-svi-oglasi',
  templateUrl: './svi-oglasi.component.html',
  styleUrls: ['./svi-oglasi.component.css']
})
export class SviOglasiComponent implements OnInit {
  
  oglaslist$: Observable<any>;
  constructor(private oglasServis:NoviOglasService,public dialog: MatDialog,private authService: AuthenticationService, private router: Router) {

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
}
