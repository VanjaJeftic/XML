import { Component, OnInit } from '@angular/core';
import { MarkaVozila } from 'src/app/models/marka-vozila';
import { AdminService } from 'src/app/services/admin.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-izmena-marke-vozila',
  templateUrl: './izmena-marke-vozila.component.html',
  styleUrls: ['./izmena-marke-vozila.component.css']
})
export class IzmenaMarkeVozilaComponent implements OnInit {


  constructor(private  markaServis:AdminService,private router: Router,private route: ActivatedRoute) { 
  }
  public markaVozila:MarkaVozila=new MarkaVozila();

  ngOnInit(): void {
      this.route.queryParams.subscribe(params => {
     this.markaVozila = JSON.parse(localStorage.getItem('markaVozila')); 
      });
  }
  
  public onSubmitIzmena(): void{
    window.alert("Uspesno ste izmenili marku vozila");
    event.preventDefault();
    console.log("Usao u onsubmit izmena marke vozila"+ this.markaVozila.naziv );
    let res=this.markaServis.izmenaMarke(this.markaVozila);
    console.log("poslato");
    this.router.navigateByUrl('/listaMarkiVozila');
    
    
  }





}

