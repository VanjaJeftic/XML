import { Component, OnInit } from '@angular/core';
import { SvaVozilaService } from 'src/app/services/sva-vozila.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Vozilo } from 'src/app/models/vozilo';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-izmena-vozila',
  templateUrl: './izmena-vozila.component.html',
  styleUrls: ['./izmena-vozila.component.css']
})
export class IzmenaVozilaComponent implements OnInit {

  marke$;
  model$;
  klasa$;
  gorivo$;
  menjac$;
  constructor(private  vozilaServis:SvaVozilaService,private router: Router,private route: ActivatedRoute,private modelService:AdminService) {
    this.marke$= modelService.getMarkeVozila();
    this.model$=modelService.getModelVozila();
    this.klasa$=modelService.getKlaseVozila();
    this.menjac$=modelService.getVrsteMenjacaVozila();
    this.gorivo$=modelService.getTipoviGorivaVozila();

  }

  public vozilo:Vozilo=new Vozilo();

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
  this.vozilo = JSON.parse(localStorage.getItem('vozilo')); 
    });
}


public onSubmitIzmenaCenovnika(): void{
  window.alert("Uspesno ste izmenili vozilo");
  event.preventDefault();
  let res=this.vozilaServis.izmenaVozila(this.vozilo);
  this.router.navigateByUrl('/svavozila');
}



}
