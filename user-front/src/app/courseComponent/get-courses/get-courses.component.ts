import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Location } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-get-courses',
  templateUrl: './get-courses.component.html',
  styleUrls: ['./get-courses.component.css']
})
export class GetCoursesComponent implements OnInit {

  public courses;

  constructor(private userService: UserService, private location: Location, private router: Router) { }

  ngOnInit() {
    this.getCourses();
  }

  getCourses() {
    this.userService.listCourses().subscribe(
      data => {this.courses = data;
               console.log(data); },
      err => console.log(err),
      () => console.log('all courses are listed!')
    );
  }

  onDelete(id: number) {
    if (confirm('Are you sure you want to delete the course?')) {
      this.userService.deleteCourse(id)
      .subscribe(
        () => {this.reloadPage(); },
        (error: any) => console.error(error)
      );
    }}

  cancel() {
    this.location.back();
  }

  reloadPage() {
    window.location.reload();
  }
}
