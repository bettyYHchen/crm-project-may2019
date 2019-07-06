import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-crm-header',
  templateUrl: './crm-header.component.html',
  styleUrls: ['./crm-header.component.css']
})
export class CrmHeaderComponent implements OnInit {

  roles: string[];
   authority: string;
   myInfoUrl: string;
   title = 'BusyQA';
   showHideitem: boolean;
   public isCollapsed = false;

   constructor(private http: HttpClient,
               private router: Router,
               private tokenStorage: TokenStorageService,
    ) {}

  ngOnInit() {
    this.showHideitem = false;
    this.showNav();
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
        } else if (role === 'ROLE_USER') {
        this.authority = 'user';
        return false;
        } else {
          this.authority = 'client';
          return true;
        }
    });
    }
  }

  showNav() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.showHideitem = true;
    }
  }

  logout() {
    this.router.navigate(['home']);
    this.tokenStorage.signOut();
    this.router.navigate(['auth/login']);
  }

}
