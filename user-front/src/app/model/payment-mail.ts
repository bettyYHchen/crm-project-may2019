export class PaymentMail {
  email: string;
  subject: string;
  message: string;
  paymentDate: string;
  unpaidAmount: number;
  constructor(email: string, subject: string, message: string, paymentDate: string, unpaidAmount: number) {
    this.email = email;
    this.subject = subject;
    this.message = message;
    this.paymentDate = paymentDate;
    this.unpaidAmount = unpaidAmount;
  }
}
