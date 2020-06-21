import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { MatDialog } from '@angular/material';
import { DijalogInfoKorisnikaComponent } from '../dijalog-info-korisnika/dijalog-info-korisnika.component';
import { Komentar } from 'src/app/models/komentar';

@Component({
  selector: 'app-list-odobrenih-komentara-admin',
  templateUrl: './list-odobrenih-komentara-admin.component.html',
  styleUrls: ['./list-odobrenih-komentara-admin.component.css']
})
export class ListOdobrenihKomentaraAdminComponent implements OnInit {

  komentari: Komentar[];
  komentarlist$;

  constructor(public dialog2: MatDialog,private  komentarodobrenService:AdminService,private router: Router) {
    this.komentarlist$= komentarodobrenService.getKomentariOdobreni();
   }

   public userEntity:User=new User();
   public komentar:Komentar=new Komentar();

   ngOnInit() {

    }

  public pribaviKorisnika(korisnik_id){
      let res = this.komentarodobrenService.getUserPoId(korisnik_id).subscribe(
       data => {
           this.userEntity = data;
           localStorage.setItem('user', JSON.stringify(this.userEntity));
           console.log("ovo je user: "+ JSON.parse(localStorage.getItem('user')));
      }
  );
  }

 

public korisnikInfoDijalog(){
    const dialogRef = this.dialog2.open(DijalogInfoKorisnikaComponent, {
      data: {
        
       }
    });
    dialogRef.afterClosed().subscribe(result => {

    });
  }


}


