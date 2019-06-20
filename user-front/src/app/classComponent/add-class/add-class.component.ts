import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { Location } from '@angular/common';
import { TrainingClass } from 'src/app/model/training-class';
import { TrainingClassBrief } from 'src/app/model/training-class-brief';

@Component({
  selector: 'app-add-class',
  templateUrl: './add-class.component.html',
  styleUrls: ['./add-class.component.css']
})
export class AddClassComponent implements OnInit {

  instructorList: string[] = ['Keerthana Devatha', 'Naresh', 'Pavan Kumar', 'Mark Nicholas',
'Akram Mohammed', 'Sean', 'Richa Prasad', 'Janise Peters',
'Maher Selim', 'Ibraheem Haruna', 'James Hung', 'Tye Alli',
'Rahul Nimodiya'];

  courseList: string[] = ['AUTOMATION_TESTING', 'DATA_SCIENCE', 'SCRUM_MASTER',
    'SOFTWARE_TESTING', 'AUTOMATION_TESTING_ONLINE', 'BUSINESS_ANALYSIS', 'DATA_SCIENCE_ONLINE',
      'CERTIFIED_SCRUM_MASTER', 'FULLSTACK_JAVA_DEVELOPER', 'PERFORMANCE_TESTING', 'SOFTWARE_TESTING_ONLINE'];
currentYear = new Date().getFullYear().toString();


batchList: string[] = [
      'WINTER ' + this.currentYear,
      'SPRING ' + this.currentYear,
      'SUMMER ' + this.currentYear,
      'FALL ' + this.currentYear,
    ];

addressList: string[] = [
'Suite 1532, 4 Robert Speck Parkway, Mississauga ON L4Z 1S1',
'Iceland Mississauga, 705 Matheson Blvd E, Mississauga ON L4Z 3X9',
'Swansea Town Hall Community Center, 95 Lavinia Ave, Toronto ON M6S 3H9',
'Armadale Community Center, 2401 Denison St, Markham ON L3S 1E7',
'Cassie Campbell Community Center, 1050 Sandalwood Pkwy W, Brampton ON L7A 0K9'];

  postForm: FormGroup;
  validMessage = '';
  confirmationMessage = '';
  classExample: any;
  splitted: string;

  @Output() sendClassInfo: EventEmitter <TrainingClassBrief> = new EventEmitter<TrainingClassBrief>();
  constructor(
    private userService: UserService,
    private fb: FormBuilder,
    private location: Location
  ) {}

  ngOnInit() {
    this.createForm();
  }



  createForm() {
    this.postForm = this.fb.group({
      courseName: '',
      batch: '',
      instructorName: '',
      address: '',
      start: '',
      end: ''
    });
  }

  onSubmit() {
    if (this.postForm.valid) {
      this.validMessage = 'The class has been saved. Thank you!';
      this.userService.createClass(this.postForm.value).subscribe(
        data => {
          this.classExample = data;
          this.splitted  = this.classExample.name.split(' ', 3);
          this.sendClassInfo.emit(new TrainingClassBrief(this.splitted[0],
            this.splitted[1] + ' ' + this.splitted[2]));
          return true;
        },
        error => {
          alert('Couldnt create this class!'); }
      );
    } else {
      this.validMessage = 'Please fill out the form before submitting!';
    }
  }

  cancel() {
    this.location.back();
  }

}
