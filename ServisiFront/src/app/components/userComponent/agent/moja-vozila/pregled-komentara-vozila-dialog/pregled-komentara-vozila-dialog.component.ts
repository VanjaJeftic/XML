import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { VoziloRejting } from 'src/app/models/vozilo-rejting.model';
import { KomentarService } from 'src/app/services/komentar.service';
import { Komentar } from 'src/app/models/komentar';

@Component({
  selector: 'app-pregled-komentara-vozila-dialog',
  templateUrl: './pregled-komentara-vozila-dialog.component.html',
  styleUrls: ['./pregled-komentara-vozila-dialog.component.css']
})
export class PregledKomentaraVozilaDialogComponent implements OnInit {
  komentariSource : Komentar[]=[];
  nazivVozila:string;
  constructor(public dialogRef: MatDialogRef<PregledKomentaraVozilaDialogComponent>, @Inject(MAT_DIALOG_DATA) public data : VoziloRejting,
  private komentarService: KomentarService, private snackBar: MatSnackBar) { }

  ngOnInit() {
  this.nazivVozila = this.data.markaVozila + ' ' + this.data.modelVozila + ' ' +this.data.klasaVozila
    console.log(this.data);
    this.komentariSource= this.data.komentari;
    //this.komentariSource[0].odgovor_id
  }

  dodajKomentar(sadrzaj : string){
    let pomocni:Komentar = new Komentar();
    pomocni.datum = new Date();
    pomocni.odbijen = false;
    pomocni.odobren = true;
    pomocni.odgovor_id = 1;
    pomocni.ocena=10;
    pomocni.oglas_id = this.komentariSource[0].oglas_id;
    pomocni.korisnik_id = localStorage.getItem('userId');
    pomocni.usernameusera = localStorage.getItem('username');
    pomocni.sadrzaj = sadrzaj;
    this.komentarService.saveKomentar(pomocni).subscribe(data=>{
      this.snackBar.open('Odgovor je poslat!', null, { duration: 4000 });
      this.komentariSource.push(pomocni);
    });
  }
}