import { Component, OnInit } from '@angular/core';
import {Vozilo} from 'src/app/models/vozilo';
import { NoviOglasService } from 'src/app/services/novi-oglas.service';


@Component({
  selector: 'novi-oglas',
  templateUrl: './novi-oglas.component.html',
  styleUrls: ['./novi-oglas.component.css']
})
export class NoviOglasComponent implements OnInit {

  public vozilo:Vozilo=new Vozilo();

  constructor(private noviOglasService:NoviOglasService) { }

  ngOnInit() {
  }

  public onSubmit(): void{
    console.log("Usao u onsubmit"+ this.vozilo.modelVozila);
    let res = this.noviOglasService.saveVozilo(this.vozilo);
  }
}
