import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-rates',
  templateUrl: './update-rates.component.html',
  styleUrls: ['./update-rates.component.css']
})
export class UpdateRatesComponent implements OnInit {

  editForm: FormGroup;
  validMessage = '';
  message: string;
  settingExample: any;



  constructor(private fb: FormBuilder, private userService: UserService, private router: Router) {
  }

  ngOnInit() {
  this.updateForm();
  this.getSetting();
  }


  getSetting(): void {
    this.userService.getSetting()
    .subscribe(
      data => {
        this.displayForm( data);
        },
      (error: any) => console.error(error)
    );
  }

  updateForm() {
  this.editForm = this.fb.group({
    taxPercentage: '',
    lateFeeRate: '',
    creditExtraRate: ''

  });

  }


  displayForm(data: any): void {
  if (this.editForm) {
    this.editForm.reset();
  }
  this.settingExample = data;
  console.log(this.settingExample);
  this.editForm.patchValue({
    taxPercentage: this.settingExample.taxPercentage,
    lateFeeRate: this.settingExample.lateFeeRate,
    creditExtraRate: this.settingExample.creditExtraRate
  });

  }

  onUpdate() {
  if (this.editForm.valid) {
    this.validMessage = 'Setting has been updated!';
    this.userService.updateSetting(this.editForm.value).subscribe(
      data => {
        this.message = 'Setting has been updated!';
        this.router.navigate(['admin']);
        return true;
      },
      error => {
        alert('Couldnt update this setting!'); });
  } else {
    this.validMessage = 'Please make sure the inputs are valid!';
  }
  }





}
