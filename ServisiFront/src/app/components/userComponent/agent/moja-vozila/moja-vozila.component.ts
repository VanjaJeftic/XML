import { VoziloView } from './../../../../models/vozilo-view';
import { VoziloService } from './../../../../services/vozilo.service';
import { AuthenticationService } from './../../../../services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-moja-vozila',
  templateUrl: './moja-vozila.component.html',
  styleUrls: ['./moja-vozila.component.css']
})
export class MojaVozilaComponent implements OnInit {

  displayedColumns: string[] = ['Vlasnik', 'Klasa', 'Model', 'Zauzece']
  voziloSource;

  constructor(private authService: AuthenticationService, private voziloService: VoziloService) { }

  ngOnInit() {
    this.voziloService.getVozilaAgenta().subscribe(
      data => {
        console.log(data);
        this.voziloSource = new MatTableDataSource(data);
      }
    );
  }

  onUnesiZauzece(element){
    console.log(element);
  }
  onOdjaviMe(){
    this.authService.logout();
  }

}
