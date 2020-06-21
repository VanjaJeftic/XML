import { Component, OnInit } from '@angular/core';
import { VoziloService } from 'src/app/services/vozilo.service';
import { KomentarService } from 'src/app/services/komentar.service';
import { VoziloView } from 'src/app/models/vozilo-view';
import { VoziloRejting } from 'src/app/models/vozilo-rejting.model';
import { Komentar } from 'src/app/models/komentar';

@Component({
  selector: 'app-statistika',
  templateUrl: './statistika.component.html',
  styleUrls: ['./statistika.component.css']
})
export class StatistikaComponent implements OnInit {
  source;
  listaVozila: VoziloView[];
  listaVoziloRejting: VoziloRejting[] = [];
  najvecaOcena: VoziloView = new VoziloView();
  najviseKomentara: VoziloView = new VoziloView();
  najvecaKm: VoziloRejting = new VoziloRejting();
  komentariList: Komentar[] = [];
  ocena: number = 0;

  constructor(private komentarService: KomentarService, private voziloService: VoziloService) {
    
    this.voziloService.getVozilaAgenta().subscribe(
      data => {
        this.listaVozila = data as VoziloView[];
        for (let v of this.listaVozila) {
          let imaKomentara: boolean = false;
          let pomocnoVozilo: VoziloRejting = new VoziloRejting();
          pomocnoVozilo.id = v.id;
          pomocnoVozilo.brsedistadeca = v.brsedistadeca;
          pomocnoVozilo.klasaVozila = v.klasaVozila;
          pomocnoVozilo.markaVozila = v.markaVozila;
          pomocnoVozilo.modelVozila = v.modelVozila;
          pomocnoVozilo.predjeniKm = v.predjeniKm;
          pomocnoVozilo.tipGoriva = v.tipGoriva;
          pomocnoVozilo.user = v.user;
          pomocnoVozilo.vrstaMenjaca = v.vrstaMenjaca;
          pomocnoVozilo.ocena = 0;
          pomocnoVozilo.komentari = [];
          this.voziloService.getOglasiByVoziloId(v.id).subscribe(oglasi => {
            for (let o of oglasi) {
              let prosecnaOcena: number = 0;
              let brojac: number = 0;
              this.komentarService.getKomentariByOglasId(o.id).subscribe(komentari => {
                for (let k of komentari) {
                  imaKomentara = true;
                  if (k.ocena != 10) {
                    brojac = brojac + 1;
                    prosecnaOcena = (prosecnaOcena + k.ocena) / brojac;
                    pomocnoVozilo.ocena = prosecnaOcena;
                    if (+localStorage.getItem('ocena') < pomocnoVozilo.ocena) {
                      localStorage.setItem('ocena', JSON.stringify(pomocnoVozilo.ocena));
                      localStorage.setItem('ocenaId', JSON.stringify(pomocnoVozilo.id));
                    };
                  }
                  pomocnoVozilo.komentari.push(k);
                  try {
                    if ((JSON.parse(localStorage.getItem('niz')) as Komentar[]).length < pomocnoVozilo.komentari.length) {
                      localStorage.setItem('niz', JSON.stringify(pomocnoVozilo.komentari));
                      localStorage.setItem('nizId', JSON.stringify(pomocnoVozilo.id));
                    };
                  } catch (exception) {
                    localStorage.setItem('niz', JSON.stringify(pomocnoVozilo.komentari));
                    localStorage.setItem('nizId', JSON.stringify(pomocnoVozilo.id));
                  }
                }
              });
            }
          });
          this.listaVoziloRejting.push(pomocnoVozilo);
        }
        this.najvecaKm.predjeniKm="0";
        for (let v of this.listaVoziloRejting) {
          if (v.id == +localStorage.getItem('nizId')) {
            this.najviseKomentara = v;
          }
          if (+this.najvecaKm.predjeniKm < +v.predjeniKm) {
            this.najvecaKm = v;
          }
          if ( v.id == +localStorage.getItem('ocenaId')) {
            this.najvecaOcena = v;
          }
        }
        this.ocena = +localStorage.getItem('ocena');
        this.komentariList = (JSON.parse(localStorage.getItem('niz')) as Komentar[]);
      }
    );
  }

  ngOnInit() {

  }

}