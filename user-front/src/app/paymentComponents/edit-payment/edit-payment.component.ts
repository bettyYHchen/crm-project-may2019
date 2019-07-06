import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { PaymentMail } from 'src/app/model/payment-mail';

@Component({
  selector: 'app-edit-payment',
  templateUrl: './edit-payment.component.html',
  styleUrls: ['./edit-payment.component.css']
})
export class EditPaymentComponent implements OnInit {

  paymentStatusList: string[] = ['UNPAID', 'PAID', 'PAID_WITH_LATEFEE'];

  editForm: FormGroup;
  validMessage = '';
  private sub: Subscription;
  message: string;
  confirmationMessage: string;
  paymentExample: any;
  userId: number;
  paymentId: number;
  userResponse: any;
  paymentMail: PaymentMail;
  unpaidAmount: number;

  constructor(private fb: FormBuilder, private route: ActivatedRoute, private userService: UserService, private router: Router) {
   }

  ngOnInit() {
    this.userId = this.route.snapshot.params.userId;
    this.paymentId = this.route.snapshot.params.paymentId;
    this.updateForm();
    this.getPayment(this.userId, this.paymentId);
    this.getUserById(this.userId);
  }

  getUserById(id: number): void {
    this.userService.getUserById(id)
    .subscribe(
      data => {
        this.userResponse = data;
        console.log(this.userResponse); },
      (error: any) => console.error(error)
    );
  }


  getPayment(userId, paymentId): void {
    this.userService.listPaymentByUserIdAndPaymentId(userId, paymentId)
    .subscribe(
      data => {
        console.log(data);
        this.displayForm( data);
        },
      (error: any) => console.error(error)
    );
  }

  updateForm() {
    this.editForm = this.fb.group({
      date: '',
      regularFee: '',
      taxFee: '',
      lateFee: '',
      amount: '',
      status: '',
      paidDate: '',
      transactionNumber: ''
    });

  }


  displayForm(data: any): void {
    if (this.editForm) {
      this.editForm.reset();
    }
    this.paymentExample = data;
    this.editForm.patchValue({
        date: this.paymentExample.date,
        regularFee: this.paymentExample.regularFee,
        taxFee: this.paymentExample.taxFee,
        lateFee: this.paymentExample.lateFee,
        amount: this.paymentExample.amount,
        status: this.paymentExample.status,
        paidDate: this.paymentExample.paidDate,
        transactionNumber: this.paymentExample.transactionNumber
    });

  }

  onUpdate() {
    if (this.editForm.valid) {
      this.validMessage = 'The payment has been updated!';
      this.userService.updatePayment(this.userId, this.paymentId, this.editForm.value).subscribe(
        data => {
          this.message = 'The payment has been updated!';
          this.router.navigate(['payments/user/' + this.userId]);
          return true;
        },
        error => {
          alert('Couldnt update this payment info!'); });
    } else {
      this.validMessage = 'Please make sure the inputs are valid!';
    }
  }


  onDelete() {
    if (confirm('Are you sure you want to delete this payment?')) {
      this.userService.deletePayment(this.userId, this.paymentId)
      .subscribe(
        () => {this.editForm.reset(); this.router.navigate(['payments/user/' + this.userId]); },
        (error: any) => console.error(error)
      );
    }
  }

  onSendEmailForLatePayment() {
    if (this.paymentExample.status === 'UNPAID') { this.unpaidAmount = this.paymentExample.amount;
    } else if (this.paymentExample.status === 'PAID') { this.unpaidAmount = this.paymentExample.lateFee;
    } else { this.unpaidAmount = 0; }

    this.paymentMail = new PaymentMail(this.userResponse.email, 'BusyQA Payment Notification', this.userResponse.name,
                this.paymentExample.date, this.unpaidAmount);
    if (confirm('Are you sure you want to send the email?')) {
      this.userService.sendEmailForLatePayment(this.paymentMail)
      .subscribe(
        data => {
          this.confirmationMessage = 'The email has been sent!';
          return true;
        },
        (error: any) => console.error(error)
      );
    }
  }
}
