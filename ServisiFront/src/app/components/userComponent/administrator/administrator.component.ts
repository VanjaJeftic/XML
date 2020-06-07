import { AuthenticationService } from './../../../services/authentication.service';
import { Injectable } from '@angular/core';
import { Component, OnInit } from '@angular/core';

import { MatMenuModule} from '@angular/material/menu';

@Component({
  selector: 'app-administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.css']
})
export class AdministratorComponent  {

  constructor(private authService: AuthenticationService) { }

  ngOnInit() {
  }

  onOdjaviMe(){
    this.authService.logout();
  }

  

}
