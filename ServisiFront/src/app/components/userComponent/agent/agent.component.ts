import { Router } from '@angular/router';
import { AuthenticationService } from './../../../services/authentication.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-agent',
  templateUrl: './agent.component.html',
  styleUrls: ['./agent.component.css']
})
export class AgentComponent implements OnInit {

  constructor(private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {
  }

  onMojaVozila(){
    this.router.navigateByUrl('vozila');
  }

  onZahtevi(){
    this.router.navigateByUrl('zahtev');
  }

  onOdjaviMe(){
    this.authService.logout();
  }
}
