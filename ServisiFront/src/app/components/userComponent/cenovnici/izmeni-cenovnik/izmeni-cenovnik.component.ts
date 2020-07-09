import { Component, OnInit } from '@angular/core';
import { CenovnikService } from 'src/app/services/cenovnik.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Cenovnik } from 'src/app/models/cenovnik';

@Component({
  selector: 'app-izmeni-cenovnik',
  templateUrl: './izmeni-cenovnik.component.html',
  styleUrls: ['./izmeni-cenovnik.component.css']
})
export class IzmeniCenovnikComponent implements OnInit {
 
    constructor(private  cenovnikServis:CenovnikService,private router: Router,private route: ActivatedRoute) { 
    }
  
    public cenovnik:Cenovnik=new Cenovnik();
  
    ngOnInit(): void {
      this.route.queryParams.subscribe(params => {
    this.cenovnik = JSON.parse(localStorage.getItem('cenovnik')); 
      });
  }
  
  
  public onSubmitIzmenaCenovnika(): void{
    window.alert("Uspesno ste izmenili cenovnik");
    event.preventDefault();
    let res=this.cenovnikServis.izmenaCenovnika(this.cenovnik);
    this.router.navigateByUrl('/cenovnici');
  }
  
  
  
  }
  