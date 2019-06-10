import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-edit-mock',
  templateUrl: './edit-mock.component.html',
  styleUrls: ['./edit-mock.component.css']
})
export class EditMockComponent implements OnInit {

  editForm: FormGroup;
  validMessage = '';
  private sub: Subscription;
  message: string;
  mockExample: any;


  constructor(private fb: FormBuilder, private route: ActivatedRoute, private userService: UserService, private router: Router) {
  }

  ngOnInit() {
  this.updateForm();
  this.sub = this.route.paramMap.subscribe(
    params => {
      const email = params.get('email');
      this.getMock(email);
    }
  );
  }


  getMock(email: string): void {
    this.userService.listMockByEmail(email)
    .subscribe(
      data => {
        console.log(data);
        this.displayForm( data);
        },
      (error: any) => console.error(error)
    );
  }

  updateForm() {
  // const formControls = this.rolesArray.map(control => new FormControl(false));
  this.editForm = this.fb.group({
    name: ['', Validators.required],
    phone: ['', Validators.required],
    email: ['', Validators.required],
    address: '',
    currentJob: '',
    desiredJob: '',
    aTrainingClassName: '',
    projectAssigned: '',
    performance: '',
    interviewDate: ''

  });

  }


  displayForm(data: any): void {
  if (this.editForm) {
    this.editForm.reset();
  }
  this.mockExample = data;
  console.log(this.mockExample);
  this.editForm.patchValue({
    name: this.mockExample.name,
    phone: this.mockExample.phone,
    email: this.mockExample.email,
    address: this.mockExample.address,
    currentJob: this.mockExample.currentJob,
    desiredJob: this.mockExample.desiredJob,
    aTrainingClassName: this.mockExample.aTrainingClassName,
    projectAssigned: this.mockExample.projectAssigned,
    performance: this.mockExample.performance,
    interviewDate: this.mockExample.interviewDate
  });

  }

  onUpdate() {
  if (this.editForm.valid) {
    this.validMessage = 'Your information has been updated!';
    this.userService.updateMock(this.route.snapshot.params.email, this.editForm.value).subscribe(
      data => {
        this.message = 'The mock has been updated!';
        this.router.navigate(['mocks']);
        return true;
      },
      error => {
        alert('Couldnt update this mock!'); });
  } else {
    this.validMessage = 'Please make sure the inputs are valid!';
  }
  }


  onDelete() {
  if (confirm('Are you sure you want to delete this mock?')) {
    this.userService.deleteMock(this.route.snapshot.params.email)
    .subscribe(
      () => {this.editForm.reset(); this.router.navigate(['mocks']); },
      (error: any) => console.error(error)
    );
  }
  }

  onConvertToAlumni() {
  if (confirm('Are you sure you want to change this mock to an alumni?')) {
    this.userService.changeMockToAlumni(this.route.snapshot.params.email)
    .subscribe(
      () => {this.editForm.reset(); this.router.navigate(['mocks']); },
      (error: any) => console.error(error)
    );
  }

  }

}
