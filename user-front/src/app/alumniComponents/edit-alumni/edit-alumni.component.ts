import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-edit-alumni',
  templateUrl: './edit-alumni.component.html',
  styleUrls: ['./edit-alumni.component.css']
})
export class EditAlumniComponent implements OnInit {

  editForm: FormGroup;
  validMessage = '';
  private sub: Subscription;
  message: string;
  alumniExample: any;
  placementStatusList = [
    'placed',
    'unplaced'
  ];
  employmentTypeList = [
    'FullTime',
    'Contract',
    'PaidCoop'
  ];


  constructor(private fb: FormBuilder, private route: ActivatedRoute, private userService: UserService, private router: Router) {
  }

  ngOnInit() {
  this.updateForm();
  this.sub = this.route.paramMap.subscribe(
    params => {
      const email = params.get('email');
      this.getAlumni(email);
    }
  );
  }


  getAlumni(email: string): void {
    this.userService.listAlumniByEmail(email)
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
    placementStatus: '',
    companyName: '',
    employmentType: '',
    designation: '',
    comment: '',

  });

  }


  displayForm(data: any): void {
  if (this.editForm) {
    this.editForm.reset();
  }
  this.alumniExample = data;
  console.log(this.alumniExample);
  this.editForm.patchValue({
    name: this.alumniExample.name,
    phone: this.alumniExample.phone,
    email: this.alumniExample.email,
    placementStatus: this.alumniExample.placementStatus,
    companyName: this.alumniExample.companyName,
    employmentType: this.alumniExample.employmentType,
    designation: this.alumniExample.designation,
    comment: this.alumniExample.comment,
  });

  }

  onUpdate() {
  if (this.editForm.valid) {
    this.validMessage = 'Your information has been updated!';
    this.userService.updateAlumni(this.route.snapshot.params.email, this.editForm.value).subscribe(
      data => {
        this.message = 'The alumni has been updated!';
        this.router.navigate(['alumnus']);
        return true;
      },
      error => {
        alert('Couldnt update this alumni!'); });
  } else {
    this.validMessage = 'Please make sure the inputs are valid!';
  }
  }


  onDelete() {
  if (confirm('Are you sure you want to delete this alumni?')) {
    this.userService.deleteAlumni(this.route.snapshot.params.email)
    .subscribe(
      () => {this.editForm.reset(); this.router.navigate(['alumnus']); },
      (error: any) => console.error(error)
    );
  }
  }

}
