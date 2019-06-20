import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

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
  paymentExample: any;
  userId: number;
  paymentId: number;

  constructor(private fb: FormBuilder, private route: ActivatedRoute, private userService: UserService, private router: Router) {
   }

  ngOnInit() {
    this.userId = this.route.snapshot.params.userId;
    this.paymentId = this.route.snapshot.params.paymentId;
    this.updateForm();
    this.getPayment(this.userId, this.paymentId);
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
      status: ''
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
        status: this.paymentExample.status
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
}
