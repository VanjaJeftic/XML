import { Router } from '@angular/router';
import { OglasService } from './../../../services/oglas.service';
import { Oglas } from './../../../models/oglas';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AuthenticationService } from './../../../services/authentication.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSnackBar, MatDialog, MatSort, Sort } from '@angular/material';
import { stringify } from 'querystring';
import { SearchService } from 'src/app/services/search.service';
import { Search } from 'src/app/models/search.model';
import { DateTimeAdapter } from 'ng-pick-datetime';
import { SearchView } from 'src/app/models/search-view.model';
import { UserView } from 'src/app/models/user-view.model';
import { KomentarFormaComponent } from '../komentar-forma/komentar-forma.component';
import { DijalogInfoKorisnikaComponent } from '../dijalog-info-korisnika/dijalog-info-korisnika.component';
import { SviKomentariOglasaDijalogComponent } from '../svi-komentari-oglasa-dijalog/svi-komentari-oglasa-dijalog.component';
import { Komentar } from 'src/app/models/komentar';
import { ResetLozinkeComponent } from '../reset-lozinke/reset-lozinke.component';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  sviOglasi: Search[] = [];
  oglasiSource: Search[] = [];
  oglasiSorted: Search[]=[];
  startAt: Date = new Date();
  pomocniSearchModel : SearchView = new SearchView();
  pomocniFilterModel : String = new String();
  spinner:boolean= true;
  datumValue : string = "";
  oglasIdzaPrenos:number;
  listaMarki : String[] = [];
  listaModela : String[] = [];
  listaGoriva : String[] = ["Benzin","Dizel", "Benzin + Gas (TNG)", "Metan CNG", "Elektricni pogon", "Hibridni pogon"];
  listaMenjaca : String[] = [];
  listaKlasa : String[] = ["Limuzina","Hecbek","Karavan","Kupe","Kabriolet/Roadster","Monovolumen (miniVan)","Dzip/SUV","Pickup",];
  listaCDW : String[] = ["Da","Ne"];
  listaSedista : String[] = ["2","3","4","5","6","7","8","9"];
  listaFiltriranja : String[] = ["Po ceni uzlazno","Po ceni silazno","Po kilometrima uzlazno","Po kilometrima silazno"];

  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(public dialog4: MatDialog, public dialog3: MatDialog,public dialog2: MatDialog,public dialog: MatDialog,public snackBar: MatSnackBar, dateTimeAdapter: DateTimeAdapter<any>,private authService: AuthenticationService, private oglasService: OglasService, private router: Router, private searchService: SearchService) {
    dateTimeAdapter.setLocale('en-GB');
  }


  public komentar:Komentar=new Komentar();
  public ogl:Oglas=new Oglas();
  openDialog(oglas): void { //ova metoda je bila za kreiranje komentara
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
  
  this.searchService.getAll().subscribe(
    data => {
      this.oglasiSource = data;
      this.oglasiSorted= data;
      console.log(this.oglasiSource);
      this.sviOglasi = data;
      this.spinner = false;
      //provera za ispis marki kod napredne pretrage
      for(let i of this.oglasiSource) {
        let postoji:boolean = false;
        this.listaMarki.map(oglas=>{
          if(i.markaVozila == oglas) {
            postoji = true;
          }
        });
        if(!postoji) {
          this.listaMarki.push(i.markaVozila);
        }
      }
      for(let n of this.oglasiSource) {
        let postoji:boolean = false;
        this.listaMenjaca.map(oglas=>{
          if(n.vrstaMenjaca== oglas) {
            postoji = true;
          }
        });
        if(!postoji) {
          this.listaMenjaca.push(n.vrstaMenjaca);
        }
      }
      console.log(this.listaMenjaca);
    }
  );
  this.startAt.setHours(this.startAt.getHours() + 48);

}

  sortData(odabranaOpcija) {
    let sort: Sort;
    switch(odabranaOpcija){
      case "Po ceni uzlazno":
        sort = {"active":"cena","direction":"asc"};
        break;
      case "Po ceni silazno":
        sort = {"active":"cena","direction":"desc"};
        break;
      case "Po kilometrima silazno":
        sort = {"active":"predjeniKm","direction":"desc"};
        break;
      case "Po kilometrima uzlazno":
        sort = {"active":"predjeniKm","direction":"asc"};
        break;
    }
    
    const data = this.oglasiSource.slice();
    if (!sort.active || sort.direction === '') {
      this.oglasiSorted = data;
      return;
    }

    this.oglasiSorted = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'cena': return compare(a.cena, b.cena, isAsc);
        case 'predjeniKm': return compare(a.predjeniKm, b.predjeniKm, isAsc);
        default: return 0;
      }
    });
    this.oglasiSource = this.oglasiSorted;
  }

  onRezervisi(selectedOglas){
    console.log(selectedOglas);
    this.router.navigateByUrl('vozilo/' + selectedOglas.author);
  }

  public openDialogLozinka(){
    const dialogRef = this.dialog2.open(ResetLozinkeComponent, {
      data: { }
    });
    dialogRef.afterClosed().subscribe(result => {

    });
  }

  public openDialogInfo(){
    //za korisnika
      const dijalogInfo=this.dialog3.open(DijalogInfoKorisnikaComponent,{
        data: { }
    });
    dijalogInfo.afterClosed().subscribe(result => {

    });
  }

