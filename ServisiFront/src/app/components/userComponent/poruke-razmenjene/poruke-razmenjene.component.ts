import { Component, OnInit } from '@angular/core';
import { ZahtevBundleViewDTO } from './../../../models/zahtev-bundle-view-dto';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { MatDialog, MatSnackBar } from '@angular/material';
import { ZahtevService } from 'src/app/services/zahtev.service';

@Component({
  selector: 'app-poruke-razmenjene',
  templateUrl: './poruke-razmenjene.component.html',
  styleUrls: ['./poruke-razmenjene.component.css']
})
export class PorukeRazmenjeneComponent implements OnInit {

  zahtevi: ZahtevBundleViewDTO[] = [];

  constructor(private router: Router, private authService: AuthenticationService, private zahtevService: ZahtevService,
    private snackBar: MatSnackBar, private dialog: MatDialog) { }

    ngOnInit() {
      this.zahtevService.getZahteviIzvestaj().subscribe(
        data => {
          console.log(data);
          this.zahtevi = data;
          for(let bundle of this.zahtevi){
            for(let zahtev of bundle.bundleZahtevi){
              let arrayPreuzimanje = zahtev.preuzimanje.split('T');
              let arrayPovratak = zahtev.povratak.split('T');
              
              zahtev.preuzimanje = arrayPreuzimanje[0].concat(' ', arrayPreuzimanje[1]);
              zahtev.povratak = arrayPovratak[0].concat(' ', arrayPovratak[1]);
            }
          }
        }
      );
    }

    
    onPoruke(data){
      console.log("Da vidimo ovaj bundle"+ data.oglas.id)
      localStorage.setItem("zahtevId",data.oglas.id);
      this.router.navigateByUrl('prikazPoruke');
      
    }
    onZahtevi(){
      this.router.navigateByUrl('agent/zahtev');
    }
  
    onMojaVozila(){
      this.router.navigateByUrl('agent/vozila');
    }
  
    onOdjaviMe(){
      this.authService.logout();
    }
  
  }
  