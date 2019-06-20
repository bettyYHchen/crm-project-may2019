export class TrainingClass {
  courseName: string;
  batch: string;
  instructorName: string;
  address: string;
  start: string;
  end: string;

  constructor(courseName: string, batch: string, instructorName: string,
              address: string, start: string, end: string) {
    this.courseName = courseName;
    this.batch = batch;
    this.instructorName = instructorName;
    this.address = address;
    this.start = start;
    this.end = end;
}
}
