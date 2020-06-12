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

  
  marked=false;
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

  public onSubmit(): void{
  //  this.oglas.user_id=localStorage.getItem('userId');
    console.log("Vozilo id"+this.data.id);
    this.oglas.vozilo_id=this.data.id;
    //console.log(this.oglas.user_id+localStorage.getItem('userId'));
    let res=this.noviOglasService.saveOglas(this.oglas);

    window.location.href = this.rout.url;
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste!', 'U redu', { duration: 1000 });
  }



}
