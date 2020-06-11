import { Component, OnInit } from '@angular/core';
import { TipGoriva } from 'src/app/models/tip-goriva';
import { AdminService } from 'src/app/services/admin.service';
import { ActivatedRoute, Router } from '@angular/router';

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
