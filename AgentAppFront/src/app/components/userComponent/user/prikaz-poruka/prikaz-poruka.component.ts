import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ZahtevService } from 'src/app/services/zahtev.service';
import { PorukaService } from 'src/app/services/poruka.service';
import { Poruka } from 'src/app/models/poruka';
import { PorukaComponent } from '../poruka-zahtev/poruka/poruka.component';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-prikaz-poruka',
  templateUrl: './prikaz-poruka.component.html',
  styleUrls: ['./prikaz-poruka.component.css']
})
export class PrikazPorukaComponent implements OnInit {


  poruke$;
  bundleId : any;

  constructor(private porukaService : PorukaService,
    private router:Router,
    private activatedRoute: ActivatedRoute, private dialog: MatDialog) {
      this.bundleId=localStorage.getItem("zahtevId");
      this.poruke$=  this.porukaService.getAllPoruke(this.bundleId);
      console.log("Kod prikaza "+localStorage.getItem("zahtevId"));
     }

     public poruka:Poruka=new Poruka();

    ngOnInit() {
     
    }

    onPosalji(){
  

      const dialogRef = this.dialog.open(PorukaComponent, {data: this.bundleId});
      console.log("Saljem u poruku"+this.bundleId);
      dialogRef.afterClosed().subscribe(
      result => {
        location.reload();
      }
    );
    }
    
}
