import { Component, OnInit, Inject } from '@angular/core';
import { MatSnackBar, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';
import { Agent } from 'src/app/models/agent';


@Component({
  selector: 'app-dijalog-registracija-agenta',
  templateUrl: './dijalog-registracija-agenta.component.html',
  styleUrls: ['./dijalog-registracija-agenta.component.css']
})
export class DijalogRegistracijaAgentaComponent implements OnInit {
  potvrdalozinke:string;

  public agent:Agent=new Agent();


  constructor(public snackBar: MatSnackBar, public dialogRef: MatDialogRef<DijalogRegistracijaAgentaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private rout: Router,private service: AdminService) { }

ngOnInit() {
}



cancel(){
this.dialogRef.close();
this.snackBar.open('Odustali ste!', 'U redu', { duration: 1000 });
}



public onSubmit(): void{


  if(this.agent.password.length < 10){
    this.snackBar.open('Lozinka mora da ima barem 10 karaktera!', 'U redu', {duration: 10000});
    return;
  }else {
    if(this.potvrdalozinke === this.agent.password){
      //this.user.uloga = this.Roles[1];
   console.log("Username je "+ this.agent.username);
   window.alert("Uspesno je registrovan agent");
      let res = this.service.registrujAgenta(this.agent);
      res.subscribe((res)=>{
        if(res == null ){
          alert('Vec postoji korisnicki nalog sa unetim Username-om!');
        }else{
          
          this.dialogRef.close();
          //if( res.uloga == this.Roles[1] )
        }
      });
    }else{
      this.snackBar.open('Vase Lozinke se ne podudaraju!', 'U redu', {duration: 10000});
      return;
    }
  }
}
















}
