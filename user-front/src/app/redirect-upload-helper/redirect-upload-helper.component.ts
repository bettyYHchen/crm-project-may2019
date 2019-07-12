import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-redirect-upload-helper',
  templateUrl: './redirect-upload-helper.component.html',
  styleUrls: ['./redirect-upload-helper.component.css']
})
export class RedirectUploadHelperComponent implements OnInit {
  editForm: FormGroup;
  validMessage = '';
  message: string;
  settingExample: any;
  fileNameComp: string;

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

  paymentPlanList = [
    'One_Time_Credit_Card',
    'One_Time_Debit_Card_Or_Cash',
    'One_Time_Email_Money',
    'Automated_Weekly_Credit_Card',
    'Automated_BiWeekly_Credit_Card',
    'Automated_Weekly_Debit_Card_Or_Cash',
    'Automated_BiWeekly_Debit_Card_Or_Cash'
  ];

  constructor(private fb: FormBuilder, private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.updateForm();
  }

  updateForm() {
    this.editForm = this.fb.group({
      courseName: '',
      paymentPlan: ''
    });
    }

    onUpdate() {
      if (this.editForm.valid) {
        this.validMessage = 'Info has been updated!';
        this.fileNameComp = '(' + this.editForm.value.courseName + ')(' + this.editForm.value.paymentPlan + ')';
        console.log(this.fileNameComp);
        this.router.navigate(['uploadFile/' + this.fileNameComp]);
      } else {
        this.validMessage = 'Please make sure the inputs are valid!';
      }
      }


}
