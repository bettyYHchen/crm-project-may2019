import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import { Observable } from 'rxjs';


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
  validMessage = '';
  paymentPlanList = [
    'One_Time_Credit_Card',
    'One_Time_Debit_Card_Or_Cash',
    'One_Time_Email_Money',
    'Automated_Weekly',
    'Automated_BiWeekly'
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



  selectedCourse = [];
  courseDropdownList = [];
  courseDropdownSettings = {};

  selectedTerm = [];
  termDropdownList = [];
  termDropdownSettings = {};

  constructor(
    private userService: UserService,
    private fb: FormBuilder
  ) { }

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
    ;
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

  onCourseSelect(item:any){
    console.log(item);
    console.log(this.selectedCourse);
}
  OnCourseDeSelect(item:any){
    console.log(item);
    console.log(this.selectedCourse);
}

  onTermSelect(item:any){
  console.log(item);
  console.log(this.selectedTerm);
}

  OnTermDeSelect(item:any){
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

  onSubmit() {
    if (this.postForm.valid) {
      this.validMessage = 'Your information has been saved. Thank you!';
      console.log(this.postForm.value);
      this.userService.signUpLead(this.postForm.value).subscribe(
        data => {
          this.postForm.reset();
          return true;
        },
        error => {
          alert('Couldnt register this lead!'); }
      );
    } else {
      this.validMessage = 'Please fill out the form before submitting!';
    }
  }



}
