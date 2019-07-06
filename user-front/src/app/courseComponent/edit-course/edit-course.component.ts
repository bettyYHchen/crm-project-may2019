import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-edit-course',
  templateUrl: './edit-course.component.html',
  styleUrls: ['./edit-course.component.css']
})
export class EditCourseComponent implements OnInit {

  editForm: FormGroup;
  validMessage = '';
  private sub: Subscription;
  message: string;
  courseExample: any;


  constructor(private fb: FormBuilder, private route: ActivatedRoute, private userService: UserService, private router: Router) {
  }

  ngOnInit() {
  this.updateForm();
  this.sub = this.route.paramMap.subscribe(
    params => {
      const name = params.get('name');
      this.getCourse(name);
    }
  );
  }


  getCourse(name: string): void {
    this.userService.listCourseByName(name)
    .subscribe(
      data => {
        console.log(data);
        this.displayForm( data);
        },
      (error: any) => console.error(error)
    );
  }

  updateForm() {
  this.editForm = this.fb.group({
    name: '',
    fee: '',
    durationWeek: '',
    paymentDurationWeek: '',
    paymentDurationBiWeek: '',
    depositAmount: ''
  });

  }


  displayForm(data: any): void {
  if (this.editForm) {
    this.editForm.reset();
  }
  this.courseExample = data;
  console.log(this.courseExample);
  this.editForm.patchValue({
    name: this.courseExample.name,
    fee: this.courseExample.fee,
    durationWeek: this.courseExample.durationWeek,
    paymentDurationWeek: this.courseExample.paymentDurationWeek,
    paymentDurationBiWeek: this.courseExample.paymentDurationBiWeek,
    depositAmount: this.courseExample.depositAmount
  });

  }

  onUpdate() {
  if (this.editForm.valid) {
    this.validMessage = 'Course information has been updated!';
    this.userService.updateCourse(this.route.snapshot.params.name, this.editForm.value).subscribe(
      data => {
        this.message = 'The course has been updated!';
        this.router.navigate(['courses']);
        return true;
      },
      error => {
        alert('Couldnt update this course!'); });
  } else {
    this.validMessage = 'Please make sure the inputs are valid!';
  }
  }



}


