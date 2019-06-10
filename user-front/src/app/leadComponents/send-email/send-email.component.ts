import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-send-email',
  templateUrl: './send-email.component.html',
  styleUrls: ['./send-email.component.css']
})
export class SendEmailComponent implements OnInit {

  confirmationMessage = '';

  constructor(private route: ActivatedRoute,  private userService: UserService) { }

  ngOnInit() {
    this.onSendPortalLink();
  }

  onSendPortalLink() {
    if (confirm('Are you sure you want to send portal link to this lead?')) {
      this.userService.sendEmailWithAttachment(this.route.snapshot.params.email)
      .subscribe(
        () => this.confirmationMessage = 'The portal link has been sent.',
        (error: any) => console.error(error)
      );
    }
  }

}
