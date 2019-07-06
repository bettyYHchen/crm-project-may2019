import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-get-drop-off',
  templateUrl: './get-drop-off.component.html',
  styleUrls: ['./get-drop-off.component.css']
})
export class GetDropOffComponent implements OnInit {

  board: string;
  errorMessage: string;
  users: any;


  constructor(private router: Router, private userService: UserService) { }

  ngOnInit() {
    this.userService.getDropOff().subscribe(
        data => {
          this.users = data;
          console.log(this.users);
        },
        err => console.log(err)
      );
    }

    onDelete(username: string) {
      if (confirm('Are you sure you want to delete this user?')) {
        this.userService.deleteUser(username)
        .subscribe(
          () => this.reloadPage(),
          (error: any) => console.error(error)
        );
      }
    }

    reloadPage() {
      window.location.reload();
    }



}
