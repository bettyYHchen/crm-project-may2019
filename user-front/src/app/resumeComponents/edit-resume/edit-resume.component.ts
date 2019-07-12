import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Subscription } from 'rxjs';
import { FileUploader } from 'ng2-file-upload';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { environment } from 'src/environments/environment';
import { FileValidator } from 'src/app/file-input.validator';

@Component({
  selector: 'app-edit-resume',
  templateUrl: './edit-resume.component.html',
  styleUrls: ['./edit-resume.component.css']
})
export class EditResumeComponent implements OnInit {
  apiUrl = environment.apiUrl;
  uploadUrl = this.apiUrl + '/api/file/uploadFile/';
  editForm: FormGroup;
  validMessage = '';
  private sub: Subscription;
  message: string;
  resumeExample: any;
  // for file upload
  uploader: FileUploader;
  hasUploaded: boolean;
  response: string[];
  fileName: string = '';
  clientEmail: string;


  constructor(private fb: FormBuilder, private route: ActivatedRoute, private userService: UserService, private router: Router) {
  }

  ngOnInit() {
  this.updateForm();
  this.sub = this.route.paramMap.subscribe(
    params => {
      const email = params.get('email');
      this.getResume(email);
    }
  );
  this.clientEmail = this.route.snapshot.params.email;
  this.fileName = 'Resume' + '(' + this.clientEmail.split('.', 2)[0] + ')';
  const headers = [{name: 'Accept', value: 'application/json'}];
  this.uploader = new FileUploader({url: this.uploadUrl + this.fileName, autoUpload: true, headers});
  this.uploader.onCompleteItem = (item, response, status, headers) => {
      alert('File uploaded');
      this.hasUploaded = true;
      this.response = response.split(',', 4);
      this.fileName = this.response[0].split(':', 2)[1];}
  }


  getResume(email: string): void {
    this.userService.listResumeByEmail(email)
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
    resumeStartDate: '',
    resumeEndDate: '',
    resumeDoc: ['',    [FileValidator.validate]]

  });

  }


  displayForm(data: any): void {
  if (this.editForm) {
    this.editForm.reset();
  }
  this.resumeExample = data;
  console.log(this.resumeExample);
  this.editForm.patchValue({
    name: this.resumeExample.name,
    phone: this.resumeExample.phone,
    email: this.resumeExample.email,
    address: this.resumeExample.address,
    currentJob: this.resumeExample.currentJob,
    desiredJob: this.resumeExample.desiredJob,
    aTrainingClassName: this.resumeExample.aTrainingClassName,
    projectAssigned: this.resumeExample.projectAssigned,
    performance: this.resumeExample.performance,
    resumeStartDate: this.resumeExample.resumeStartDate,
    resumeEndDate: this.resumeExample.resumeEndDate,
    resumeDoc: this.resumeExample.resumeDoc,
  });

  }

  onUpdate() {
  if (this.editForm.valid) {
    this.validMessage = 'Your information has been updated!';
    this.userService.updateResume(this.route.snapshot.params.email, this.editForm.value).subscribe(
      data => {
        this.message = 'The resume has been updated!';
        this.router.navigate(['resumes']);
        return true;
      },
      error => {
        alert('Couldnt update this resume!'); });
  } else {
    this.validMessage = 'Please make sure the inputs are valid!';
  }
  }


  onDelete() {
  if (confirm('Are you sure you want to delete this resume?')) {
    this.userService.deleteResume(this.route.snapshot.params.email)
    .subscribe(
      () => {this.editForm.reset(); this.router.navigate(['resumes']); },
      (error: any) => console.error(error)
    );
  }
  }

  onConvertToMock() {
  if (confirm('Are you sure you want to change this resume to a mock?')) {
    this.userService.changeResumeToMock(this.route.snapshot.params.email)
    .subscribe(
      () => {this.editForm.reset(); this.router.navigate(['resumes']); },
      (error: any) => console.error(error)
    );
  }

  }

}
