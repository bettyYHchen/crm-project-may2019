import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-client-first-time-update',
  templateUrl: './client-first-time-update.component.html',
  styleUrls: ['./client-first-time-update.component.css']
})
export class ClientFirstTimeUpdateComponent implements OnInit {

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

employmentStatusList = [
  'Unemployed',
  'PartTime',
  'FullTime',
  'SelfEmployed'
];

paymentPlanList = [
  'One_Time_Credit_Card',
  'One_Time_Debit_Card_Or_Cash',
  'One_Time_Email_Money',
  'Automated_Weekly',
  'Automated_BiWeekly'
];



classList = [];




editForm: FormGroup;
validMessage = '';
private sub: Subscription;
message: string;
leadExample: any;

constructor(private fb: FormBuilder, private route: ActivatedRoute, private userService: UserService) {
  }

ngOnInit() {
  this.courseList.forEach((course) => {
    this.termList.forEach((term) => this.classList.push(course + ' ' + term));
  });
  this.updateForm();
  this.sub = this.route.paramMap.subscribe(
    params => {
      const email = params.get('email');
      this.getLead(email);
    }
  );
}


getLead(email: string): void {
  this.userService.listLeadClientByEmail(email)
  .subscribe(
    data => {
      console.log(data);
      this.displayForm( data);
      },
    (error: any) => console.error(error)
  );
}

updateForm() {
  this.editForm = this.fb.group({
    name: '',
    email: '',
    phone: '',
    address: '',
    aTrainingClassName: '',
    employmentStatus: '',
    currentJob: '',
    desiredJob: '',
    paymentPlan: '',
    paymentPlanAgreement: '',
    leadSource: ''
  });

}


displayForm(data: any): void {
  if (this.editForm) {
    this.editForm.reset();
  }
  this.leadExample = data;
  console.log(this.leadExample);
  this.editForm.patchValue({
    name: this.leadExample.firstName + ' ' + this.leadExample.lastName,
    email: this.leadExample.email,
    phone: this.leadExample.phone,
    address: 'Unit, Street, City, Province, Postal',
    aTrainingClassName: this.leadExample.aTrainingClassName,
    employmentStatus: this.leadExample.employmentStatus,
    currentJob: 'if unemployed, input N/A',
    desiredJob: '',
    paymentPlan: this.leadExample.paymentPlan,
    paymentPlanAgreement: this.leadExample.paymentPlanAgreement,
    leadSource: '',
  });

}

onUpdate() {
  if (this.editForm.valid) {
    this.validMessage = 'Your information has been updated!';
    this.userService.updateLeadClient(this.route.snapshot.params.email, this.editForm.value).subscribe(
      data => {
        this.message = 'The lead has been updated!';
        return true;
      },
      error => {
        alert('Couldnt update this lead!'); });
  } else {
    this.validMessage = 'Please make sure the inputs are valid!';
  }
}

}
