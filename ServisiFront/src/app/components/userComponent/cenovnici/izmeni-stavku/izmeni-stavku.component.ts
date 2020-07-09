import { Component, OnInit } from '@angular/core';
import { CenovnikService } from 'src/app/services/cenovnik.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Cenovnik } from 'src/app/models/cenovnik';
import { StavkaCenovnika } from 'src/app/models/stavka-cenovnika';

@Component({
  selector: 'app-izmeni-stavku',
  templateUrl: './izmeni-stavku.component.html',
  styleUrls: ['./izmeni-stavku.component.css']
})
export class IzmeniStavkuComponent implements OnInit {

  cenovnici$;
  constructor(private  cenovnikServis:CenovnikService,private router: Router,private route: ActivatedRoute) { 
    this.cenovnici$=cenovnikServis.getCenovnici();
  }

  public stavka:StavkaCenovnika=new StavkaCenovnika();

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
  this.stavka = JSON.parse(localStorage.getItem('stavka')); 
    });
}


public onSubmitIzmenaStavke(): void{
  window.alert("Uspesno ste izmenili cenovnik");
  event.preventDefault();
  let res=this.cenovnikServis.izmenaStavke(this.stavka);
  this.router.navigateByUrl('/svioglasi');
}



}
