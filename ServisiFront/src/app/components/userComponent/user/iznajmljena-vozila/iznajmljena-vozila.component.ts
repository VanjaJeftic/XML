import { Component, OnInit } from '@angular/core';
import { ZahtevService } from 'src/app/services/zahtev.service';
import { Zahtev } from 'src/app/models/zahtev';
import { MatDialog } from '@angular/material';
import { KomentarDialogComponent } from './komentar-dialog/komentar-dialog.component';

@Component({
  selector: 'app-iznajmljena-vozila',
  templateUrl: './iznajmljena-vozila.component.html',
  styleUrls: ['./iznajmljena-vozila.component.css']
})
export class IznajmljenaVozilaComponent implements OnInit {
  zahteviSource : Zahtev[]=[];
  constructor(public dialog: MatDialog,private zahtevService : ZahtevService) {
    zahtevService.getZahteviPodnosioca().subscribe(data=>{
      console.log(data);
      this.zahteviSource = data;
    });
   }

  ngOnInit() {
  }

  
  onOceni(zahtev:Zahtev){
    console.log(zahtev);
    const dialogRef = this.dialog.open(KomentarDialogComponent, {data: zahtev});
    dialogRef.afterClosed().subscribe(result => {
      //alert('Pritisnuto zatvaranje!')s
    });
  }
  getAccepted(zahtev:Zahtev){
    return zahtev.status=="ACCEPTED" && !this.getInactive(zahtev)?true:false;
  }
  getPending(zahtev:Zahtev){
    return zahtev.status=="PENDING"?true:false;
  }
  getInactive(zahtev:Zahtev){
    let datumIsteka = new Date(zahtev.povratak);
    let danasnjiDatum = new Date();
    return datumIsteka<danasnjiDatum && zahtev.status=="ACCEPTED"?true:false;
  }
}
