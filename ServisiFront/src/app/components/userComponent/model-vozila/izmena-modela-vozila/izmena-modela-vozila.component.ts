import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';
import { ModelVozila } from 'src/app/models/model-vozila';

@Component({
  selector: 'app-izmena-modela-vozila',
  templateUrl: './izmena-modela-vozila.component.html',
  styleUrls: ['./izmena-modela-vozila.component.css']
})
export class IzmenaModelaVozilaComponent implements OnInit {
marke$;
  constructor(private  modelServis:AdminService,private router: Router,private route: ActivatedRoute) { 
    this.marke$= modelServis.getMarkeVozila();
  }

  public modelVozila:ModelVozila=new ModelVozila();

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
  this.modelVozila = JSON.parse(localStorage.getItem('modelVozila')); 
    });
}


public onSubmitIzmenaModela(): void{
  window.alert("Uspesno ste izmenili model vozila");
  event.preventDefault();
  let res=this.modelServis.izmenaModelaVozila(this.modelVozila);
  this.router.navigateByUrl('/listaModelaVozila');
}



}
