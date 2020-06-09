import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';
import { VrstaMenjaca } from 'src/app/models/vrsta-menjaca';

@Component({
  selector: 'app-izmena-vrsta-menjaca',
  templateUrl: './izmena-vrsta-menjaca.component.html',
  styleUrls: ['./izmena-vrsta-menjaca.component.css']
})
export class IzmenaVrstaMenjacaComponent implements OnInit {

  constructor(private  vrstaMenjacaServis:AdminService,private router: Router,private route: ActivatedRoute) { }

  public vrstaMenjaca:VrstaMenjaca=new VrstaMenjaca();

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
  this.vrstaMenjaca = JSON.parse(localStorage.getItem('vrstaMenjaca')); 
    });
}

public onSubmitIzmenaMenjaca(): void{
  event.preventDefault();
  let res=this.vrstaMenjacaServis.izmenaVrsteMenjaca(this.vrstaMenjaca);
  this.router.navigateByUrl('/listaVrstaMenjacaVozila');
}



}
