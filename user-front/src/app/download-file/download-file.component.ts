import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-download-file',
  templateUrl: './download-file.component.html',
  styleUrls: ['./download-file.component.css']
})
export class DownloadFileComponent implements OnInit {

  fileNameDownload: string;
  clientEmail: string;

  constructor(private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit() {
    this.clientEmail = this.route.snapshot.params.email;
    this.fileNameDownload = 'PaymentPlanAgreement' + '(' + this.clientEmail.split('.', 2)[0] + ')' + '.pdf';
    this.onDownload();
  }

  onDownload() {
    if (confirm('Do you want to download the paymentplan agreement?')) {
      this.userService.downloadPDF(this.fileNameDownload);
    }
  }

}
