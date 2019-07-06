import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../auth/token-storage.service';


@Component({
  selector: 'app-pm',
  templateUrl: './pm.component.html',
  styleUrls: ['./pm.component.css']
})
export class PmComponent implements OnInit {
  username: string;

  constructor(private token: TokenStorageService) {
    this.username = this.token.getUsername();
  }

  ngOnInit() {
  }

}

