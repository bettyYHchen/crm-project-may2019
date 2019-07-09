import { Component, OnInit } from '@angular/core';

import { AuthService } from '../auth/auth.service';
import { SignUpInfo } from '../auth/signup-info';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, FormArray, Validators, FormControl } from '@angular/forms';
import { UserService } from '../services/user.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  editForm: FormGroup;
  positions: FormArray;
  validMessage = '';
  signupInfo: SignUpInfo;
  private sub: Subscription;
  message: string;
  constructor(private fb: FormBuilder, private route: ActivatedRoute, private authService: AuthService) {
   }
  positionArray = [
  'ROLE_PM,TEAM_SALES', 'ROLE_USER,TEAM_ACCOUNTS',
  'ROLE_ADMIN,TEAM_ADMIN', 'ROLE_USER,TEAM_UNASSIGNED'];
  ngOnInit() {
    this.updateForm();
  }

  get formData() { return this.editForm.get('positions') as FormArray; }


  updateForm() {
    // const formControls = this.rolesArray.map(control => new FormControl(false));
    this.editForm = this.fb.group({
      name: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      positions: this.fb.array([])
    });

  }

  // createPosition(): FormGroup {
  //   return this.fb.group({
  //     name: ''
  //   });
  // }

  // addPosition(): void {
  //   this.positions = this.editForm.get('positions') as FormArray;
  //   this.positions.push(this.createPosition());
  // }

  onChange(isChecked: boolean, position: string) {
    const formArray = this.editForm.get('positions') as FormArray;

    if(isChecked) {
      formArray.push(new FormControl(position));
    } else {
      let index = formArray.controls.findIndex(x => x.value === position);
      formArray.removeAt(index);
    }
    console.log(formArray);
  }




  onAdd() {
    if (this.editForm.valid) {
      this.validMessage = 'The user information is added';
      this.authService.signUp(this.editForm.value).subscribe(
        data => {
          this.message = 'The user has been added!';
          return true;
        },
        error => console.error(error));
    } else {
      this.validMessage = 'Please make sure the inputs are valid!';
    }
  }

}
