import { Component, OnInit } from '@angular/core';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-edit-student',
  templateUrl: './edit-student.component.html',
  styleUrls: ['./edit-student.component.css']
})

export class EditStudentComponent implements OnInit {

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





  editForm: FormGroup;
  validMessage = '';
  private sub: Subscription;
  message: string;
  studentExample: any;
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
        this.getStudent(email);
      }
    );
  }


  getStudent(email: string): void {
    this.userService.listStudentByEmail(email)
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
      paymentPlan: '',
      paymentPlanStatus: '',
      paymentPlanAgreement: '',
      aTrainingClassName: '',
      comment: '',
      amountPaid: '',
      remainingBalance: '',
      classFinished: '',
      dropOff: ''
    });

  }


  displayForm(data: any): void {
    if (this.editForm) {
      this.editForm.reset();
    }
    this.studentExample = data;
    this.userId = this.studentExample.id;
    this.editForm.patchValue({
      firstName: this.studentExample.firstName,
      lastName: this.studentExample.lastName,
      phone: this.studentExample.phone,
      email: this.studentExample.email,
      paymentPlan: this.studentExample.paymentPlan,
      paymentPlanStatus: this.studentExample.paymentPlanStatus,
      paymentPlanAgreement: this.studentExample.paymentPlanAgreement,
      aTrainingClassName: this.studentExample.aTrainingClassName,
      comment: this.studentExample.comment,
      amountPaid: this.studentExample.amountPaid,
      remainingBalance: this.studentExample.remainingBalance,
      classFinished: this.studentExample.classFinished,
      dropOff: this.studentExample.dropOff
    });

  }

  onDropOff() {
    this.editForm.patchValue({
      dropOff: true
    });
    this.userService.updateStudent(this.route.snapshot.params.email, this.editForm.value).subscribe(
      data => {
        this.message = 'The student has been drop off!';
        this.router.navigate(['students']);
        return true;
      },
      error => {
        alert('Couldnt drop off this student!'); });
  }

  onUpdate() {
    if (this.editForm.valid) {
      this.validMessage = 'Your information has been updated!';
      this.userService.updateStudent(this.route.snapshot.params.email, this.editForm.value).subscribe(
        data => {
          this.message = 'The student has been updated!';
          this.router.navigate(['students']);
          return true;
        },
        error => {
          alert('Couldnt update this student!'); });
    } else {
      this.validMessage = 'Please make sure the inputs are valid!';
    }
  }


  onDelete() {
    if (confirm('Are you sure you want to delete this student?')) {
      this.userService.deleteStudent(this.route.snapshot.params.email)
      .subscribe(
        () => { this.editForm.reset(); this.router.navigate(['students']); },
        (error: any) => console.error(error)
      );
    }
  }

  onConvertToIntern() {
    if (confirm('Are you sure you want to change this student to an intern?')) {
      this.userService.changeStudentToIntern(this.route.snapshot.params.email)
      .subscribe(
        () => {this.editForm.reset(); this.router.navigate(['students']); },
        (error: any) => console.error(error)
      );
    }

  }

}
