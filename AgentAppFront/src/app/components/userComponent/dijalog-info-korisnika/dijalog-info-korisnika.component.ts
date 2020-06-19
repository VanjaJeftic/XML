import { Component, OnInit, SystemJsNgModuleLoader, Inject } from '@angular/core';
import { User } from 'src/app/models/user';
import { RegisterService } from 'src/app/services/register.service';
import { MatSnackBar, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-dijalog-info-korisnika',
  templateUrl: './dijalog-info-korisnika.component.html',
  styleUrls: ['./dijalog-info-korisnika.component.css']
})
export class DijalogInfoKorisnikaComponent implements OnInit {


  modifikovano: User;
  stara: string;
  novaLozinka: string;
  potvrdjenaLozinka: string;
  usernameStorage:string;
  public user:User=new User();
  constructor(public snackBar: MatSnackBar, public dialogRef: MatDialogRef<DijalogInfoKorisnikaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private router: Router,private service: AdminService,private route: ActivatedRoute) { }

    ngOnInit(): void {
      this.route.queryParams.subscribe(params => {
      this.user = JSON.parse(localStorage.getItem('user'));  
      console.log("user data je "+ this.user.firstname+ "   "+ this.user.lastname);
  
      });
  }


cancel(){
this.dialogRef.close();
//this.snackBar.open('Odustali ste!', 'U redu', { duration: 1000 });
}
}