import { Router } from '@angular/router';
import { AuthenticationService } from './../../services/authentication.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  user: any;

  dataInvalid = false;

  constructor(private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {

  }

  onSubmit(){
    this.authService.login(this.username, this.password).subscribe(
      auth => {
        this.authService.getLoggedInUserData().subscribe(
          user => {
            console.log(user);
            localStorage.setItem('usernameStorage', this.username);
         
            console.log(localStorage.getItem('usernameStorage'));
            for(let i of user.authorities)
            if(i.authority == 'log_ADMIN'){
              window.alert("Uspesno ste se ulogovali");
              this.router.navigateByUrl('administrator');
            }else if(i.authority == 'log_AGENT'){
              window.alert("Uspesno ste se ulogovali");
              this.router.navigateByUrl('agent');
            }else if(i.authority == 'log_USER'){
              window.alert("Uspesno ste se ulogovali");
              this.router.navigateByUrl('user');
            }/*else {
              alert('Nazalost, nemate dozvolu na posetite ovu stranicu.');
            }*/
          }
        );
      },
      error => {
        console.log(error.status);
        if(error.status == 400){
          this.dataInvalid = true;
        }else if(error.status == 423){
          alert('Nalog nije aktivan');
        }
        
        else {
          alert('Ups, servis izgleda nije u funkciji. Probaj ponovo!');
        }
      }
    );
  }
}
