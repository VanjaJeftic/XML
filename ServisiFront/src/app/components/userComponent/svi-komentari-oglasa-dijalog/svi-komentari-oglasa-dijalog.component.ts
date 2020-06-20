import { Component, OnInit, SystemJsNgModuleLoader, Inject } from '@angular/core';
import { User } from 'src/app/models/user';
import { MatSnackBar, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';
import { Oglas } from 'src/app/models/oglas';
import { Komentar } from 'src/app/models/komentar';
@Component({
  selector: 'app-svi-komentari-oglasa-dijalog',
  templateUrl: './svi-komentari-oglasa-dijalog.component.html',
  styleUrls: ['./svi-komentari-oglasa-dijalog.component.css']
})
export class SviKomentariOglasaDijalogComponent implements OnInit {
  modifikovano: User;
  stara: string;
  novaLozinka: string;
  potvrdjenaLozinka: string;
  usernameStorage:string;
  public user:User=new User();
  public ogl:Oglas=new Oglas();
  public kom:Komentar=new Komentar();
  komentari: Komentar[];
  komentarlist$;
  idoglasa:string;

  constructor(public snackBar: MatSnackBar, public dialogRef: MatDialogRef<SviKomentariOglasaDijalogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private router: Router,private service: AdminService,private route: ActivatedRoute) { }

    ngOnInit(): void {
      this.route.queryParams.subscribe(params => {
      this.user = JSON.parse(localStorage.getItem('user'));  
      console.log("user data je "+ this.user.ime+ "   "+ this.user.prezime);
  
      this.ogl=JSON.parse(localStorage.getItem('oglasStorage'));
      console.log("ovo je oglas iz storage " + this.ogl.id);

      
      //localStorage.getItem('usernameStorage');
      console.log(localStorage.getItem('usernameStorage'));
      this.komentarlist$= this.service.getOdobreniKomZaovajOglas(this.ogl.id);

      });
  }


cancel(){
this.dialogRef.close();
//this.snackBar.open('Odustali ste!', 'U redu', { duration: 1000 });
}



 
}
