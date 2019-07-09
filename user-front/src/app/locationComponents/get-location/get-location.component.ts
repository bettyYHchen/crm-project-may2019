import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-get-location',
  templateUrl: './get-location.component.html',
  styleUrls: ['./get-location.component.css']
})
export class GetLocationComponent implements OnInit {

  public locations;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.getCourses();
  }

  getCourses() {
    this.userService.getLocation().subscribe(
      data => {this.locations = data;
               console.log(data); },
      err => console.log(err),
      () => console.log('all training locations are listed!')
    );
  }

  onDelete(id: number) {
    if (confirm('Are you sure you want to delete the training location?')) {
      this.userService.deleteLocation(id)
      .subscribe(
        () => {this.reloadPage(); },
        (error: any) => console.error(error)
      );
    }}

    reloadPage() {
      window.location.reload();
    }

}
