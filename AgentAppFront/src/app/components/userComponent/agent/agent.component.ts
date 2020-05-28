import { AuthenticationService } from './../../../services/authentication.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-agent',
  templateUrl: './agent.component.html',
  styleUrls: ['./agent.component.css']
})
export class AgentComponent implements OnInit {

  constructor(private authService: AuthenticationService) { }

  ngOnInit() {
  }

  onOdjaviMe(){
    this.authService.logout();
  }
}
