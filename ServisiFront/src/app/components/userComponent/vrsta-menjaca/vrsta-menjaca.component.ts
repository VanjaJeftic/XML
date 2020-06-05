import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';
import { VrstaMenjaca } from 'src/app/models/vrsta-menjaca';

@Component({
  selector: 'app-vrsta-menjaca',
  templateUrl: './vrsta-menjaca.component.html',
  styleUrls: ['./vrsta-menjaca.component.css']
})
export class VrstaMenjacaComponent implements OnInit {

  constructor(private  vrstaMenjacaServis:AdminService) { }
  public vrstaMenjaca:VrstaMenjaca=new VrstaMenjaca();

  ngOnInit() {
  }


  public onSubmit(): void{
    event.preventDefault();
    console.log("Usao u onsubmit vrste menjaca"+ this.vrstaMenjaca.naziv );
    let res=this.vrstaMenjacaServis.saveMenjac(this.vrstaMenjaca);
    console.log("poslato");
    
  }


}
