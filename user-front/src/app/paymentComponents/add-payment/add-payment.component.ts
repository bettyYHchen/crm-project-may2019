import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-add-payment',
  templateUrl: './add-payment.component.html',
  styleUrls: ['./add-payment.component.css']
})
export class AddPaymentComponent implements OnInit {

  paymentStatusList: string[] = ['UNPAID', 'PAID', 'PAID_WITH_LATEFEE'];

  postForm: FormGroup;
  validMessage = '';
  paymentExample: any;

  constructor(
    private userService: UserService,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit() {

    this.createForm();

  }



  createForm() {
    this.postForm = this.fb.group({
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

  onSubmit() {
    if (this.postForm.valid) {
      this.validMessage = 'The new payment has been saved. Thank you!';
      this.userService.createPayment(this.route.snapshot.params.userId, this.postForm.value).subscribe(
        data => {
          this.paymentExample = data;
          this.postForm.reset();
          return true;
        },
        error => {
          alert('Couldnt add this payment!'); }
      );
    } else {
      this.validMessage = 'Please fill out the payment info before submitting!';
    }
  }

  cancel() {
    this.location.back(); // <-- go back to previous location on cancel
  }

}
