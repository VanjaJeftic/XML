import { VoziloDetailsDialogComponent } from './vozilo-details-dialog/vozilo-details-dialog.component';
import { Oglas } from './../../modeli/oglas';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MatTableDataSource, MatDialog } from '@angular/material';

@Component({
  selector: 'app-oglas',
  templateUrl: './oglas.component.html',
  styleUrls: ['./oglas.component.css']
})
export class OglasComponent implements OnInit {

  displayedColumns: string[] = ['vozilo', 'mesto', 'zakazi']
  oglasiSource;

  shopCartItem: any[] = [];

  constructor(private http: HttpClient, public dialog: MatDialog) { }

  ngOnInit() {
    this.getOglase().subscribe(
      data => {
        console.log(data);
        this.oglasiSource = new MatTableDataSource(data);
      }
    );
  }

  onRezervisi(elem){
    // console.log(elem);
    // this.shopCartItem.push(elem);
    // window.localStorage.setItem('ShopCartItem', JSON.stringify(this.shopCartItem));
    // console.log('Iz LocalStorage-a: ');
    // console.log(window.localStorage.getItem('ShopCartItem'));
    const dialogRef = this.dialog.open(VoziloDetailsDialogComponent, {data: elem});
    dialogRef.afterClosed().subscribe( result => {
      
    });
  }

  getOglase():Observable<Oglas[]>{
    return this.http.get<Oglas[]>('http://localhost:8088/oglas');
  }
}
