import { Component, OnInit } from '@angular/core';
import { PorukaComponent } from './poruka/poruka.component';
import { Poruka } from 'src/app/models/poruka';
import { PorukaService } from 'src/app/services/poruka.service';
import { Router, ActivatedRoute } from '@angular/router';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-prikaz-poruka',
  templateUrl: './prikaz-poruka.component.html',
  styleUrls: ['./prikaz-poruka.component.css']
})
export class PrikazPorukaComponent implements OnInit {

  

  poruke$;
  zahtevId : any;

  constructor(private porukaService : PorukaService,
    private router:Router,
    private activatedRoute: ActivatedRoute, private dialog: MatDialog) {
      this.zahtevId=localStorage.getItem("zahtevId");
      this.poruke$=  this.porukaService.getAllPoruke(this.zahtevId);
      console.log("Kod prikaza "+localStorage.getItem("zahtevId"));
     }

     public poruka:Poruka=new Poruka();

    ngOnInit() {
     
    }

    onPosalji(){
  

      const dialogRef = this.dialog.open(PorukaComponent, {data: this.zahtevId});
      console.log("Saljem u poruku"+this.zahtevId);
      dialogRef.afterClosed().subscribe(
      result => {
        location.reload();
      }
    );
    }
    
}