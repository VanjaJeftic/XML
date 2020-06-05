import { PutanjaService } from './../putanje/putanja.service';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, catchError} from 'rxjs/operators'
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  loggedInUser: any;
  TOKEN_KEY : string;
  constructor(private http: HttpClient, private router: Router, private putanjeService: PutanjaService) {this.TOKEN_KEY="jwtToken"; }


  getJwtToken() {
    return localStorage.getItem(this.TOKEN_KEY);
  };

   setJwtToken(token) {
      localStorage.setItem(this.TOKEN_KEY, token);
  };

  removeJwtToken() {
      localStorage.removeItem(this.TOKEN_KEY);
  };


  createAuthorizationTokenHeader() {
    var token = this.getJwtToken();
    if (token) {
        return {
          "Authorization": "Bearer " + token,
          'Content-Type': 'application/json'
        };
    } else {
        return {
          'Content-Type': 'application/json'
        };
    }
}
  login(username, password){
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

    loginUser(username, password) {
      console.log('Usao u loginUser');
      let user={
           "email": username,
           "password": password
               };
      console.log(username,password);
      return this.http.post('http://localhost:8095/login', user, {headers: this.createAuthorizationTokenHeader()});
    }
      
  getLogged(token: string) {
      return this.http.post('http://localhost:8662/auth/userprofile', token, {headers: this.createAuthorizationTokenHeader()});
    }
      
  logOut() {
      return this.http.get('http://localhost:8662/auth/logout', {headers: this.createAuthorizationTokenHeader()});
    }
}
