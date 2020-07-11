import { Component, OnInit } from '@angular/core';
import { CenovnikService } from 'src/app/services/cenovnik.service';
import { Router, ActivatedRoute } from '@angular/router';
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

  public stavkaCenovnika:StavkaCenovnika=new StavkaCenovnika();

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
  this.stavkaCenovnika = JSON.parse(localStorage.getItem('stavkaCenovnika')); 
  console.log("Sta imam tu "+this.stavkaCenovnika.cenovnik+this.stavkaCenovnika.id);
    });
}


public onSubmitIzmenaStavke(): void{
  window.alert("Uspesno ste izmenili cenovnik");
  event.preventDefault();
  let res=this.cenovnikServis.izmenaStavke(this.stavkaCenovnika);
  this.router.navigateByUrl('/oglasi');
}



}
