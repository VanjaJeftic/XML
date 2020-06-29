import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AdminService } from 'src/app/services/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-korisnici',
  templateUrl: './korisnici.component.html',
  styleUrls: ['./korisnici.component.css']
})
export class KorisniciComponent implements OnInit {

  users:User[];
  userlist$;

  constructor(private  userServis:AdminService,private router: Router) { 
    this.userlist$=userServis.getKorisnici();
  }

  public user:User=new User();

  ngOnInit() {
  }


  deleteKorisnik(userB: User): void {
    //console.log("brisanje klase");
    this.userServis.deleteKorisnik(userB)
      .subscribe( data => {
        window.alert("Uspesno!");
        this.users = this.users.filter(u => u !== userB);
      },err =>{
        
         console.log(err);
        window.alert("Nije moguce obrisati korisnika!");
  
      },
      () => {
      
       console.log(`We're done here!`);
     });
      
    
   };


   blok(userB: User): void {
    if(userB.nalogAktiviran==true){
      this.userServis.blok(userB)
      .subscribe( data => {
        window.alert("Uspesno!");
        this.users = this.users.filter(u => u !== userB);
      },err =>{
        
         console.log(err);
        window.alert("Nije moguce blokirati korisnika!");
  
      },
      () => {
      
       console.log(`We're done here!`);
     });
    }else{
    this.userServis.unblok(userB)
      .subscribe( data => {
        window.alert("Uspesno!");
        this.users = this.users.filter(u => u !== userB);
      },err =>{
        
         console.log(err);
        window.alert("Nije moguce odblokirati korisnika!");
  
      },
      () => {
      
       console.log(`We're done here!`);
     });
      
    }
   };



}
