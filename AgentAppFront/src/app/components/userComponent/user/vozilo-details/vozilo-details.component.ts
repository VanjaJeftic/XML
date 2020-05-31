import { OglasService } from './../../../../services/oglas.service';
import { AuthenticationService } from './../../../../services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-vozilo-details',
  templateUrl: './vozilo-details.component.html',
  styleUrls: ['./vozilo-details.component.css']
})
export class VoziloDetailsComponent implements OnInit {

  oglas;

  constructor(private authService: AuthenticationService, private route: ActivatedRoute,
              private oglasService: OglasService ) { }

  ngOnInit() {
    let idOglasa = parseInt(this.route.snapshot.paramMap.get('id'));
    this.oglasService.getOneOglas(idOglasa).subscribe(
      data => {
        console.log(data);
        this.oglas = data;
      }
    );
  }

  onRezervisi(){
  }

  onOdjaviMe(){
    this.authService.logout();
  }

}
