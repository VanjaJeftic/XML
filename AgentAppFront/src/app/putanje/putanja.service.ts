import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PutanjaService {

  private _app_url = 'http://localhost:8088/';
  private _authentication_url = 'http://localhost:8088/auth';

  constructor() { }

  private _login_url = this._authentication_url + '/login';
    get login_url(): string {
        return this._login_url;
    }
  
  private _user_info_url = this._app_url + 'user/getUserInfo';
    get userInfo(): string{
        return this._user_info_url;
    }
  
  private _oglas_url = this._app_url + 'oglas';
    get get_oglas_url(): string {
      return this._oglas_url;
    }
}
