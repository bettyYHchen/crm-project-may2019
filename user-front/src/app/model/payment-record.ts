export class PaymentRecord {
  transactionNumber: string;
  paidDate: string;
  paidAmount: number;
  paidLateFee: number;
  constructor(transactionNumber: string, paidDate: string, paidAmount: number, paidLateFee: number) {
    this.transactionNumber = transactionNumber;
    this.paidDate = paidDate;
    this.paidAmount = paidAmount;
    this.paidLateFee = paidLateFee;
    }

}
