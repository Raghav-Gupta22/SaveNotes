import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root',
})
export class UserserviceService {
  isLoggedIn = false;
  constructor(private http: HttpClient) {}

  //is login or not
  public isLogIn() {
    let tokenStr = localStorage.getItem('token');
    if (tokenStr == undefined || tokenStr == '' || tokenStr == null) {
      return false;
    }
    return true;
  }

  //logout
  public logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    localStorage.removeItem('note');
    this.isLoggedIn = false;
    return true;
  }

  public setUser(user: any) {
    localStorage.setItem('user', JSON.stringify(user));
  }

  public getUser() {
    let userStr = localStorage.getItem('user');
    if (userStr != null) {
      return JSON.parse(userStr);
    }
    this.logout();
    return null;
  }
  //current User
  

  public registerUser(user: User): Observable<any> {
    return this.http.post<any>('http://localhost:3000/user/register', user);
  }
}
