import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { Zahtev } from 'src/app/models/zahtev';
import { Komentar } from 'src/app/models/komentar.model';
import { KomentarService } from 'src/app/services/komentar.service';

@Component({
  selector: 'app-komentar-dialog',
  templateUrl: './komentar-dialog.component.html',
  styleUrls: ['./komentar-dialog.component.css']
})
export class KomentarDialogComponent implements OnInit {
  komentarDTO : Komentar = new Komentar();
  public starRating : number = 0.00;
  public komentar:string = "";
  zahtev: Zahtev = new Zahtev();
  constructor(private komentarService : KomentarService ,private snackBar : MatSnackBar ,public dialogRef: MatDialogRef<KomentarDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: Zahtev) {
    this.zahtev = data;
   }

  ngOnInit() {
  }
  onRatingChange(event){
    this.starRating = event.rating;
  }
  dodajKomentar(){
    if(this.starRating == 0 || this.komentar==""){
      this.snackBar.open('Unesite ocenu i komentar!', null, { duration: 3000 });
    } else {
      this.komentarDTO.sadrzaj = this.komentar;
      this.komentarDTO.korisnik_id = localStorage.getItem('userId');
      this.komentarDTO.oglasid = this.zahtev.oglas.id;
      this.komentarDTO.datum = new Date();
      this.komentarDTO.ocena = this.starRating;
      this.komentarService.saveKomentar(this.komentarDTO).subscribe(data=>{
        console.log(this.komentar);
        this.snackBar.open('Uspesno ste ocenili vozilo!', null, { duration: 4000 });
        this.dialogRef.close();
      });
    }
  }
  public izadji(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste!', null, { duration: 2000 });
  }
  validate(){
    if(this.starRating == 0 || this.komentar=="")
      return false;
    return true;
  }
}
