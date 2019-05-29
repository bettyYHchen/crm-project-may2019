import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-get-leads',
  templateUrl: './get-leads.component.html',
  styleUrls: ['./get-leads.component.css']
})
export class GetLeadsComponent implements OnInit {

  public leads;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getLeads();
  }

  getLeads() {
    this.userService.listLeads().subscribe(
      data => {this.leads = data;
               console.log(data); },
      err => console.log(err),
      () => console.log('all leads are listed!')
    );
  }

}
