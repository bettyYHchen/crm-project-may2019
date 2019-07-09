import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import { Observable } from 'rxjs';
import { Mail } from '../model/mail';
import { TrainingClass } from '../model/training-class';
import { LocationRequest } from '../model/location-request';


@Component({
  selector: 'app-lead-signup',
  templateUrl: './lead-signup.component.html',
  styleUrls: ['./lead-signup.component.css']
})
export class LeadSignupComponent implements OnInit {
  courseList: string[] = ['AUTOMATION_TESTING', 'DATA_SCIENCE', 'SCRUM_MASTER',
    'SOFTWARE_TESTING', 'AUTOMATION_TESTING_ONLINE', 'BUSINESS_ANALYSIS', 'DATA_SCIENCE_ONLINE',
      'CERTIFIED_SCRUM_MASTER', 'FULLSTACK_JAVA_DEVELOPER', 'PERFORMANCE_TESTING', 'SOFTWARE_TESTING_ONLINE'];
  currentYear = new Date().getFullYear().toString();
  postForm: FormGroup;
  classExample: any;
  classInfoStored: any;
  validMessage = '';
  confirmationMessage = '';
  paymentPlanList = [
    'One_Time_Credit_Card',
    'One_Time_Debit_Card_Or_Cash',
    'One_Time_Email_Money',
    'Automated_Weekly_Credit_Card',
    'Automated_BiWeekly_Credit_Card',
    'Automated_Weekly_Debit_Card_Or_Cash',
    'Automated_BiWeekly_Debit_Card_Or_Cash'
  ];

  paymentPlanStatusList = [
    'CONFIRMED',
    'UNCONFIRMED'
  ];

  leadSourceList = [
    'Advertisement',
    'Cold_Call',
    'Employee_Referral',
    'External_Referral',
    'Online_Store',
    'Partner',
    'Public_Relations',
    'Sales_Email_Alias',
    'Seminar_Partner',
    'Internal_Seminar',
    'Trade_Show',
    'Web_Download',
    'Web_Research',
    'Chat'
  ];

  leadStatusList = [
    'CONFIRMED',
    'UNCONFIRMED'
  ];

  instructorList: string[] = ['Keerthana Devatha', 'Naresh', 'Pavan Kumar', 'Mark Nicholas',
  'Akram Mohammed', 'Sean', 'Richa Prasad', 'Janise Peters',
  'Maher Selim', 'Ibraheem Haruna', 'James Hung', 'Tye Alli',
  'Rahul Nimodiya'];



  selectedCourse = [];
  courseDropdownList = [];
  courseDropdownSettings = {};

  selectedTerm = [];
  termDropdownList = [];
  termDropdownSettings = {};
  mail: Mail;
  leadExample: any;



  constructor(
    private userService: UserService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {

    this.courseDropdownList = [
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

    this.selectedCourse = ['AUTOMATION_TESTING'];

    this.courseDropdownSettings = {
      singleSelection: true,
      idField: 'id',
      textField: 'name',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      scrollableHeight: '300px',
      scrollable: true,
      allowSearchFilter: false
    };



    this.termDropdownList = [
      'WINTER ' + this.currentYear,
      'SPRING ' + this.currentYear,
      'SUMMER ' + this.currentYear,
      'FALL ' + this.currentYear,
    ];

    this.selectedTerm = ['WINTER ' + this.currentYear];

    this.termDropdownSettings = {
      singleSelection: true,
      idField: 'id',
      textField: 'name',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      scrollableHeight: '300px',
      scrollable: true,
      allowSearchFilter: false
    };

    this.createForm();



  }

  onCourseSelect(item:any) {
    console.log(item);
    console.log(this.selectedCourse);
}
  OnCourseDeSelect(item: any) {
    console.log(item);
    console.log(this.selectedCourse);
}

  onTermSelect(item: any) {
  console.log(item);
  console.log(this.selectedTerm);
}

  OnTermDeSelect(item: any) {
  console.log(item);
  console.log(this.selectedTerm);
}




createForm() {
  this.postForm = this.fb.group({
    firstName: '',
    lastName: '',
    phone: '',
    email: ['', Validators.required],
    paidDeposit: false,
    paymentPlan: '',
    paymentPlanStatus: '',
    paymentPlanAgreement: '',
    leadSource: '',
    leadStatus: '',
    courseName: [],
    batch: [],
    comment: ''
  });
}

  customFunc(data) {
    this.classExample = data;
    this.postForm.patchValue({
      courseName: this.classExample.courseName,
      batch: this.classExample.batch
    });
    this.userService.listClassByName(this.classExample.courseName + ' ' + this.classExample.batch).subscribe(
      loadedClass => { this.classInfoStored = loadedClass; },
      error => {
        alert('Couldnt load this class!'); }
    );
  }

  onSubmit() {
    if (this.postForm.valid) {
      this.validMessage = 'Your information has been saved. Thank you!';
      console.log(this.postForm.value);
      this.userService.signUpLead(this.postForm.value).subscribe(
        data => {
          this.leadExample = data;
          return true;
        },
        error => {
          alert('Couldnt register this lead!'); }
      );
    } else {
      this.validMessage = 'Please fill out the form before submitting!';
    }
  }

  onSendTemplate() {
    console.log(this.leadExample);
    this.mail = new Mail(this.leadExample.email, 'BusyQA Welcome Package', this.leadExample.firstName,
                    this.leadExample.firstName, this.classInfoStored.name, this.classInfoStored.address,
                    this.classInfoStored.start, this.classInfoStored.instructorName);
    if (confirm('Are you sure you want to send the welcome package?')) {
      this.userService.sendTemplateEmail(this.mail)
      .subscribe(
        data => {
          this.confirmationMessage = 'The welcome package has been sent!';
          return true;
        },
        (error: any) => console.error(error)
      );
    }
  }

  // onSubmitClassForm() {
  //   if (this.classForm.valid) {
  //     console.log(this.classForm.value);
  //     this.userService.createClass(this.classForm.value).subscribe(
  //       data => {
  //         this.classExample = data;
  //         this.postForm.patchValue({
  //           courseName: this.classExample.courseName,
  //           batch: this.classExample.batch
  //         });
  //       },
  //       error => {
  //         alert('Couldnt create this class!'); }
  //     );
  //   } else {
  //     this.validMessage = 'Please fill out the form before submitting!';
  //   }
  // }

}
