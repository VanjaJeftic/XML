import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from './../../services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Shared } from 'src/app/models/shared';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  SingIn:FormGroup;
  submitted:boolean=false;
  public isLogged:boolean=false;
  user: any;
  uAt:any;
  temp:any

  dataInvalid = false;

  constructor(private authService: AuthenticationService, private router: Router,private formBuilder:FormBuilder,private activatedRoute:ActivatedRoute,private shared:Shared) { }

  ngOnInit() {
    this.SingIn = this.formBuilder.group({
      
      username:this.username,
      password:this.password
    });
  }

  get f() { return this.SingIn.controls; }

  onSubmit(event:any) {
   
    this.submitted = true;
    this.temp = this.SingIn;
    console.log(this.username,this.password)
    this.authService.login(this.username, this.password).subscribe(
      data => {
        console.log(data);
        this.uAt = JSON.parse(data);
        this.shared.token = this.uAt.token;
        this.shared.username = this.uAt.username;
        localStorage.setItem('usernameStorage', this.username);
         
       
        localStorage.setItem('token',this.uAt.token);
        localStorage.setItem('username',this.uAt.username);
        localStorage.setItem('userId',this.uAt.userId);
        localStorage.setItem('nalogAktivan',this.uAt.nalogAktivan);
        console.log("Username storage" + localStorage.getItem('usernameStorage')+localStorage.getItem('nalogAktivan'));
        //this.router.navigateByUrl("");

        if(this.uAt.roles=='ROLE_admin'){
          console.log("ADMIN");
          window.alert("Uspesno ste se ulogovali");
          this.router.navigateByUrl('administrator');
        }else if(this.uAt.roles=='ROLE_agent'){
          console.log("agent");
          window.alert("Uspesno ste se ulogovali");
          this.router.navigateByUrl('agent');
        }else if(localStorage.getItem('nalogAktivan')=='false'){
          window.alert("Nalog nije aktivan");
        }else if(this.uAt.roles=='ROLE_user'){
          console.log("user");
          window.alert("Uspesno ste se ulogovali");
          this.router.navigateByUrl('user');
        }else{
          alert('Nazalost, doslo je do greske, proverite da li kucate dobre kredencijale');
        }
    },
    error=>{
      alert('Vas nalog nije aktivan!');
    }
    );
  }
}