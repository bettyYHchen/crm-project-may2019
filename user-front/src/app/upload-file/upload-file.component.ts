import { Component, OnInit, ViewChild, ElementRef, Output, EventEmitter,  } from '@angular/core';
import { FileUploader } from 'ng2-file-upload';
import { environment } from 'src/environments/environment';
import { ActivatedRoute } from '@angular/router';

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
    this.fileName = 'PaymentPlanAgreement'  +
                    this.route.snapshot.params.fileNameComp;
    this.uploader = new FileUploader({url: this.uploadUrl + this.fileName, autoUpload: true, headers});
    this.uploader.onCompleteItem = (item, response, status, headers) => {
      alert('File uploaded');
      this.hasUploaded = true;
      this.valueChange.emit(this.fileName);
       };
  }

  fileOverAnother(e: any): void {
    this.isDropOver = e;
  }

  fileClicked() {
    this.fileInput.nativeElement.click();
  }

}
