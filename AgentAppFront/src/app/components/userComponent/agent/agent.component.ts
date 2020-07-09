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
    this.router.navigateByUrl('agent/vozilo');
  }

  onZahtevi(){
    this.router.navigateByUrl('agent/zahtev');
  }

  onUnesiIzvestaj(){
    this.router.navigateByUrl('agent/izvestaj');
  }

  onOdjaviMe(){
    this.authService.logout();
  }

  novo(){
    this.router.navigateByUrl('novoVozilo');
  }

  sva(){
    this.router.navigateByUrl('svaVozila');
  }

  zahteviMess(){
    this.router.navigateByUrl('zahteviMess');
  }

  prihvaceni(){
    this.router.navigateByUrl('posalji');
  }

  cenovnici(){
    this.router.navigateByUrl('cenovnici');
  }

  sviMojiOglasi(){
    this.router.navigateByUrl('oglasi');
  }

}
