import { Component, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { User } from 'src/app/models/user';
import { RegisterService } from 'src/app/services/register.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  repeatPassword: string;

  Roles: any = ['ROLE_ADMIN', 'ROLE_AGENT', 'ROLE_USER'];
  public user: User = new User();

  constructor(private registerService : RegisterService, private snackBar: MatSnackBar) { }

  ngOnInit() {
  }


  public onSubmit(): void{


    if(this.user.password.length < 10){
      this.snackBar.open('Lozinka mora da ima barem 10 karaktera!', 'U redu', {duration: 10000});
      return;
    }else {
      if(this.repeatPassword === this.user.password){
        //this.user.uloga = this.Roles[1];
     console.log("Username je "+ this.user.username + " " + this.user.email);
     window.alert("Uspesno ste se registrovali");
        let res = this.registerService.saveUser(this.user);
        res.subscribe((res)=>{
          if(res == null ){
            alert('Vec postoji korisnicki nalog sa unetim Username-om ili Email-om!');
          }else{
            alert('Uspesno ste se registrovali kao korisnik!');
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