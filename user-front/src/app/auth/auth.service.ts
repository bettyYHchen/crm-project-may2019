import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, from } from 'rxjs';

import { JwtResponse } from './jwt-response';
import { AuthLoginInfo } from './login-info';
import { SignUpInfo } from './signup-info';
import { environment } from '../../environments/environment';
import { ApiResponse } from '../services/api.response';
import { TokenStorageService } from './token-storage.service';
import { ResetPasswordForm } from './reset-password-form';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  apiUrl = environment.apiUrl;
  private loginUrl = this.apiUrl + '/api/auth/signin';
  private signupUrl = this.apiUrl + '/api/auth/signup';
  private resetCredentialUrl = this.apiUrl + '/api/auth/resetpassword/';
  info: any;
  clientCredential: AuthLoginInfo;

  constructor(private http: HttpClient, private token: TokenStorageService) {
  }

  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }

  resetUserPassword(credentials: AuthLoginInfo): Observable<ApiResponse> {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()};

    return this.http.put<ApiResponse>(this.resetCredentialUrl + this.info.username, credentials, httpOptions);
  }

  resetClientPassword(email: string, resetPasswordForm: ResetPasswordForm): Observable<ApiResponse> {
    this.clientCredential  = new AuthLoginInfo(resetPasswordForm.username, resetPasswordForm.password);
    return this.http.put<ApiResponse>(this.resetCredentialUrl + email, this.clientCredential, httpOptions);
  }





}
