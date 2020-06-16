import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { RegistrationServiceService } from 'src/app/services/registration-service.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit {

  public user:User=new User();

  constructor(private  registrationService:RegistrationServiceService,private snackBar: MatSnackBar) { }

  ngOnInit() {
  }



  public onSubmit(): void{
    if(this.user.password.length < 10){
      this.snackBar.open('Lozinka mora da ima barem 10 karaktera!', 'U redu', {duration: 10000});
      return;
    }else{
      if(this.user.potvrdalozinke === this.user.password){
        event.preventDefault();
        console.log("Usao u onsubmit usera "+ this.user.ime );
        let res=this.registrationService.saveKorisnik(this.user);
        console.log("poslato");
      }else{
           this.snackBar.open('Vase Lozinke se ne podudaraju!', 'U redu', {duration: 10000});
        return;
      }
    }
  }
  

}