//za prikaz svih komentara oglasa
 public openDialogPrikazKomentara(oglas){

  console.log(" da li se stampa oglas: " + oglas);
  //this.oglasService.nadjiCeoOglas(oglas);
  localStorage.setItem('oglasStorage', JSON.stringify(oglas));
 this.ogl=JSON.parse(localStorage.getItem('oglasStorage'));

  const dialogRef = this.dialog4.open(SviKomentariOglasaDijalogComponent, {
    data: {
      
     }
  });
  dialogRef.afterClosed().subscribe(result => {

  });
}



  onOdjaviMe(){
    window.localStorage.clear();
    this.authService.logout();
  }

  onUnesiIzvestaj(){
    this.router.navigateByUrl('user/izvestaj');
  }

  onZahtevi(){
    this.router.navigateByUrl('user/zahtev');
  }

  onShoppingCart(){
    this.router.navigateByUrl('user/cart');
  }

  onVozilo(){
    this.router.navigateByUrl('vozilo/novoVozilo');
  }

  onMojaVozila(){
    this.router.navigateByUrl('svavozila');
  }

  onIznajmljenaVozila() {
    this.router.navigateByUrl('user/iznajmljena-vozila');
  }

  resetuj(){
    this.datumValue = "";
    this.pomocniSearchModel = new SearchView();
    this.oglasiSource = this.sviOglasi;
    this.pomocniFilterModel = "";
  }

  onPretrazi() {
    this.spinner=true;
    console.log(this.pomocniSearchModel);
    this.pomocniSearchModel.datumi = this.datumValue.toString();
    let splitedDatum = this.pomocniSearchModel.datumi.split(',');
    if (splitedDatum[1] != "") {
      this.searchService.pretrazi(this.pomocniSearchModel).subscribe(res => {
        this.oglasiSource = res;
        this.spinner = false;
        if (this.oglasiSource.length == 0) {
          this.snackBar.open('Nismo pronasli ni jedan oglas.', null, { duration: 4000 });
        }
      },
        (err) => {
          this.spinner = false;
          if (err.status == 400) {
            this.oglasiSource = this.sviOglasi;
            this.snackBar.open('Morate uneti vrednost za mesto \n datum preuzimanja i vracanja.', null, { duration: 4000 });
          }
        });
    } else {
      this.spinner = false;
      this.snackBar.open('Morate uneti vrednost za mesto \n datum preuzimanja i vracanja.', null, { duration: 4000 });
    }

  }

  getDatum(da: Date) {
    let d: Date = new Date(da.toString());
    let datum: string = (d.getDate() >= 10 ? d.getDate() : "0" + d.getDate()) + "-" + (d.getMonth()+1 >= 10 ? d.getMonth()+1 : "0" + (d.getMonth()+1)) + "-" + d.getFullYear() + ", " + (d.getHours() >= 10 ? d.getHours() : "0" + d.getHours()) + ":" + (d.getMinutes() >= 10 ? d.getMinutes() : "0" + d.getMinutes());
    return datum;
  }

  getUsername(id:string){
    let author_name : string = "";
    this.searchService.getUserById(+id).subscribe(user => {
      author_name = user.username;
    });
    return author_name;
  }

  zahteviMess(){
    this.router.navigateByUrl('zahteviMess');
  }

  cenovnici(){
    this.router.navigateByUrl('cenovnici');
  }

  proveriMarku() {
    console.log(this.pomocniSearchModel.marka)
    if(this.pomocniSearchModel.marka==null || this.pomocniSearchModel.marka==""){
      this.listaModela = [];
      this.pomocniSearchModel.model="";
      return;
    }
    this.listaModela = [];
    for(let p of this.sviOglasi) {
      if(p.markaVozila == this.pomocniSearchModel.marka && !(this.listaModela.includes(p.modelVozila))) {
        this.listaModela.push(p.modelVozila);
      }
    }
  }

}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}