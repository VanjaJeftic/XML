import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PutanjaService {

  private _app_url = 'http://localhost:8662/';
  private _authentication_url = 'http://localhost:8662/auth';
  private _oglas_url='http://localhost:8092/'

  constructor() { }

  private _login_url = this._authentication_url + '/login';
    get login_url(): string {
        return this._login_url;
    }
  
  private _user_info_url = this._app_url + 'user/getUserInfo'
    get userInfo(): string{
        return this._user_info_url;
    }

  private _novi_oglas_url=this._oglas_url+"novoVozilo";
    get novoVozilo():string{
        return this._novi_oglas_url;
    }
}
