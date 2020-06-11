import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdminServiceService } from 'src/app/services/admin-service.service';

@Component({
  selector: 'app-activation-page',
  templateUrl: './activation-page.component.html',
  styleUrls: ['./activation-page.component.css']
})
export class ActivationPageComponent implements OnInit {

  constructor(private route: ActivatedRoute, private adminService: AdminServiceService) { }

  ngOnInit() {
    let idSelektovanog = parseInt(this.route.snapshot.paramMap.get('id'));
    console.log(idSelektovanog);

    this.adminService.aktivirajNalog(idSelektovanog).subscribe(
      data => {
        console.log(data);
      },
      error => {
        console.log('Ne moze se aktivirati!');
      }
    );

  }

}
