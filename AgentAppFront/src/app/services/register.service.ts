import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }


  public saveUser(user: User){
    return this.http.post('https://localhost:8088/auth/register', user)
  }

}
