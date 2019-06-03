import { Component, OnInit } from '@angular/core';
import { UserResponse } from '../model/user-response';
import { TokenStorageService } from '../auth/token-storage.service';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-what-iam',
  templateUrl: './what-iam.component.html',
  styleUrls: ['./what-iam.component.css']
})
export class WhatIamComponent implements OnInit {

  errorMessage: string;
  user: UserResponse;
  username: string;


  constructor(private token: TokenStorageService, private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.username = this.token.getUsername();
    console.log(this.username);
    this.userService.getUserByUsername(this.username).subscribe(
      data => {
// tslint:disable-next-line: max-line-length
        this.user = new UserResponse(data.result.name, data.result.username, data.result.email, data.result.positions,data.result.status, data.result.statusAsOfDay);
        console.log(this.user);
      },
      error => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    )
  }
  addBrackets(list: string []): string [] {
    return list.map( x => '(' + x + ')');
  }

}
