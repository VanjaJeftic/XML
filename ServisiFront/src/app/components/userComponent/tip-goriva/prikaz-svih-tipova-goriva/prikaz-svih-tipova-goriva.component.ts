import { Component, OnInit } from '@angular/core';
import { TipGoriva } from 'src/app/models/tip-goriva';
import { AdminService } from 'src/app/services/admin.service';
import { TipGorivaComponent } from '../tip-goriva.component';

@Component({
  selector: 'app-prikaz-svih-tipova-goriva',
  templateUrl: './prikaz-svih-tipova-goriva.component.html',
  styleUrls: ['./prikaz-svih-tipova-goriva.component.css']
})
export class PrikazSvihTipovaGorivaComponent implements OnInit {
  goriva: TipGoriva[];
  tipGorivalist$;

  constructor(private  tipGorivaServis:AdminService) { 
    this.tipGorivalist$= tipGorivaServis.getTipoviGorivaVozila();
  }

  public tipGoriva:TipGoriva=new TipGoriva();

  ngOnInit() {
  }

  deleteTipGoriva(tipGoriva: TipGoriva): void {
    console.log("brisanje tipa goriva");
    this.tipGorivaServis.deleteTipGoriva(tipGoriva)
      .subscribe( data => {
        this.goriva = this.goriva.filter(u => u !== tipGoriva);
      })
  };
}

