import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-get-classes',
  templateUrl: './get-classes.component.html',
  styleUrls: ['./get-classes.component.css']
})
export class GetClassesComponent implements OnInit {

  public classes;
  editForm: FormGroup;
  validMessage = '';
  message: string;

  constructor(private fb: FormBuilder, private userService: UserService, private location: Location, private router: Router) { }

  ngOnInit() {
    this.getClasses();
    this.editForm = this.fb.group({
      finishStatus: ''
  });
  }

  getClasses() {
    this.userService.listClasses().subscribe(
      data => {this.classes = data;
               console.log(data); },
      err => console.log(err),
      () => console.log('all classes are listed!')
    );
  }

  onDelete(id: number) {
    if (confirm('Are you sure you want to delete the class?')) {
      this.userService.deleteClass(id)
      .subscribe(
        () => {this.reloadPage(); },
        (error: any) => console.error(error)
      );
    }
  }

  cancel() {
    this.location.back();
  }


  onUpdate(id: number) {
    if (this.editForm.valid) {
      this.validMessage = 'Status updated!';
      this.userService.updateClassFinishedStatus(id, this.editForm.value).subscribe(
        data => {
          this.message = 'The status has been updated!';
          this.reloadPage();
          return true;
        },
        error => {
          alert('Couldnt update the status!'); });
    } else {
      this.validMessage = 'Please make sure the inputs are valid!';
    }
  }

  reloadPage() {
    window.location.reload();
  }

}
