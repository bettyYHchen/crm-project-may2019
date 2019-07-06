import { Role } from './../model/role.model';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from "../model/user.model";
import { TokenStorageService } from '../auth/token-storage.service';
import {Router} from "@angular/router";
import { UserResponse } from '../model/user-response';
import { UserRequest } from '../model/user-request';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  username: string;

  constructor(private token: TokenStorageService) {
    this.username = this.token.getUsername();
  }

  ngOnInit() {}
}
