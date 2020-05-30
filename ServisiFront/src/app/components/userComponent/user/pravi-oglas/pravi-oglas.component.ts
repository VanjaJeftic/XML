import { Component, OnInit } from '@angular/core';
import { PraviOglasService } from 'src/app/services/pravi-oglas.service';
import { Oglas } from 'src/app/models/oglas';

@Component({
  selector: 'app-pravi-oglas',
  templateUrl: './pravi-oglas.component.html',
  styleUrls: ['./pravi-oglas.component.css']
})
export class PraviOglasComponent implements OnInit {

  public oglas:Oglas=new Oglas();

  constructor(private  praviOglasService:PraviOglasService) { }

  ngOnInit() {
  }

  public onSubmit(): void{

    let res=this.praviOglasService.saveOglas(this.oglas);
  }



}
