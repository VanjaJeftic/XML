import { ZahtevBundleViewDTO } from './../../../../models/zahtev-bundle-view-dto';
import { ZahtevService } from './../../../../services/zahtev.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-zahtevi',
  templateUrl: './zahtevi.component.html',
  styleUrls: ['./zahtevi.component.css']
})
export class ZahteviComponent implements OnInit {

  zahtevi: ZahtevBundleViewDTO[] = [];

  constructor(private router: Router, private authService: AuthenticationService, private zahtevService: ZahtevService) { }

  ngOnInit() {
    this.zahtevService.getZahtevi().subscribe(
      data => {
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

  onPrihvati(data){
    console.log(data);
    this.zahtevService.prihvatiZahtev(data.bundleID).subscribe(
      data => {
        console.log('Uspesno izmenjeni statusi!');
      }
    );
  }

  onMojaVozila(){
    this.router.navigateByUrl('vozila');
  }

  onOdjaviMe(){
    this.authService.logout();
  }

}
