import { Component, OnInit } from '@angular/core';
import { UserResponse } from '../model/user-response';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { TokenStorageService } from '../auth/token-storage.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  board: string;
  errorMessage: string;
  users: UserResponse[];


  constructor(private router: Router, private userService: UserService, private token: TokenStorageService) { }

  ngOnInit() {
    this.userService.getUsersByTeam().subscribe(
        data => {
          this.users = data.result;
          console.log(this.users);
        },
        error => {
          this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
        }
      );
    }

  addBrackets(list: string []): string [] {
      return list.map( x => '(' + x + ')');
  }

}
