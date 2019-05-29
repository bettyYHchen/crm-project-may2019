import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-get-students',
  templateUrl: './get-students.component.html',
  styleUrls: ['./get-students.component.css']
})
export class GetStudentsComponent implements OnInit {

  public students;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getStudents();
  }

  getStudents() {
    this.userService.listStudents().subscribe(
      data => {this.students = data;
               console.log(data); },
      err => console.log(err),
      () => console.log('all students are listed!')
    );
  }

}
