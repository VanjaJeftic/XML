import { Component, OnInit, ViewChild } from '@angular/core';
import { VoziloService } from 'src/app/services/vozilo.service';
import { KomentarService } from 'src/app/services/komentar.service';
import { VoziloView } from 'src/app/models/vozilo-view';
import { VoziloRejting } from 'src/app/models/vozilo-rejting.model';
import { Komentar } from 'src/app/models/komentar';
import { StatistikaVozilo } from 'src/app/models/statistika-vozilo.model';
import { Sort, MatSort } from '@angular/material';

@Component({
  selector: 'app-statistika',
  templateUrl: './statistika.component.html',
  styleUrls: ['./statistika.component.css']
})
export class StatistikaComponent implements OnInit {
  source;
  listaVozila: VoziloView[];
  listaStatistikaVozila:StatistikaVozilo[]=[];
  listaVoziloRejting: VoziloRejting[] = [];
  najvecaOcena: VoziloView = new VoziloView();
  najviseKomentara: VoziloView = new VoziloView();
  najvecaKm: VoziloRejting = new VoziloRejting();
  komentariList: Komentar[] = [];
  ocena: number = 0;
  listaTopPredjenihKm: VoziloView[]=[]
  listaTopOcena: VoziloRejting[]=[]
  listaTopKomentara: VoziloRejting[]=[]

  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private komentarService: KomentarService, private voziloService: VoziloService) {
    
    this.voziloService.getVozilaAgenta().subscribe(
      data => {
        this.listaVozila = data as VoziloView[];
        for (let v of this.listaVozila) {
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
          let idPrethdnogVozila:number = -1;
          this.voziloService.getOglasiByVoziloId(v.id).subscribe(oglasi => {
            for (let o of oglasi) {
              let prosecnaOcena: number = 0;
              let brojac: number = 0;
              this.komentarService.getKomentariByOglasId(o.id).subscribe(komentari => {
                for (let k of komentari) {
                  if (k.ocena != 10) {
                    brojac = brojac + 1;
                    brojac>=2?brojac=2:brojac=1;
                    prosecnaOcena = (prosecnaOcena + k.ocena) / brojac;
                    console.log(prosecnaOcena);
                    console.log(brojac);
                    pomocnoVozilo.ocena = prosecnaOcena;
                  }
                  pomocnoVozilo.komentari.push(k);
                  if(idPrethdnogVozila!=v.id){
                    this.listaVoziloRejting.push(pomocnoVozilo);
                    idPrethdnogVozila=v.id;
                  } else {
                    this.listaVoziloRejting.pop()
                    this.listaVoziloRejting.push(pomocnoVozilo);
                  }
                  this.listaTopOcena = this.sortList('ocena',this.listaVoziloRejting);
                  this.listaTopKomentara = this.sortList('komentari',this.listaVoziloRejting);
                }
              });
            }
          });
        }
        this.listaTopPredjenihKm = this.sortList('predjeniKm',this.listaVozila);
      }
    );
  }

  ngOnInit() {
  }

  sortList(active:string,lista) {
    let returnLista=[];
    let sort: Sort;
    sort = {"active":active,"direction":"desc"}
    const data = lista.slice();
    if (!sort.active || sort.direction === '') {
      returnLista = data;
      return;
    }

    returnLista = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'ocena': return compare(a.ocena, b.ocena, isAsc);
        case 'predjeniKm': return compare(a.predjeniKm, b.predjeniKm, isAsc);
        case 'komentari': return compare(a.komentari.length, b.komentari.length, isAsc);
        default: return 0;
      }
    });
    if(returnLista.length>5){
      for(let br=0;br<returnLista.length-5;br++){
        returnLista.pop();
      }
    }
    console.log(returnLista)
    return returnLista;
  }
}
function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (+a < +b ? -1 : 1) * (isAsc ? 1 : -1);
}