import { Component, OnInit } from '@angular/core';
import { TipGoriva } from 'src/app/models/tip-goriva';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-tip-goriva',
  templateUrl: './tip-goriva.component.html',
  styleUrls: ['./tip-goriva.component.css']
})
export class TipGorivaComponent implements OnInit {

  constructor(private  gorivoServis:AdminService) { }
  public tipGoriva:TipGoriva=new TipGoriva();

  ngOnInit() {
  }

  public onSubmit(): void{
    event.preventDefault();
    console.log("Usao u onsubmit tip goriva vozila"+ this.tipGoriva.naziv );
    let res=this.gorivoServis.saveGorivo(this.tipGoriva);
    console.log("poslato");
    
  }

}
