import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';
import { Router, ActivatedRoute } from '@angular/router';
import { KlasaVozila } from 'src/app/models/klasa-vozila';

@Component({
  selector: 'app-izmena-klase-vozila',
  templateUrl: './izmena-klase-vozila.component.html',
  styleUrls: ['./izmena-klase-vozila.component.css']
})
export class IzmenaKlaseVozilaComponent implements OnInit {
  
  constructor(private  klasaVozilaServis:AdminService,private router: Router,private route: ActivatedRoute) { 
    
  }
  

  public klasaVozila:KlasaVozila=new KlasaVozila();

    ngOnInit(): void {
      this.route.queryParams.subscribe(params => {
    this.klasaVozila = JSON.parse(localStorage.getItem('klasaVozila')); 
      });
  }

public onSubmitIzmenaKlase(): void{
  event.preventDefault();
  let res=this.klasaVozilaServis.izmenaKlase(this.klasaVozila);
  this.router.navigateByUrl('/listaKlasaVozila');
}


}
