import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistrationServiceService {

  constructor(private http:HttpClient) { }

  public saveKorisnik(user:User){
    console.log("User se salje "+ user.adresa)
    return this.http.post('https://localhost:8662/auth/korisnik/noviKorisnik',user).subscribe(
      data=>{console.log('Vratio je '+data);
      window.alert("Uspesno ste se registrovali");}
   
    );
  }

  public azurirajSifraUser(password:string,username:string):Observable<User> {
    console.log("Username je "+ username + " " + password);
    let header = new HttpHeaders();
    const fd = new FormData();
    fd.append('username', username);
    fd.append('password',password);
    header.append('Content-Type', 'application/json');
    console.log("Izmena sifre");
    return this.http.post<User>('https://localhost:8662/auth/korisnik/izmenaLozinke',fd);

  }

}
