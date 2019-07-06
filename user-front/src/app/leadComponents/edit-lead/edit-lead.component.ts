import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { Lead } from 'src/app/model/lead';

@Component({
  selector: 'app-edit-lead',
  templateUrl: './edit-lead.component.html',
  styleUrls: ['./edit-lead.component.css']
})

export class EditLeadComponent implements OnInit {
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





  editForm: FormGroup;
  validMessage = '';
  private sub: Subscription;
  message: string;
  leadExample: any;
  confirmationMessage = '';
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
        this.getLead(email);
      }
    );
  }


  getLead(email: string): void {
    this.userService.listLeadByEmail(email)
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
      paidDeposit: false,
      discount: '',
      paymentPlan: '',
      paymentPlanStatus: '',
      paymentPlanAgreement: '',
      leadSource: '',
      leadStatus: '',
      aTrainingClassName: '',
      comment: '',
      dropOff: ''
    });

  }


  displayForm(data: any): void {
    if (this.editForm) {
      this.editForm.reset();
    }
    this.leadExample = data;
    this.userId = this.leadExample.id;
    this.editForm.patchValue({
      firstName: this.leadExample.firstName,
      lastName: this.leadExample.lastName,
      phone: this.leadExample.phone,
      email: this.leadExample.email,
      paidDeposit: this.leadExample.paidDeposit,
      discount: this.leadExample.discount,
      paymentPlan: this.leadExample.paymentPlan,
      paymentPlanStatus: this.leadExample.paymentPlanStatus,
      paymentPlanAgreement: this.leadExample.paymentPlanAgreement,
      leadSource: this.leadExample.leadSource,
      leadStatus: this.leadExample.leadStatus,
      aTrainingClassName: this.leadExample.aTrainingClassName,
      comment: this.leadExample.comment,
      dropOff: this.leadExample.dropOff
    });

  }

  onDropOff() {
    this.editForm.patchValue({
      dropOff: true
    });
    this.userService.updateLead(this.route.snapshot.params.email, this.editForm.value).subscribe(
      data => {
        this.message = 'The lead has been drop off!';
        this.router.navigate(['leads']);
        return true;
      },
      error => {
        alert('Couldnt drop off this lead!'); });
  }

  onUpdate() {
    if (this.editForm.valid) {
      this.validMessage = 'Your information has been updated!';
      this.userService.updateLead(this.route.snapshot.params.email, this.editForm.value).subscribe(
        data => {
          this.message = 'The lead has been updated!';
          this.router.navigate(['leads']);
          return true;
        },
        error => {
          alert('Couldnt update this lead!'); });
    } else {
      this.validMessage = 'Please make sure the inputs are valid!';
    }
  }


  onDelete() {
    if (confirm('Are you sure you want to delete this lead?')) {
      this.userService.deleteLead(this.route.snapshot.params.email)
      .subscribe(
        () => {this.editForm.reset(); this.router.navigate(['leads']); },
        (error: any) => console.error(error)
      );
    }
  }

  onConvertToStudent() {
    if (! this.editForm.value.paidDeposit) {alert('You cannot convert this lead to a student because the deposite has not been paid yet!');
    } else if (this.editForm.value.paymentPlanStatus !== 'CONFIRMED') {
      alert('You cannot convert this lead to a student because the payment plan has been confirmed yet!');
    } else {
      if (confirm('Are you sure you want to change this lead to a student?')) {
        this.userService.changeLeadToStudent(this.route.snapshot.params.email)
        .subscribe(
          () => {this.editForm.reset(); this.router.navigate(['leads']); },
          (error: any) => console.error(error)
        );
      }
    }

  }

  onSendPortalLink() {
    if (confirm('Are you sure you want to send portal link to this lead?')) {
      this.userService.sendEmailWithAttachment(this.route.snapshot.params.email)
      .subscribe(
        () => this.confirmationMessage = 'The portal link has been sent.',
        (error: any) => console.error(error)
      );
    }
  }





}
