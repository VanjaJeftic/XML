import { AuthenticationService } from './../../../services/authentication.service';
import { Component, OnInit } from '@angular/core';

import { MatMenuModule} from '@angular/material/menu';

@Component({
  selector: 'app-administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.css']
})
export class AdministratorComponent implements OnInit {

  constructor(private authService: AuthenticationService) { }

  ngOnInit() {
  }

  onOdjaviMe(){
    this.authService.logout();
  }
}
