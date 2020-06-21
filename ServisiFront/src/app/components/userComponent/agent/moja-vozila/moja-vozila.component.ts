import { Router } from '@angular/router';
import { TerminZauzecaDialogComponent } from './../termin-zauzeca-dialog/termin-zauzeca-dialog.component';
import { VoziloView } from './../../../../models/vozilo-view';
import { VoziloService } from './../../../../services/vozilo.service';
import { AuthenticationService } from './../../../../services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { VoziloRejting } from 'src/app/models/vozilo-rejting.model';
import { KomentarService } from 'src/app/services/komentar.service';
import { PregledKomentaraVozilaDialogComponent } from './pregled-komentara-vozila-dialog/pregled-komentara-vozila-dialog.component';

@Component({
  selector: 'app-moja-vozila',
  templateUrl: './moja-vozila.component.html',
  styleUrls: ['./moja-vozila.component.css']
})
export class MojaVozilaComponent implements OnInit {

  displayedColumns: string[] = ['Klasa', 'Model', 'Marka', 'KM' ,'Zauzece', 'Detaljnije','Ocena','Komentari']
  voziloSource;
  listaVozila:VoziloView[];
  listaVoziloRejting:VoziloRejting[]=[];

  constructor(private komentarService:KomentarService,private authService: AuthenticationService, private voziloService: VoziloService,
              private dialog: MatDialog, private router: Router) { }

  ngOnInit() {
    this.voziloService.getVozilaAgenta().subscribe(
      data => {
        this.listaVozila = data as VoziloView[];
        for(let v of this.listaVozila){
          let pomocnoVozilo :VoziloRejting = new VoziloRejting();
          pomocnoVozilo.id = v.id;
          pomocnoVozilo.brsedistadeca = v.brsedistadeca;
          pomocnoVozilo.klasaVozila= v.klasaVozila;
          pomocnoVozilo.markaVozila = v.markaVozila;
          pomocnoVozilo.modelVozila = v.modelVozila;
          pomocnoVozilo.predjeniKm = v.predjeniKm;
          pomocnoVozilo.tipGoriva = v.tipGoriva;
          pomocnoVozilo.user = v.user;
          pomocnoVozilo.vrstaMenjaca = v.vrstaMenjaca;
          pomocnoVozilo.komentari = [];
          let prosecnaOcena:number=0;
          let brojac:number = 0;
          this.voziloService.getOglasiByVoziloId(v.id).subscribe(oglasi=>{
            for(let o of oglasi){
              this.komentarService.getKomentariByOglasId(o.id).subscribe(komentari=>{
                for(let k of komentari){
                  if(k.ocena!=10){
                    brojac=brojac+1;
                    prosecnaOcena=(prosecnaOcena+k.ocena)/brojac;
                    pomocnoVozilo.ocena = prosecnaOcena;
                  }
                  pomocnoVozilo.komentari.push(k);
                }
              })
            }
          });
          this.listaVoziloRejting.push(pomocnoVozilo);
        }
        console.log(this.listaVoziloRejting);
        console.log(this.listaVoziloRejting.length);
        this.voziloSource = new MatTableDataSource(this.listaVoziloRejting);
      }
    );
  }

  onUnesiZauzece(element){
    const dialogRef = this.dialog.open(TerminZauzecaDialogComponent, {data: element});
    dialogRef.afterClosed().subscribe(
      result => {
        
      }
    );
  }

  onDetaljnije(element){
    alert('U izradi!');
  }

  onOdjaviMe(){
    this.authService.logout();
  }

  getBrojKomentara(o:VoziloRejting){
    return o.komentari.length;
  }

  openKomentarDialog(vozilo:VoziloRejting){
    console.log(vozilo);
    const dialogRef = this.dialog.open(PregledKomentaraVozilaDialogComponent, {data: vozilo});
  }
}
