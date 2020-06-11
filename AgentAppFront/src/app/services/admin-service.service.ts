import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {

  constructor(private http: HttpClient) { }


  public aktivirajNalog(id: number){
    return this.http.post('https://localhost:8088/auth/aktivirajNalog', id);
  }

}
