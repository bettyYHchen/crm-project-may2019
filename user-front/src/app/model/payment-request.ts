export class PaymentRequest {
    date: string;
    regularFee: number;
    taxFee: number;
    lateFee: number;
    amount: number;
    status: string;
    paidDate = '';
    transactionNumber = '';
}
