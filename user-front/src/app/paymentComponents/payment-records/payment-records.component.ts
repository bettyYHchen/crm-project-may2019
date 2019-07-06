import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { PaymentRecord } from 'src/app/model/payment-record';

@Component({
  selector: 'app-payment-records',
  templateUrl: './payment-records.component.html',
  styleUrls: ['./payment-records.component.css']
})
export class PaymentRecordsComponent implements OnInit {

  public payments;
  public userId: number;
  public paymentRecords: PaymentRecord[] = [];
  public lateFeeRemain = 0;
  public balanceRemain = 0;

  constructor(private userService: UserService, private route: ActivatedRoute, private location: Location) { }

  ngOnInit() {
    this.userId = this.route.snapshot.params.userId;
    this.getPaymentRecords();
  }

  getPaymentRecords() {
    this.userService.listPaymentsByUserId(this.route.snapshot.params.userId).subscribe(
      data => {this.payments = data;
               for (let i in this.payments) {
                 if (this.payments[i].status === 'PAID_WITH_LATEFEE') {
                    this.paymentRecords.push(new PaymentRecord(this.payments[i].transactionNumber,
                    this.payments[i].paidDate, this.payments[i].amount, this.payments[i].lateFee));
                 } else if (this.payments[i].status === 'PAID') {
                    this.paymentRecords.push(new PaymentRecord(this.payments[i].transactionNumber,
                    this.payments[i].paidDate, this.payments[i].regularFee + this.payments[i].taxFee, 0));
                    this.lateFeeRemain = this.lateFeeRemain + this.payments[i].lateFee;
                    this.balanceRemain = this.balanceRemain + this.payments[i].lateFee;
                 } else {
                    this.balanceRemain = this.balanceRemain + this.payments[i].amount;
                 }}
               },
      err => console.log(err)
    );
  }

  cancel() {
    this.location.back();
  }

}
