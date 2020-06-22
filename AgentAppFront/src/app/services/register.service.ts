import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }


  public saveUser(user: User){
    return this.http.post('http://localhost:8088/auth/register', user)
  }


  public proveriUseraSaSifrom(user:User){
    console.log("promena sifre");
    console.log("Username je: "+user);
    return this.http.post('http://localhost:8088/auth/izmenaLozinke',user);
  
  }


  public azurirajSifraUser(password:string,username:string):Observable<User> {
    console.log("Username je "+ username + " " + password);
    let header = new HttpHeaders();
    const fd = new FormData();
    fd.append('username', username);
    fd.append('password',password);
    header.append('Content-Type', 'application/json');
    console.log("Izmena sifre");
    return this.http.post<User>('http://localhost:8088/auth/izmenaLozinke',fd);

  }
}
