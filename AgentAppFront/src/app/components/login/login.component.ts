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
            if(user.authorities[0].authority == 'ROLE_ADMIN'){
              this.router.navigateByUrl('administrator');
            }else if(user.authorities[0].authority == 'ROLE_AGENT'){
              this.router.navigateByUrl('agent');
            }else if(user.authorities[0].authority == 'ROLE_USER'){
              this.router.navigateByUrl('user');
            }else {
              alert('Nazalost, nemate dozvolu na posetite ovu stranicu.');
            }
          }
        );
      },
      error => {
        console.log(error.status);
        if(error.status == 400){
          this.dataInvalid = true;
        }else {
          alert('Ups, servis izgleda nije u funkciji. Probaj ponovo!');
        }
      }
    );
  }
}
