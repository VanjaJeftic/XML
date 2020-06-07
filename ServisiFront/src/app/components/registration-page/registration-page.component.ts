import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { RegistrationServiceService } from 'src/app/services/registration-service.service';

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit {

  public user:User=new User();

  constructor(private  registrationService:RegistrationServiceService) { }

  ngOnInit() {
  }



  public onSubmit(): void{
    event.preventDefault();
    console.log("Usao u onsubmit usera "+ this.user.ime );
    let res=this.registrationService.saveKorisnik(this.user);
    console.log("poslato");
    
  }
  

}
