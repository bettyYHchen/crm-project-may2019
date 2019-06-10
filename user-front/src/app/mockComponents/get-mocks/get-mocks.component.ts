import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-get-mocks',
  templateUrl: './get-mocks.component.html',
  styleUrls: ['./get-mocks.component.css']
})

export class GetMocksComponent implements OnInit {

  public mocks;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getMocks();
  }

  getMocks() {
    this.userService.listMocks().subscribe(
    data => {this.mocks = data;
             console.log(data); },
    err => console.log(err),
    () => console.log('all mocks are listed!')
  );
  }

}
