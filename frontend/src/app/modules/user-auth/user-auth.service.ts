import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 
// @class
import { Observable } from 'rxjs';
import  jwt_decode from 'jwt-decode';
import { User } from './user';
import { HTTP_OPTIONS } from 'src/app/header.config';
export const TOKEN_NAME:string = "jwt_token";
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  
  authServiceEndpoint:string = "http://localhost:9001/api/v1/userservice";

  constructor(private http: HttpClient) { }

  registerUser(newUser: User) {
    const url = `${this.authServiceEndpoint}/register`;
    console.log(url);
    return this.http.post(url, newUser, { headers : HTTP_OPTIONS.headers, responseType: 'text'});
  }
  getToken() {
    return localStorage.getItem(TOKEN_NAME);
  }

  deleteToken() {
    return localStorage.removeItem(TOKEN_NAME);
  }
  setToken(token:string) {
    return localStorage.setItem(TOKEN_NAME, token);
  }
  loginUser(newUser: User) {
    const url = `${this.authServiceEndpoint}/login`;
    console.log(url);
    return this.http.post(url, newUser, HTTP_OPTIONS);
  }
  getTokenExpirationDate(token: string) {
    const decoded = jwt_decode(token);
    if(decoded['exp'] === undefined) {
      return null;
    }
    const date = new Date(0);
    date.setUTCSeconds(decoded['exp']);
    return date;
  }

  isTokenExpired(token?: string): boolean {
    if(!token) {
      token = this.getToken();
    }
    if(!token) {
      return true;
    }
    const date = this.getTokenExpirationDate(token);
    if(date === undefined || date === null) {
      return false;
    }
    return !(date.valueOf() > new Date().valueOf());
  }
}
