import { Component, OnInit, SystemJsNgModuleLoader, Inject } from '@angular/core';
import { User } from 'src/app/models/user';
import { RegisterService } from 'src/app/services/register.service';
import { MatSnackBar, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reset-lozinke',
  templateUrl: './reset-lozinke.component.html',
  styleUrls: ['./reset-lozinke.component.css']
})
export class ResetLozinkeComponent implements OnInit {

  modifikovano: User;
  stara: string;
  novaLozinka: string;
  potvrdjenaLozinka: string;
  usernameStorage:string;

  constructor(public snackBar: MatSnackBar, public dialogRef: MatDialogRef<ResetLozinkeComponent>,
          @Inject(MAT_DIALOG_DATA) public data: any, private rout: Router,private service: RegisterService) { }

  ngOnInit() {
  }

  update(){

    
         
    console.log(localStorage.getItem('usernameStorage'));
    this.usernameStorage=localStorage.getItem('usernameStorage');
      if(this.novaLozinka == "" || this.novaLozinka == undefined || this.potvrdjenaLozinka == "" || this.potvrdjenaLozinka == undefined){
        this.snackBar.open('Niste uneli sve parametre!', 'U redu', { duration: 10000 }); 
      }else if(this.novaLozinka != this.potvrdjenaLozinka){
        this.snackBar.open('Lozinke se ne poklapaju', 'U redu', { duration: 10000 });
      }else if(this.novaLozinka.length < 8){
        this.snackBar.open('Lozinka mora da ima barem 8 karaktera!', 'U redu', { duration: 10000 });
      }else {
        this.data.password = this.novaLozinka;
        this.data.usernameStorage=this.usernameStorage;
        this.service.azurirajSifraUser(this.novaLozinka,this.usernameStorage).subscribe(
          res =>{
            this.snackBar.open('Uspesno ste promenili Vasu sifru.', 'U redu', { duration: 10000 });
          }
        );
      }
  }
  cancel(){
    this.dialogRef.close();
    this.snackBar.open('Odustali ste!', 'U redu', { duration: 1000 });
  }
}