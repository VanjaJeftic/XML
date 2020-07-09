import { Component, OnInit } from '@angular/core';
import { PraviOglasService } from 'src/app/services/pravi-oglas.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Oglas } from 'src/app/models/oglas';

@Component({
  selector: 'app-izmeni-oglas',
  templateUrl: './izmeni-oglas.component.html',
  styleUrls: ['./izmeni-oglas.component.css']
})
export class IzmeniOglasComponent implements OnInit {

  showKmFiled: boolean = false;
  
  maxkm=false;

  marked=false;
  cdw=false;

  constructor(private  oglasServis:PraviOglasService,private router: Router,private route: ActivatedRoute) { 

  }

  public oglas:Oglas=new Oglas();

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
  this.oglas = JSON.parse(localStorage.getItem('oglas')); 
  console.log("Sta imam tu "+this.oglas.id);
    });
}


toggleVisibility(e){
  this.oglas.cdw=e.target.checked;
}

 
toggleVisibility2(e){
  this.oglas.maxkm=e.target.checked;
}


public onSubmitIzmenaOglasa(): void{
  window.alert("Uspesno ste izmenili oglas");
  event.preventDefault();
  let res=this.oglasServis.izmenaOglasa(this.oglas);
  this.router.navigateByUrl('/svioglasi');
}

updateState(){
  // Reset 
  this.showKmFiled = false;
  // Itearte over plans 
 
      if(this.maxkm==true ){
        this.showKmFiled = true;
      }
    }



}
