import { Component, OnInit, Inject } from '@angular/core';
import { StavkaCenovnika } from 'src/app/models/stavka-cenovnika';
import { CenovnikService } from 'src/app/services/cenovnik.service';
import { Router } from '@angular/router';
import { MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-stavka-cenovnika',
  templateUrl: './stavka-cenovnika.component.html',
  styleUrls: ['./stavka-cenovnika.component.css']
})
export class StavkaCenovnikaComponent implements OnInit {

  cenovnici$;
  public stavkaCenovnika:StavkaCenovnika=new StavkaCenovnika();

  constructor(private stavkaServis:CenovnikService,private router: Router,@Inject(MAT_DIALOG_DATA) public data: any) {

    this.cenovnici$=stavkaServis.getCenovnici();

   }

  ngOnInit() {
  }

  public onSubmit(): void{

    console.log("Vozilo id"+this.data.id);
    this.stavkaCenovnika.oglas_id=this.data.id;
    let res=this.stavkaServis.saveStavka(this.stavkaCenovnika);

  }

}
