import { Component, OnInit } from '@angular/core';
import { Cenovnik } from 'src/app/models/cenovnik';
import { CenovnikService } from 'src/app/services/cenovnik.service';
import { MatSnackBar, MatDialogRef } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cenovnik',
  templateUrl: './cenovnik.component.html',
  styleUrls: ['./cenovnik.component.css']
})
export class CenovnikComponent implements OnInit {

  public cenovnik:Cenovnik=new Cenovnik();

  constructor(private cenovnikService:CenovnikService,public snackBar: MatSnackBar, public dialogRef: MatDialogRef<CenovnikComponent>,private rout: Router) { }

  ngOnInit() {
  }

  public onSubmit(): void{

    let res=this.cenovnikService.saveCenovnik(this.cenovnik);

  }

}
