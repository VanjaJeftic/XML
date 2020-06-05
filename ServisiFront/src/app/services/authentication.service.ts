import { PutanjaService } from './../putanje/putanja.service';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, catchError} from 'rxjs/operators'
import { Router } from '@angular/router';
import { Shared } from '../models/shared';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  loggedInUser: any;

  constructor(private http: HttpClient, private router: Router, private putanjeService: PutanjaService,private share: Shared) { }

  loginn(username, password){
    const loginHeaders = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });
    const body = {
      'username' : username,
      'password' : password
    };

    return this.sendPostForLogin(this.putanjeService.login_url, body, loginHeaders)
              .pipe(map((res => {
                console.log('Login Success!');
                this.saveTokenInStorage(res.accessToken);
              })));
  }

  getLoggedInUserData(){
    return this.get(this.putanjeService.userInfo)
            .pipe(map(user => {
              this.loggedInUser = user;
              this.saveAuthoritiesInSotrage(user.authorities);
              return user;
            }));
  }

  saveTokenInStorage(token: string){
    window.sessionStorage.clear();
    window.sessionStorage.setItem('Token', token);
  }
  saveAuthoritiesInSotrage(authorities: string[]){
    window.sessionStorage.setItem('Authorities', JSON.stringify(authorities));
  }
  getTokenFromStorage(){
    return window.sessionStorage.getItem('Token');
  }
  getTokenAuthorities(){
    return window.sessionStorage.getItem('Authorities');
  }
  logout(){
    window.sessionStorage.clear();
    this.router.navigate(['/home']);
  }

  //Za TokenInterceptor neophodne metode
  tokenIsPresent(){
    if(this.getTokenFromStorage() !== null && this.getTokenFromStorage != undefined){
      return true;
    }
    return false;
  }
  getToken(){
    return this.getTokenFromStorage();
  }

  //Za slanje Username i Password kao autentifikaciju na backend
  sendPostForLogin(url: string, body: any, customHeaders?: HttpHeaders): Observable<any>{
    let headers = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/json'
    });

    return this.http.post(url, body, {headers: customHeaders || headers});
  }

  //Dobavljanje podataka ulogovanog korisnika
  get(path: string, args?: any):Observable<any>{
    let headers = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    const options = {
      headers: headers
    };

    return this.http.get(path, options)
            .pipe(catchError(this.checkError.bind(this)));
  }
  private checkError(error: any):any{
      if(error && error.status === 401){

      }else {

      }
      throw error;
  }

  login(username,password){
    const body = {
      'username' : username,
      'password' : password
    };
    return this.http.post('http://localhost:8662/auth/login',body,{responseType:'text'});
  }

}
