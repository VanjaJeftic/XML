import { MatSnackBar } from '@angular/material';
import { ZahtevService } from './../../../../services/zahtev.service';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';
import { ZahtevBundleViewDTO } from './../../../../models/zahtev-bundle-view-dto';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-moji-zahtevi',
  templateUrl: './moji-zahtevi.component.html',
  styleUrls: ['./moji-zahtevi.component.css']
})
export class MojiZahteviComponent implements OnInit {

  zahtevi: ZahtevBundleViewDTO[] = [];

  constructor(private router: Router, private authService: AuthenticationService, private zahtevService: ZahtevService,
    private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.zahtevService.getZahtevi().subscribe(
      data => {
        console.log(data);
        this.zahtevi = data;
        for(let bundle of this.zahtevi){
          for(let zahtev of bundle.bundleZahtevi){
            let arrayPreuzimanje = zahtev.preuzimanje.split('T');
            let arrayPovratak = zahtev.povratak.split('T');
            let oglasSlobodanOd = zahtev.oglas.slobodanOd.split('T');
            let oglasSlobodanDo = zahtev.oglas.slobodanDo.split('T');
            
            zahtev.preuzimanje = arrayPreuzimanje[0].concat(' ', arrayPreuzimanje[1]);
            zahtev.povratak = arrayPovratak[0].concat(' ', arrayPovratak[1]);

            zahtev.oglas.slobodanOd = oglasSlobodanOd[0].concat(' ', oglasSlobodanOd[1]);
            zahtev.oglas.slobodanDo = oglasSlobodanDo[0].concat(' ', oglasSlobodanDo[1]);
          }
        }
      }
    );
  }

  onPrihvati(data){
    console.log(data);
    this.zahtevService.prihvatiZahtev(data.bundleID).subscribe(
      data => {
        this.snackBar.open('Zahtev prihvacen!', 'U redu', { duration: 10000 });
      },
      err => {
        alert('Ups, vozilo je vec zauzeto!');
        this.snackBar.open('Ups, vozilo je vec zauzeto za ovaj termin!', 'U redu', { duration: 10000 });
      }
    );
  }

  onUnesiIzvestaj(){
    this.router.navigateByUrl('user/izvestaj');
  }

  onMojaVozila(){
    this.router.navigateByUrl('svavozila');
  }

  onOdjaviMe(){
    this.authService.logout();
  }

}
