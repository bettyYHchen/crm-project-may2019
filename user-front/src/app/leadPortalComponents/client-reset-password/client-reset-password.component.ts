import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';
import { Subscription, Observable } from 'rxjs';
import { MustMatch } from 'src/app/helpers/must-match';

@Component({
  selector: 'app-client-reset-password',
  templateUrl: './client-reset-password.component.html',
  styleUrls: ['./client-reset-password.component.css']
})
export class ClientResetPasswordComponent implements OnInit {

  private sub: Subscription;
  editForm: FormGroup;
  validMessage: string;
  message: string;
  isSubmitted: boolean;


  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute, private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.editForm = this.formBuilder.group({
      username: '',
      password: ['', [Validators.required, Validators.minLength(8)]],
      confirmPassword: ['', Validators.required]
    }, {
      validator: MustMatch('password', 'confirmPassword')
  });
    this.sub = this.route.paramMap.subscribe(
      params => {
        const email = params.get('email');
        this.displayUserInfo(email);
      }
    );
  }

  // convenience getter for easy access to form fields
  get formPassword() { return this.editForm.get('password') as FormControl; }
  get formConfirmedPassword() { return this.editForm.get('confirmPassword') as FormControl; }
  displayUserInfo(userName: string): void {
    console.log(userName);
    if (this.editForm) {
      this.editForm.reset();
    }
    this.editForm.patchValue({
      username: userName,
      password: '',
      confirmPassword: ''
    });
  }

  onSubmit() {
    this.isSubmitted = true;
    if (this.editForm.valid) {
      this.validMessage = 'Your password has been updated!';
      this.authService.resetClientPassword(this.route.snapshot.params.email, this.editForm.value).subscribe(
        data => {
          this.message = 'The password has been updated!';
          this.router.navigate(['client/register/' + this.route.snapshot.params.email]);
          console.log('client/register/' + this.route.snapshot.params.email);
          return true;
        },
        error => Observable.throw(error));
    } else {
      this.validMessage = 'Please make sure the inputs are valid!';
    }
  }

}
