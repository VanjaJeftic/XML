import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class RegistrationServiceService {

  constructor(private http:HttpClient) { }

  public saveKorisnik(user:User){
    console.log("User se salje "+ user.adresa)
    return this.http.post('https://localhost:8662/auth/korisnik/noviKorisnik',user).subscribe(
      data=>{console.log('Vratio je '+data)}
    );
  }


}
