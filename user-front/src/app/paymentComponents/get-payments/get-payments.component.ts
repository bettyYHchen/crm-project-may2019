import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-get-payments',
  templateUrl: './get-payments.component.html',
  styleUrls: ['./get-payments.component.css']
})
export class GetPaymentsComponent implements OnInit {
  public payments;
  public userId: number;

  constructor(private userService: UserService, private route: ActivatedRoute, private location: Location) { }

  ngOnInit() {
    this.userId = this.route.snapshot.params.userId;
    this.getPayments();
  }

  getPayments() {
    this.userService.listPaymentsByUserId(this.route.snapshot.params.userId).subscribe(
      data => {this.payments = data;
               console.log(data); },
      err => console.log(err),
      () => console.log('all payments are listed!')
    );
  }

  cancel() {
    this.location.back();
  }

}
