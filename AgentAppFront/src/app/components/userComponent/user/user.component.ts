import { Router } from '@angular/router';
import { OglasService } from './../../../services/oglas.service';
import { Oglas } from './../../../models/oglas';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AuthenticationService } from './../../../services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  displayedColumns: string[] = ['Vlasnik', 'Mesto', 'Klasa', 'Model' ,'zakazi']
  oglasiSource;

  constructor(private authService: AuthenticationService, private oglasService: OglasService, private router: Router) { }

  ngOnInit() {

    this.oglasService.getAllOglasi().subscribe(
      data => {
        this.oglasiSource = new MatTableDataSource(data);
      }
    );

  }

  onRezervisi(selectedOglas){
    console.log(selectedOglas);
    this.router.navigateByUrl('vozilo/' + selectedOglas.id);
  }

  onOdjaviMe(){
    this.authService.logout();
  }
}
