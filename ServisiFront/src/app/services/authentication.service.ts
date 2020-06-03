import { PutanjaService } from './../putanje/putanja.service';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, } from '@angular/common/http';
import { map, catchError} from 'rxjs/operators'
import { Router } from '@angular/router';
import { Headers, RequestOptions, Http, Response, URLSearchParams } from '@angular/http';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  loggedInUser: any;

  constructor(private http: HttpClient, private router: Router, private putanjeService: PutanjaService) { }

  login(username, password){
    const loginHeaders = new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type' : 'application/x-www-form-urlencoded; charset=utf-8"'
    });
    const body = {
      'username' : username,
      'password' : password,
    };

    var data = {
      grant_type:"password", 
      username: username, 
        password: password, 
        client_id: "ecom_app"
    };

    return this.sendPostForLogin(this.putanjeService.login_url, body, loginHeaders)
              .pipe(map((res => {var token=(res.loginHeaders()['x-token'] .headers()['x-token']);
              sessionStorage.setItem("xtoken", token);
               return $http.get(sessionStorage.getItem('apiUrl')+'/customer/customer')
               .then(
                   function(response){
                     return response.data;
                   }, 
                   function(errResponse){
                     console.error('Error while getting user info '+errResponse);
                     return $q.reject(errResponse);
                   }
               );
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
  
  validateUser: function(body){ 
		  
    var data = {
          grant_type:"password", 
          username: userCredentialDTO.userName, 
            password: userCredentialDTO.password, 
            client_id: "ecom_app"
        };
    
   var encodedData = $httpParamSerializer(data);
    var authUrl=sessionStorage.getItem('apiUrl')+"/security/oauth/token"
    var req = {
              method: 'POST', 
              url: authUrl,
              headers: {
                  "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
              },
              data: encodedData
          };

     return $http(req)
    .then(
        function(response){
             var token=(response.headers()['x-token']);
           sessionStorage.setItem("xtoken", token);
            return $http.get(sessionStorage.getItem('apiUrl')+'/customer/customer')
            .then(
                function(response){
                  return response.data;
                }, 
                function(errResponse){
                  console.error('Error while getting user info '+errResponse);
                  return $q.reject(errResponse);
                }
            );
        }, 
        function(errResponse){
          console.error('Error while validating user'+errResponse);
          return $q.reject(errResponse);
        }
    );  
    
    

     }
}
