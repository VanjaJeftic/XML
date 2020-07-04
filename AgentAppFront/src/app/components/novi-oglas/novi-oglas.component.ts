import { Component, OnInit, Inject } from '@angular/core';
import { Oglas } from 'src/app/models/oglas';
import { NoviOglasService } from 'src/app/services/novi-oglas.service';
import { MatSnackBar, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-novi-oglas',
  templateUrl: './novi-oglas.component.html',
  styleUrls: ['./novi-oglas.component.css']
})
export class NoviOglasComponent implements OnInit {

  showKmFiled: boolean = false;
  
  marked=false;
  maxkm=false;
  cdw=false;
  public oglas:Oglas=new Oglas();

  constructor(private  noviOglasService:NoviOglasService,public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<NoviOglasComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private rout: Router) { }

  ngOnInit() {
  }

  toggleVisibility(e){
    this.oglas.cdw=e.target.checked;
  }

  toggleVisibility2(e){
    this.oglas.maxkm=e.target.checked;
  }
  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste!', 'U redu', { duration: 1000 });
  }

  public onSubmit(): void{
  //  window.alert("Uspesno ste doodali novi oglas");
  //  this.oglas.user_id=localStorage.getItem('userId');
    console.log("Vozilo id"+this.data.id);
    this.oglas.vozilo_id=this.data.id;
    //console.log(this.oglas.user_id+localStorage.getItem('userId'));
    let res=this.noviOglasService.saveOglas(this.oglas);

   // window.location.href = this.rout.url;
  }

  updateState(){
    // Reset 
    this.showKmFiled = false;
    // Itearte over plans 
   
        if(this.maxkm==true ){
          this.showKmFiled = true;
        }
      }

  }


