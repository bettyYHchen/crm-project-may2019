import { Component, OnInit } from '@angular/core';
import { AuthLoginInfo } from '../auth/login-info';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user.service';
import { Subscription, Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { TokenStorageService } from '../auth/token-storage.service';


@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  editForm: FormGroup;
  info: any;
  validMessage: string;
  message: string;


  constructor(private formBuilder: FormBuilder, private token: TokenStorageService, private authService: AuthService) { }

  ngOnInit() {
    this.editForm = this.formBuilder.group({
      username: '',
      password: new FormControl('',
      Validators.compose([Validators.required, Validators.minLength(8)]))
    });
    this.displayUserInfo();
  }

  displayUserInfo(): void {
    if (this.editForm) {
      this.editForm.reset();
    }
    this.info = {
      username: this.token.getUsername()
    };
    this.editForm.patchValue({
      username: this.info.username,
      password: 'realPasswordIsNotDisplayed'
    });
  }

  onReset() {
    if (this.editForm.valid) {
      this.validMessage = 'Your password has been updated!';
      this.authService.resetUserPassword(this.editForm.value).subscribe(
        data => {
          this.message = 'The password has been updated!';
          return true;
        },
        error => Observable.throw(error));
    } else {
      this.validMessage = 'Please make sure the inputs are valid!';
    }
  }

}
