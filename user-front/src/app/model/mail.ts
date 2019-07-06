export class Mail {
  email: string;
  subject: string;
  message: string;
  clientName: string;
  className: string;
  classLocation: string;
  startDate: string;
  instructorName: string;

  constructor(email: string, subject: string, message: string, clientName: string,
              className: string, classLocation: string, startDate: string, instructorName: string) {
    this.email = email;
    this.subject = subject;
    this.message = message;
    this.clientName = clientName;
    this.className = className;
    this.classLocation = classLocation;
    this.startDate = startDate;
    this.instructorName = instructorName;
}
}
