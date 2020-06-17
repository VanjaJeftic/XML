import { Component, OnInit } from '@angular/core';
import { VrstaMenjaca } from 'src/app/models/vrsta-menjaca';
import { AdminService } from 'src/app/services/admin.service';
import { ActivatedRoute, Router } from '@angular/router';

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
  window.alert("Uspesno ste izmenili vrstu menjaca!");
  event.preventDefault();
  let res=this.vrstaMenjacaServis.izmenaVrsteMenjaca(this.vrstaMenjaca);
  this.router.navigateByUrl('/listaVrstaMenjacaVozila');
}



}
