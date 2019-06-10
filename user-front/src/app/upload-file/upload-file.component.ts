import { Component, OnInit, ViewChild, ElementRef, Output, EventEmitter,  } from '@angular/core';
import { FileUploader } from 'ng2-file-upload';
import { environment } from 'src/environments/environment';
import { ActivatedRoute } from '@angular/router';
import { FileResponse } from '../model/file-response';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {
  apiUrl = environment.apiUrl;
  uploadUrl = this.apiUrl + '/api/file/uploadFile/';

  @ViewChild('fileInput') fileInput: ElementRef;

  uploader: FileUploader;
  isDropOver: boolean;
  hasUploaded: boolean;
  response: string[];
  fileName: string;
  downloadUrl: string;
  @Output() valueChange = new EventEmitter();
  constructor(private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    const headers = [{name: 'Accept', value: 'application/json'}];
    this.uploader = new FileUploader({url: this.uploadUrl + this.route.snapshot.params.email, autoUpload: true, headers});
    // this.uploader.onCompleteAll = () => {alert('File uploaded'); this.hasUploaded = true; };
// tslint:disable-next-line: max-line-length
    this.uploader.onCompleteItem = (item, response, status, headers) => {
      alert('File uploaded');
      this.hasUploaded = true;
      this.response = response.split(',', 4);
      this.fileName = this.response[0].split(':', 2)[1];
      this.valueChange.emit(this.fileName);
       };
  }

  fileOverAnother(e: any): void {
    this.isDropOver = e;
  }

  fileClicked() {
    this.fileInput.nativeElement.click();
  }

  // getFileName() {
  //   for (const item of this.uploader.queue) {
  //     console.log(item.file.name);
  //     this.fileNames.push(item.file.name);
  //   }
  //   // this.str = this.route.snapshot.params.email;
  //   // this.splitted = this.str.split('.', 2);
  //   // this.emailSignature = this.splitted[0];
  //   // this.fileName = this.fileNames.pop();
  //   // this.fileNameSignature = this.fileName.split('.', 2)[0];
  //   // this.fileNameExtension = this.fileName.split('.',2)[1];
  //   // this.fileName = this.fileNameSignature + '(' + this.emailSignature + ')' + '.' + this.fileNameExtension;
  //   // console.log(this.fileName);
  // }

}
