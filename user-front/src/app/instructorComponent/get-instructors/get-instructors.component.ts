import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Location } from '@angular/common';
import { Router } from '@angular/router';


@Component({
  selector: 'app-get-instructors',
  templateUrl: './get-instructors.component.html',
  styleUrls: ['./get-instructors.component.css']
})
export class GetInstructorsComponent implements OnInit {

  public instructors;

  constructor(private userService: UserService, private location: Location,  private router: Router) { }

  ngOnInit() {
    this.getInstructors();
  }

  getInstructors() {
    this.userService.listInstructors().subscribe(
      data => {this.instructors = data;
               console.log(data); },
      err => console.log(err),
      () => console.log('all instructors are listed!')
    );
  }

  cancel() {
    this.location.back();
  }

  onDelete(id: number) {
    if (confirm('Are you sure you want to delete the class?')) {
      this.userService.deleteInstructor(id)
      .subscribe(
        () => {this.router.navigate(['instructors']); },
        (error: any) => console.error(error)
      );
    }}

}
