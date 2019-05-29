import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-get-interns',
  templateUrl: './get-interns.component.html',
  styleUrls: ['./get-interns.component.css']
})
export class GetInternsComponent implements OnInit {
  public interns;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getInterns();
  }

  getInterns() {
    this.userService.listInterns().subscribe(
      data => {this.interns = data;
               console.log(data); },
      err => console.log(err),
      () => console.log('all interns are listed!')
    );
  }
}
