import { MojIzvestajDialogComponent } from './moj-izvestaj-dialog/moj-izvestaj-dialog.component';
import { MatSnackBar, MatDialog } from '@angular/material';
import { ZahtevService } from './../../../../services/zahtev.service';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';
import { ZahtevBundleViewDTO } from './../../../../models/zahtev-bundle-view-dto';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-moji-izvestaji',
  templateUrl: './moji-izvestaji.component.html',
  styleUrls: ['./moji-izvestaji.component.css']
})
export class MojiIzvestajiComponent implements OnInit {

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

  onUnesiIzvestaj(zahtev){
    console.log(zahtev);
    const dialogRef = this.dialog.open(MojIzvestajDialogComponent, {data: zahtev});
    dialogRef.afterClosed().subscribe(
      result => {
        
      }
    );
  }

  onZahtevi(){
    this.router.navigateByUrl('user/zahtev');
  }

  onMojaVozila(){
    this.router.navigateByUrl('svavozila');
  }

  onOdjaviMe(){
    this.authService.logout();
  }

}
