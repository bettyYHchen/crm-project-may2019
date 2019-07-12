import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { TokenStorageService } from '../auth/token-storage.service';

@Component({
  selector: 'app-get-client-profile',
  templateUrl: './get-client-profile.component.html',
  styleUrls: ['./get-client-profile.component.css']
})
export class GetClientProfileComponent implements OnInit {

  editForm: FormGroup;
  validMessage = '';
  private sub: Subscription;
  message: string;
  clientExample: any;
  username: string;
  employmentStatusList = [
    'Unemployed',
    'PartTime',
    'FullTime',
    'SelfEmployed'
  ];

  currentYear = new Date().getFullYear().toString();
  courseList = [
    'AUTOMATION_TESTING' ,
    'DATA_SCIENCE',
    'SCRUM_MASTER',
    'SOFTWARE_TESTING',
    'BUSINESS_ANALYSIS',
    'CERTIFIED_SCRUM_MASTER',
    'FULLSTACK_JAVA_DEVELOPER',
    'PERFORMANCE_TESTING',
    'AUTOMATION_TESTING_ONLINE',
    'DATA_SCIENCE_ONLINE',
    'SOFTWARE_TESTING_ONLINE'

];

termList = [
  'WINTER ' + this.currentYear,
  'SPRING ' + this.currentYear,
  'SUMMER ' + this.currentYear,
  'FALL ' + this.currentYear,
];

classList = [];




  constructor(private token: TokenStorageService, private fb: FormBuilder,
              private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    this.username = this.token.getUsername();
    this.courseList.forEach((course) => {
      this.termList.forEach((term) => this.classList.push(course + ' ' + term));
    });
    this.updateForm();
    this.getClient(this.username);
  }


  getClient(email: string): void {
    this.userService.listClientByEmail(email)
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
    aTrainingClassName: '',
    employmentStatus: '',
    currentJob: '',
    desiredJob: '',

  });

  }


  displayForm(data: any): void {
  if (this.editForm) {
    this.editForm.reset();
  }
  this.clientExample = data;
  console.log(this.clientExample);
  this.editForm.patchValue({
    name: this.clientExample.name,
    phone: this.clientExample.phone,
    email: this.clientExample.email,
    address: this.clientExample.address,
    aTrainingClassName: this.clientExample.aTrainingClassName,
    employmentStatus: this.clientExample.employmentStatus,
    currentJob: this.clientExample.currentJob,
    desiredJob: this.clientExample.desiredJob,
  });

  }

  onUpdate() {
  if (this.editForm.valid) {
    this.validMessage = 'Your information has been updated!';
    this.userService.updateClient(this.username, this.editForm.value).subscribe(
      data => {
        this.message = 'The client has been updated!';
        this.router.navigate(['']);
        return true;
      },
      error => {
        alert('Couldnt update this client!'); });
  } else {
    this.validMessage = 'Please make sure the inputs are valid!';
  }
  }

}
