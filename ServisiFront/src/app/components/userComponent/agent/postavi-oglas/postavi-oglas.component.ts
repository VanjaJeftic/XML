import { Router } from '@angular/router';
import { TerminZauzecaDialogComponent } from './../termin-zauzeca-dialog/termin-zauzeca-dialog.component';
import { VoziloView } from './../../../../models/vozilo-view';
import { VoziloService } from './../../../../services/vozilo.service';
import { AuthenticationService } from './../../../../services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { PraviOglasComponent } from '../../user/pravi-oglas/pravi-oglas.component';

@Component({
  selector: 'app-postavi-oglas',
  templateUrl: './postavi-oglas.component.html',
  styleUrls: ['./postavi-oglas.component.css']
})
export class PostaviOglasComponent implements OnInit {

  displayedColumns: string[] = ['Klasa', 'Model', 'Marka', 'KM' ,'Zauzece', 'Detaljnije']
  voziloSource;

  constructor(private authService: AuthenticationService, private voziloService: VoziloService,
              private dialog: MatDialog, private router: Router) { }

  ngOnInit() {
    this.voziloService.getVozilaAgentaOglas().subscribe(
      data => {
        console.log(data);
        this.voziloSource = new MatTableDataSource(data);
      }
    );
  }
  public openDialogOglas(id: number) {

    const dialogRef = this.dialog.open(PraviOglasComponent, {
      data: { id: id }
      
      
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(id);

    });

  }

  onOglas(element){
    console.log("Saljem u oglas "+element.id);
    const dialogRef = this.dialog.open(PraviOglasComponent, {data:{ id:element.id}});
    dialogRef.afterClosed().subscribe(
      result => {
        
      }
    );
  }

  onDetaljnije(element){
    alert('U izradi!');
  }

  onOdjaviMe(){
    this.authService.logout();
  }

}
