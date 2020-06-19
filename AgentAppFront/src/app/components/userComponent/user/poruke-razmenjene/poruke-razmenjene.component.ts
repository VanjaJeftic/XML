import { Component, OnInit } from '@angular/core';
import { ZahtevBundleViewDTO } from 'src/app/models/zahtev-bundle-view-dto';
import { Router } from '@angular/router';
import { MatSnackBar, MatDialog } from '@angular/material';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { PorukaZahtevService } from 'src/app/services/poruka-zahtev.service';


@Component({
  selector: 'app-poruke-razmenjene',
  templateUrl: './poruke-razmenjene.component.html',
  styleUrls: ['./poruke-razmenjene.component.css']
})
export class PorukeRazmenjeneComponent implements OnInit {
  zahtevi: ZahtevBundleViewDTO[] = [];

  constructor(private router: Router, private authService: AuthenticationService, private zahtevService: PorukaZahtevService,
    private snackBar: MatSnackBar, private dialog: MatDialog) { }

    ngOnInit() {
      this.zahtevService.getZahteviI().subscribe(
        data => {
          console.log(data);
          this.zahtevi = data;
          for(let bundle of this.zahtevi){
            for(let zahtev of bundle.bundleZahtevi){
              let godinaPovratak = zahtev.povratak[0];
              let mesecPovratak = zahtev.povratak[1];
              let danPovratak = zahtev.povratak[2];
              let satPovratak = zahtev.povratak[3];
              let minutPovratak = zahtev.povratak[4];
  
              let godinapreuzimanje = zahtev.preuzimanje[0];
              let mesecpreuzimanje = zahtev.preuzimanje[1];
              let danpreuzimanje = zahtev.preuzimanje[2];
              let satpreuzimanje = zahtev.preuzimanje[3];
              let minutpreuzimanje = zahtev.preuzimanje[4];
  
              zahtev.povratak = godinaPovratak + '-' + mesecPovratak + '-' + danPovratak + ' ' + satPovratak + ':' + minutPovratak;
              zahtev.preuzimanje = godinapreuzimanje + '-' + mesecpreuzimanje + '-' + danpreuzimanje + ' ' + satpreuzimanje + ':' + minutpreuzimanje;
            }
          }
        }
      );
    }

    onPoruke(data){
      console.log(data.bundleID)
      localStorage.setItem("zahtevId",data.bundleID);
      this.router.navigateByUrl('prikazPoruke');
      
    }
  

    
  onOdjaviMe(){
    window.localStorage.clear();
    this.authService.logout();
  }

  shopCart(){
    this.router.navigateByUrl('cart');
  }

  novo(){
    this.router.navigateByUrl('novoVozilo');
  }

  sva(){
    this.router.navigateByUrl('svaVozila');
  }

  prihvaceni(){
    this.router.navigateByUrl('posalji');
  }
}