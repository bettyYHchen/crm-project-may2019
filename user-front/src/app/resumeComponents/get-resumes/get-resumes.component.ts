import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-get-resumes',
  templateUrl: './get-resumes.component.html',
  styleUrls: ['./get-resumes.component.css']
})
export class GetResumesComponent implements OnInit {

  public resumes;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getResumes();
  }

  getResumes() {
    this.userService.listResumes().subscribe(
      data => {this.resumes = data;
               console.log(data); },
      err => console.log(err),
      () => console.log('all resumes are listed!')
    );
  }

}
