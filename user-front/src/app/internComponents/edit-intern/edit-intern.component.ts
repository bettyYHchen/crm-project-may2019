import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-edit-intern',
  templateUrl: './edit-intern.component.html',
  styleUrls: ['./edit-intern.component.css']
})
export class EditInternComponent implements OnInit {

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

coopStatusList = [
  'PENDING_TO_START_UNFINISHED',
  'PENDING_TO_START_FEE_NOT_CLEAR',
  'IN_PROGRESS',
  'DONE'
];

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





editForm: FormGroup;
validMessage = '';
private sub: Subscription;
message: string;
internExample: any;
userId: number;

constructor(private fb: FormBuilder, private route: ActivatedRoute, private userService: UserService, private router: Router) {
  }

ngOnInit() {
  this.courseList.forEach((course) => {
    this.termList.forEach((term) => this.classList.push(course + ' ' + term));
  });
  this.updateForm();
  this.sub = this.route.paramMap.subscribe(
    params => {
      const email = params.get('email');
      this.getIntern(email);
    }
  );
}


getIntern(email: string): void {
  this.userService.listInternByEmail(email)
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
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    phone: ['', Validators.required],
    email: ['', Validators.required],
    aTrainingClassName: '',
    paymentPlan: '',
    paymentPlanStatus: '',
    amountPaid: '',
    remainingBalance: '',
    coopStatus: '',
    coopStartDate: '',
    coopEndDate: '',
    projectAssigned: '',
    performance: ''
  });

}


displayForm(data: any): void {
  if (this.editForm) {
    this.editForm.reset();
  }
  this.internExample = data;
  this.userId = this.internExample.id;
  console.log(this.internExample);
  this.editForm.patchValue({
    firstName: this.internExample.firstName,
    lastName: this.internExample.lastName,
    phone: this.internExample.phone,
    email: this.internExample.email,
    aTrainingClassName: this.internExample.aTrainingClassName,
    paymentPlan: this.internExample.paymentPlan,
    paymentPlanStatus: this.internExample.paymentPlanStatus,
    amountPaid: this.internExample.amountPaid,
    remainingBalance: this.internExample.remainingBalance,
    coopStatus: this.internExample.coopStatus,
    coopStartDate: this.internExample.coopStartDate,
    coopEndDate: this.internExample.coopEndDate,
    projectAssigned: this.internExample.projectAssigned,
    performance: this.internExample.performance
  });

}

onUpdate() {
  if (this.editForm.valid) {
    this.validMessage = 'Your information has been updated!';
    this.userService.updateIntern(this.route.snapshot.params.email, this.editForm.value).subscribe(
      data => {
        this.message = 'The intern has been updated!';
        this.router.navigate(['interns']);
        return true;
      },
      error => {
        alert('Couldnt update this intern!'); });
  } else {
    this.validMessage = 'Please make sure the inputs are valid!';
  }
}


onDelete() {
  if (confirm('Are you sure you want to delete this intern?')) {
    this.userService.deleteIntern(this.route.snapshot.params.email)
    .subscribe(
      () => {this.editForm.reset(); this.router.navigate(['interns']); },
      (error: any) => console.error(error)
    );
  }
}

onConvertToResume() {
  if (confirm('Are you sure you want to change this intern to a resume?')) {
    this.userService.changeInternToResume(this.route.snapshot.params.email)
    .subscribe(
      () => {this.editForm.reset(); this.router.navigate(['interns']); },
      (error: any) => console.error(error)
    );
  }

}

}
