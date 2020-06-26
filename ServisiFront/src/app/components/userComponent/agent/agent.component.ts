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
    this.router.navigateByUrl('agent/vozila');
  }

  onZahtevi(){
    this.router.navigateByUrl('agent/zahtev');
  }

  onUnesiIzvestaj(){
    this.router.navigateByUrl('agent/izvestaj');
  }

  onVozilo(){
    this.router.navigateByUrl('vozilo/novoVozilo');
  }

  onOglas(){
    this.router.navigateByUrl('agent/oglas');
  }

  zahteviMess(){
    this.router.navigateByUrl('zahteviMess');
  }

  onOdjaviMe(){
    this.authService.logout();
  }
}
