import { Component, OnInit } from '@angular/core';
import { MarkaVozila } from 'src/app/models/marka-vozila';
import { AdminService } from 'src/app/services/admin.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-marka-vozila',
  templateUrl: './marka-vozila.component.html',
  styleUrls: ['./marka-vozila.component.css']
})
export class MarkaVozilaComponent implements OnInit {

  constructor(private  markaServis:AdminService,private router: Router) { }
  public markaVozila:MarkaVozila=new MarkaVozila();

  ngOnInit() {
  }


  public onSubmit(): void{
    window.alert("Uspesno ste dodali marku vozila");
    event.preventDefault();
    console.log("Usao u onsubmit marke vozila"+ this.markaVozila.naziv );
    let res=this.markaServis.saveMarka(this.markaVozila);
    console.log("poslato");
    this.router.navigateByUrl('/listaMarkiVozila');
    
  }



}
