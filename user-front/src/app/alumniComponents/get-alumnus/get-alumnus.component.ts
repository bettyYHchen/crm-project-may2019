import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-get-alumnus',
  templateUrl: './get-alumnus.component.html',
  styleUrls: ['./get-alumnus.component.css']
})
export class GetAlumnusComponent implements OnInit {

    public alumnus;

    constructor(private userService: UserService) { }

    ngOnInit() {
      this.getAlumnus();
    }

    getAlumnus() {
      this.userService.listAlumnus().subscribe(
      data => {this.alumnus = data;
               console.log(data); },
      err => console.log(err),
      () => console.log('all alumnus are listed!')
    );
    }

}
