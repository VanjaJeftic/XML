import { Component, OnInit } from '@angular/core';
import { Komentar } from 'src/app/models/komentar';
import { AdminService } from 'src/app/services/admin.service';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { MatDialog } from '@angular/material';
import { DijalogInfoKorisnikaComponent } from '../dijalog-info-korisnika/dijalog-info-korisnika.component';

@Component({
  selector: 'app-prikaz-komentara-korisnika',
  templateUrl: './prikaz-komentara-korisnika.component.html',
  styleUrls: ['./prikaz-komentara-korisnika.component.css']
})
export class PrikazKomentaraKorisnikaComponent implements OnInit {

  komentari: Komentar[];
  komentarlist$;

  constructor(public dialog2: MatDialog,private  komentarService:AdminService,private router: Router) {
    this.komentarlist$= komentarService.getKomentari();
   }

   public userEntity:User=new User();
   public komentar:Komentar=new Komentar();

   ngOnInit() {

    }

  public pribaviKorisnika(korisnik_id){
      console.log("Caoooooooooo");
      let res = this.komentarService.getUserPoId(korisnik_id).subscribe(
       data => {
           this.userEntity = data;
           localStorage.setItem('user', JSON.stringify(this.userEntity));
           console.log("ovo je user: "+ JSON.parse(localStorage.getItem('user')));
      }
  );


    
  }


public korisnikInfoDijalog(){
    
  console.log("ova funkcija");
    const dialogRef = this.dialog2.open(DijalogInfoKorisnikaComponent, {
      data: {
        
       }
    });
    dialogRef.afterClosed().subscribe(result => {

    });
  }


}
