import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthserviceService {
  constructor(private http: HttpClient) {}

  public loginUserToken(token: any) {
    localStorage.setItem('token', token);
    return true;
  }

  public getToken() {
    return localStorage.getItem('token');
  }
  //generate token
  public generateToken(loginData: any) {
    return this.http.post<any>(
      'http://localhost:3000/generate-token',
      loginData
    );
  }
  public getCurrentUser() {
    return this.http.get('http://localhost:3000/current-user');
  }
}
