import { Component, OnInit } from '@angular/core';
import { TipGoriva } from 'src/app/models/tip-goriva';
import { AdminService } from 'src/app/services/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-prikaz-svih-tipova-goriva',
  templateUrl: './prikaz-svih-tipova-goriva.component.html',
  styleUrls: ['./prikaz-svih-tipova-goriva.component.css']
})
export class PrikazSvihTipovaGorivaComponent implements OnInit {
  goriva: TipGoriva[];
  tipGorivalist$;

  constructor(private  tipGorivaServis:AdminService,private router: Router) { 
    this.tipGorivalist$= tipGorivaServis.getTipoviGorivaVozila();
  }

  public tipGoriva:TipGoriva=new TipGoriva();

  ngOnInit() {
  }

  deleteTipGoriva(tipGoriva: TipGoriva): void {
   
    this.tipGorivaServis.deleteTipGoriva(tipGoriva)
      .subscribe( data => {
        window.alert("Uspesno ste izbrisali tip goriva!");
        this.goriva = this.goriva.filter(u => u !== tipGoriva);
      })
  };


  izmenaTipaGoriva(tipGoriva){
    localStorage.setItem("tipGoriva", JSON.stringify(tipGoriva)); 
    this.router.navigate(["/izmenaTipaGorivaVozila"]);
  }



}
