import { Router } from '@angular/router';
import { OglasService } from './../../../services/oglas.service';
import { Oglas } from './../../../models/oglas';
import { AuthenticationService } from './../../../services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/services/search.service';
import { DateTimeAdapter } from 'ng-pick-datetime';
import { Search } from 'src/app/models/search.model';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { KomentarFormaComponent } from '../komentar-forma/komentar-forma.component';
import { Komentar } from 'src/app/models/komentar';
import { MatMenuModule} from '@angular/material/menu';
import { ResetLozinkeComponent } from '../../reset-lozinke/reset-lozinke.component';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  displayedColumns: string[] = ['Vlasnik', 'Mesto', 'Klasa', 'Model' ,'zakazi']
  sviOglasi: Oglas[] = [];
  oglasiSource: Oglas[] = [];
  startAt: Date = new Date();
  oglasIdzaPrenos:number;

  constructor( public dialog2: MatDialog,public dialog: MatDialog,dateTimeAdapter: DateTimeAdapter<any>, private authService: AuthenticationService, private oglasService: OglasService, private router: Router, private searchService: SearchService) {
    dateTimeAdapter.setLocale('en-GB');
   }
   public komentar:Komentar=new Komentar();
   public ogl:Oglas=new Oglas();
   openDialog(oglas): void {
     console.log(" da li se stampa oglas: " + oglas);
    //this.oglasService.nadjiCeoOglas(oglas);
    localStorage.setItem('oglasStorage', JSON.stringify(oglas));
   this.ogl=JSON.parse(localStorage.getItem('oglasStorage'));
    console.log("ovo je oglas iz storage " + this.ogl.id);
    localStorage.setItem('ceoSelektovaniOglas',""+this.ogl);
    //console.log("ovo je ceo oglas koji je selektovan "+this.ogl);
    this.oglasIdzaPrenos= this.ogl.id;
    localStorage.setItem('idOglasStorage', ""+this.oglasIdzaPrenos);
    console.log("id oglasa je iz local storage " +localStorage.getItem('idOglasStorage')); //ovo mi treba



    const dialogRef = this.dialog.open(KomentarFormaComponent, {
      width: '250px',
     // data: {user: this}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      //this.animal = result;
    });
  }


  ngOnInit() {

    this.oglasService.getAllOglasi().subscribe(
      data => {
        this.oglasiSource = data;
        this.sviOglasi = data;
      }
    );
    this.startAt.setHours(this.startAt.getHours() + 48);

  }

  onRezervisi(selectedOglas){
    console.log(selectedOglas);
   
    this.router.navigateByUrl('vozilo/' + selectedOglas.id);
  }

  public openDialogLozinka(){
    const dialogRef = this.dialog2.open(ResetLozinkeComponent, {
      data: { }
    });
    dialogRef.afterClosed().subscribe(result => {

    });
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

  zahteviMess(){
    this.router.navigateByUrl('zahteviMess');
  }


  resetuj(){
    this.oglasiSource = this.sviOglasi;
  }

  onPretrazi(mesto : string, datum : string, marka : string, model : string, maksimalnaCena : string, minimalnaCena : string){
    let pomocniSearch : Search = new Search();
    pomocniSearch.mesto = mesto;
    pomocniSearch.datumi = datum;
    pomocniSearch.marka = marka;
    pomocniSearch.model = model;
    pomocniSearch.minimalnaCena = minimalnaCena;
    pomocniSearch.maksimalnaCena = maksimalnaCena;
    console.log(pomocniSearch);
    
    this.searchService.pretrazi(pomocniSearch).subscribe(res=>{
      this.oglasiSource = res as Oglas[];
    });
  }

  getDatum(d : Date){
    let datum: string = (d[2]>=10?d[2]:"0"+d[2]) + "-" + (d[1]>=10?d[1]:"0"+d[1]) + "-" + d[0] + ", " + (d[3]>=10?d[3]:"0"+d[3]) + ":" + (d[4]>=10?d[4]:"0"+d[4]);
    return datum;
  }
}
