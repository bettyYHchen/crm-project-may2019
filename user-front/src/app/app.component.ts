import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import { TokenStorageService } from './auth/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
   roles: string[];
   authority: string;
   myInfoUrl: string;

  constructor(private router: Router, private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.myInfoUrl = 'whatIam/' + this.tokenStorage.getUsername();
      console.log(this.myInfoUrl);
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        } else if (role === 'ROLE_PM') {
          this.authority = 'pm';
          return false;
        } else if (role === 'ROLE_USER'){
        this.authority = 'user';
        return false;
        } else {
          this.authority = 'client';
          return true;
        }
    });
    }
  }

  logout() {
    this.router.navigate(['home']);
    this.tokenStorage.signOut();
    this.router.navigate(['auth/login']);
  }
}
