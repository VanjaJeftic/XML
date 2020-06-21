import { OnInit, Inject, Component } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

import { Oglas } from 'src/app/models/oglas';
import { KomentarService } from 'src/app/services/komentar.service';
import { Router } from '@angular/router';
import { Komentar } from 'src/app/models/komentar';

@Component({
  selector: 'app-komentar-forma',
  templateUrl: './komentar-forma.component.html',
  styleUrls: ['./komentar-forma.component.css']
})
export class KomentarFormaComponent implements OnInit {

  public komentar:Komentar=new Komentar();
public oglas:Oglas=new Oglas();
  constructor(private  komentarServis:KomentarService,private router: Router,
    public dialogRef: MatDialogRef<KomentarFormaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Komentar) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit() {
    this.komentar.korisnik_id = localStorage.getItem('userId');

    this.komentar.oglas_id=Number(localStorage.getItem('idOglasStorage'));
   
    console.log("id je: "+localStorage.getItem('idOglasStorage'));
    this.oglas=JSON.parse(localStorage.getItem('ceoSelektovaniOglas'));
    //localStorage.getItem('usernameStorage');
    console.log(localStorage.getItem('usernameStorage'));
  }

  
  public onSubmit(): void{
    window.alert("Uspesno ste dodali komentar");
    event.preventDefault();
    console.log("Usao u onsubmit komentara"+ this.komentar.sadrzaj );
    let res=this.komentarServis.saveKomentar(this.komentar);
    console.log("poslato");
    this.router.navigateByUrl('/user');
    
  }

}
