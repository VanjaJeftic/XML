import { MatSnackBar } from '@angular/material';
import { ZahtevService } from './../../../../services/zahtev.service';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';
import { ZahtevBundleViewDTO } from './../../../../models/zahtev-bundle-view-dto';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-izvestaj',
  templateUrl: './izvestaj.component.html',
  styleUrls: ['./izvestaj.component.css']
})
export class IzvestajComponent implements OnInit {

  zahtevi: ZahtevBundleViewDTO[] = [];

  constructor(private router: Router, private authService: AuthenticationService, private zahtevService: ZahtevService,
                private snackBar: MatSnackBar) { }

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

  onUnesiIzvestaj(zahtev){
    alert('U izradi!');
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
