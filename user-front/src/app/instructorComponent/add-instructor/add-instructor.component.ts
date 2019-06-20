import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { Location } from '@angular/common';
@Component({
  selector: 'app-add-instructor',
  templateUrl: './add-instructor.component.html',
  styleUrls: ['./add-instructor.component.css']
})
export class AddInstructorComponent implements OnInit {

  instructorList: string[] = ['Keerthana Devatha', 'Naresh', 'Pavan Kumar', 'Mark Nicholas',
'Akram Mohammed', 'Sean', 'Richa Prasad', 'Janise Peters',
'Maher Selim', 'Ibraheem Haruna', 'James Hung', 'Tye Alli',
'Rahul Nimodiya'];

  postForm: FormGroup;
  validMessage = '';

  instructorExample: any;


  constructor(
    private userService: UserService,
    private fb: FormBuilder,
    private location: Location
  ) {}

  ngOnInit() {
    this.createForm();
  }


  createForm() {
    this.postForm = this.fb.group({
      name: '',
      email: ''
    });
  }

  onSubmit() {
    if (this.postForm.valid) {
      this.validMessage = 'The instructor information has been saved. Thank you!';
      this.userService.createInstructor(this.postForm.value).subscribe(
        data => {
          this.instructorExample = data;
          this.postForm.reset();
          return true;
        },
        error => {
          alert('Couldnt create this instructor!'); }
      );
    } else {
      this.validMessage = 'Please fill out the form before submitting!';
    }
  }

  cancel() {
    this.location.back();
  }
}
