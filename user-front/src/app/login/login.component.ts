import { Component, OnInit } from '@angular/core';

import { AuthService } from '../auth/auth.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { AuthLoginInfo } from '../auth/login-info';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: any;
  authority: string;
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo: AuthLoginInfo;
  dashboardUrl: string;
  role: string;

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private router: Router) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
    }
    console.log(this.tokenStorage.getToken());
    this.roleAccess();
    this.dashboardUrl = '/' + this.authority;
  }

  onSubmit() {
    console.log(this.form);

    this.loginInfo = new AuthLoginInfo(
      this.form.username,
      this.form.password);

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        this.reloadPage();
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  roleAccess() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();

    // Find the User ROLE
      this.roles.find(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          this.role = 'administrator';
          return false;
        } else if (role === 'ROLE_PM') {
          this.authority = 'pm';
          this.role = 'sales';
          return false;
        } else if (role === 'ROLE_USER') {
        this.authority = 'user';
        this.role = 'account';
        return false;
        } else {
          this.authority = 'client';
          this.role = 'client';
          return true;
        }
      });

      this.username = this.tokenStorage.getUsername();
    }
  }

  redirectToDashboard() {
    if (this.router.url === '/auth/login') {
      window.location.reload();
      // if (this.router.url === '/auth/login') {
      //   setTimeout(() => {
      //     this.router.navigate(['/dashboard/']);
      //   }, 1000 );
      // }
    } else {
 // Stay here or do something
 // for the case 'However if he is on a safe page I don't want to redirected him.'
    }

    // window.location.reload();

  }

  reloadPage() {
    window.location.reload();
  }
}
