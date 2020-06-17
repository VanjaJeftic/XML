import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PutanjaService {

  private _app_url = 'https://localhost:8088/';
  private _authentication_url = 'https://localhost:8088/auth';
  private _zahtev_url = this._app_url + 'zahtev';
  private _izvestaj_url = this._app_url + 'izvestaj';
  private _vozilo_url = this._app_url + 'vozilo';
  private _termin_url = this._app_url + 'termin';

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
  
  get get_zahtev_url(): string{
    return this._zahtev_url;
  }

  get get_izvestaj_url(): string{
    return this._izvestaj_url;
  }

  get get_vozilo_url(): string{
    return this._vozilo_url;
  }

  get get_termin_url(): string{
    return this._termin_url;
  }

  get get_search_url(): string{
    return this._app_url + 'search';
  }
}
