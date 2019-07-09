import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-location',
  templateUrl: './add-location.component.html',
  styleUrls: ['./add-location.component.css']
})
export class AddLocationComponent implements OnInit {

  postForm: FormGroup;
  validMessage = '';

  locationExample: any;


  constructor(
    private userService: UserService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.createForm();
  }


  createForm() {
    this.postForm = this.fb.group({
      address: ''
    });
  }

  onSubmit() {
    if (this.postForm.valid) {
      this.validMessage = 'The location information has been saved. Thank you!';
      this.userService.createLocation(this.postForm.value).subscribe(
        data => {
          this.locationExample = data;
          this.postForm.reset();
          return true;
        },
        error => {
          alert('Couldnt create this locations!'); }
      );
    } else {
      this.validMessage = 'Please fill out the form before submitting!';
    }
  }

}
