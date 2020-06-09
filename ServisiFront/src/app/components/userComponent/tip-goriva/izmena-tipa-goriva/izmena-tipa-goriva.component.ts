import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';
import { TipGoriva } from 'src/app/models/tip-goriva';

@Component({
  selector: 'app-izmena-tipa-goriva',
  templateUrl: './izmena-tipa-goriva.component.html',
  styleUrls: ['./izmena-tipa-goriva.component.css']
})
export class IzmenaTipaGorivaComponent implements OnInit {

  constructor(private  tipGorivaServis:AdminService,private router: Router,private route: ActivatedRoute) { }

  public tipGoriva:TipGoriva=new TipGoriva();


  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
   this.tipGoriva = JSON.parse(localStorage.getItem('tipGoriva')); 
    });
  }


  public onSubmitIzmenaTipaGoriva(): void{
    event.preventDefault();
    let res=this.tipGorivaServis.izmenaTipaGoriva(this.tipGoriva);
    this.router.navigateByUrl('/listaTipaGorivaVozila');
  }

}
