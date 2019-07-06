import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { UserResponse } from '../model/user-response';
import { TokenStorageService } from '../auth/token-storage.service';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  username: string;

  constructor(private token: TokenStorageService) {
    this.username = this.token.getUsername();
  }

  ngOnInit() {
  }

}
