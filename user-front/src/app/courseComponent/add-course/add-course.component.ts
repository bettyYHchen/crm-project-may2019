import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { Location } from '@angular/common';
@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {

  courseList: string[] = ['AUTOMATION_TESTING', 'DATA_SCIENCE', 'SCRUM_MASTER',
    'SOFTWARE_TESTING', 'AUTOMATION_TESTING_ONLINE', 'BUSINESS_ANALYSIS', 'DATA_SCIENCE_ONLINE',
      'CERTIFIED_SCRUM_MASTER', 'FULLSTACK_JAVA_DEVELOPER', 'PERFORMANCE_TESTING', 'SOFTWARE_TESTING_ONLINE'];
  postForm: FormGroup;
  validMessage = '';

  courseExample: any;


  constructor(
    private userService: UserService,
    private fb: FormBuilder,
    private location: Location,
  ) {}

  ngOnInit() {
    this.createForm();
  }


  createForm() {
    this.postForm = this.fb.group({
      name: '',
      fee: '',
      durationWeek: '',
      paymentDurationWeek: '',
      paymentDurationBiWeek: ''
    });
  }

  onSubmit() {
    if (this.postForm.valid) {
      this.validMessage = 'The course information has been saved. Thank you!';
      this.userService.createCourse(this.postForm.value).subscribe(
        data => {
          this.courseExample = data;
          this.postForm.reset();
          return true;
        },
        error => {
          alert('Couldnt create this course!'); }
      );
    } else {
      this.validMessage = 'Please fill out the form before submitting!';
    }
  }

  cancel() {
    this.location.back();
  }

}
