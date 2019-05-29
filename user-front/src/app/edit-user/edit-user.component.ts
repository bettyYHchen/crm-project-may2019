import { Component, OnInit , Inject} from '@angular/core';
import {Router, ActivatedRoute} from "@angular/router";
import {User} from "../model/user.model";
import {FormBuilder, FormGroup, Validators, FormControl, FormArray} from "@angular/forms";
import { UserService } from '../services/user.service';
import { UserRequest } from '../model/user-request';
import { Observable, Subscription } from 'rxjs';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  editForm: FormGroup;
  positions: FormArray;
  validMessage = '';
  userRequest: UserRequest;
  private sub: Subscription;
  message: string;
  nameCtrl: FormControl;
  constructor(private fb: FormBuilder, private route: ActivatedRoute, private userService: UserService) {
   }
  positionArray = ['ROLE_USER,TEAM_SALES', 'ROLE_ADMIN,TEAM_SALES',
  'ROLE_PM,TEAM_SALES', 'ROLE_USER,TEAM_ACCOUNTS',
  'ROLE_ADMIN,TEAM_ACCOUNTS', 'ROLE_PM,TEAM_ACCOUNTS',
  'ROLE_USER,TEAM_ADMIN', 'ROLE_ADMIN,TEAM_ADMIN',
  'ROLE_PM,TEAM_ADMIN', 'ROLE_USER,TEAM_UNASSIGNED'];
  ngOnInit() {
    this.updateForm();
    this.sub = this.route.paramMap.subscribe(
      params => {
        const username = params.get('username');
        this.getUser(username);
      }
    );
  }

  get formData() { return this.editForm.get('positions') as FormArray; }

  getUser(username: string): void {
    this.userService.getUserByUsername(username)
    .subscribe(
      data => {
        this.displayForm(data.result); },
      (error: any) => console.error(error)
    );
  }

  updateForm() {
    // const formControls = this.rolesArray.map(control => new FormControl(false));
    this.editForm = this.fb.group({
      name: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', Validators.required],
      positions: this.fb.array([]),
      status: '',
      statusAsOfDay: ''
    });

  }

  // createPosition(): FormGroup {
  //   return this.fb.group({
  //     name: ''
  //   });
  // }

  // addPosition(): void {
  //   this.positions = this.editForm.get('positions') as FormArray;
  //   this.positions.push(this.createPosition());
  // }

  onChange(isChecked: boolean, position: string) {
    const formArray = this.editForm.get('positions') as FormArray;

    if(isChecked) {
      formArray.push(new FormControl(position));
    } else {
      let index = formArray.controls.findIndex(x => x.value === position);
      formArray.removeAt(index);
    }
    console.log(formArray);
  }





  displayForm(data: UserRequest): void {
    if (this.editForm) {
      this.editForm.reset();
    }
    this.userRequest = data;
    this.editForm.patchValue({
      name: this.userRequest.name,
      username: this.userRequest.username,
      email: this.userRequest.email,
      status: this.userRequest.status,
      statusAsOfDay: this.userRequest.statusAsOfDay,
    });



  }


  onUpdate() {
    if (this.editForm.valid) {
      this.validMessage = 'The user information is updated';
      console.log(this.route.snapshot.params.username);
      console.log(this.editForm.value);
      this.userService.updateUser(this.route.snapshot.params.username, this.editForm.value).subscribe(
        data => {
          this.message = 'The user has been updated!';
          return true;
        },
        error => console.error(error));
    } else {
      this.validMessage = 'Please make sure the inputs are valid!';
    }
  }

onDelete() {
  if (confirm('Are you sure you want to delete this user?')) {
    this.userService.deleteUser(this.route.snapshot.params.username)
    .subscribe(
      () => this.editForm.reset(),
      (error: any) => console.error(error)
    );
  }
}

  // user: User;
  // username: string
  // editForm: FormGroup;
  // constructor(private formBuilder: FormBuilder,private router: Router, private userService: UserService) { }

  // ngOnInit() {
  //   let userId = window.localStorage.getItem("editUserId");
  //   if(!userId) {
  //     alert("Invalid action.")
  //     this.router.navigate(['admin']);
  //     return;
  //   }
  //   this.editForm = this.formBuilder.group({
  //     id: [''],
  //     password:[''],
  //     name: ['', Validators.required],
  //     username: ['', Validators.required],
  //     email: ['', Validators.required],
  //     roles: [''],
  //     teams: ['']
  //   });
  //   this.userService.getUserById(+userId)
  //     .subscribe( data => {
  //       this.editForm.setValue(data.result);
  //     });
  // }

  // onSubmit() {
  //   this.userService.updateUser(this.editForm.value)
  //     .pipe(first())
  //     .subscribe(
  //       data => {
  //         if(data.status === 200) {
  //           alert('User updated successfully.');
  //           this.router.navigate(['admin']);
  //         }else {
  //           alert(data.message);
  //         }
  //       },
  //       error => {
  //         alert("Couldnt update the user");
  //       });
  // }

}
