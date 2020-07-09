import { PraviOglasService } from 'src/app/services/pravi-oglas.service';
import { Oglas } from 'src/app/models/oglas';
import { MAT_DIALOG_DATA, MatDialogRef, MatSnackBar } from '@angular/material';
import { Component, OnInit, Inject } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'pravi-oglas',
  templateUrl: './pravi-oglas.component.html',
  styleUrls: ['./pravi-oglas.component.css']
})
export class PraviOglasComponent implements OnInit {

  showKmFiled: boolean = false;
  
  maxkm=false;

  marked=false;
  cdw=false;
  public oglas:Oglas=new Oglas();

  constructor(private  praviOglasService:PraviOglasService,public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<PraviOglasComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private rout: Router) { }

  ngOnInit() {
  }

  toggleVisibility(e){
    this.oglas.cdw=e.target.checked;
  }

  
  toggleVisibility2(e){
    this.oglas.maxkm=e.target.checked;
  }

  public onSubmit(): void{
   
    this.oglas.user_id=localStorage.getItem('userId');
    console.log(this.data.id);
    this.oglas.vozilo_id=this.data.id;
    console.log(this.oglas.user_id+localStorage.getItem('userId'));
    let res=this.praviOglasService.saveOglas(this.oglas);

   // 
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste!', 'U redu', { duration: 1000 });
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
