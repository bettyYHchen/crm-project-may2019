import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import {FlexLayoutModule} from '@angular/flex-layout';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import {FileUploadModule} from 'ng2-file-upload';
import { AutoSizeInputModule } from 'ngx-autosize-input';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';


import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { PmComponent } from './pm/pm.component';
import { ReactiveFormsModule } from '@angular/forms';
import { EditUserComponent } from './edit-user/edit-user.component';

import { httpInterceptorProviders } from './auth/auth-interceptor';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { AddUserComponent } from './add-user/add-user.component';
import { LeadSignupComponent } from './lead-signup/lead-signup.component';
import { GetLeadsComponent } from './leadComponents/get-leads/get-leads.component';
import { EditLeadComponent } from './leadComponents/edit-lead/edit-lead.component';
import { SidemenuSalesComponent } from './sidemenu-sales/sidemenu-sales.component';
import { UserListComponent } from './user-list/user-list.component';
import { AdminActionCardComponent } from './admin-action-card/admin-action-card.component';
import { SidemenuAdminComponent } from './sidemenu-admin/sidemenu-admin.component';
import { GetStudentsComponent } from './studentComponents/get-students/get-students.component';
import { SidemenuAccountComponent } from './sidemenu-account/sidemenu-account.component';
import { EditStudentComponent } from './studentComponents/edit-student/edit-student.component';
import { GetInternsComponent } from './internComponents/get-interns/get-interns.component';
import { EditInternComponent } from './internComponents/edit-intern/edit-intern.component';
import { ClientResetPasswordComponent } from './leadPortalComponents/client-reset-password/client-reset-password.component';
import { WhatIamComponent } from './what-iam/what-iam.component';
import { ClientFirstTimeUpdateComponent } from './leadPortalComponents/client-first-time-update/client-first-time-update.component';
import { SendEmailComponent } from './leadComponents/send-email/send-email.component';
import { UploadFileComponent } from './upload-file/upload-file.component';
import { GetResumesComponent } from './resumeComponents/get-resumes/get-resumes.component';
import { GetMocksComponent } from './mockComponents/get-mocks/get-mocks.component';
import { EditResumeComponent } from './resumeComponents/edit-resume/edit-resume.component';
import { EditMockComponent } from './mockComponents/edit-mock/edit-mock.component';
import {FileValidator} from './file-input.validator';
import { AccountActionCardComponent } from './account-action-card/account-action-card.component';
import { GetAlumnusComponent } from './alumniComponents/get-alumnus/get-alumnus.component';
import { EditAlumniComponent } from './alumniComponents/edit-alumni/edit-alumni.component';
import { GetClientProfileComponent } from './get-client-profile/get-client-profile.component';
import { GetPaymentsComponent } from './paymentComponents/get-payments/get-payments.component';
import { AddPaymentComponent } from './paymentComponents/add-payment/add-payment.component';
import { EditPaymentComponent } from './paymentComponents/edit-payment/edit-payment.component';
import { GetCoursesComponent } from './courseComponent/get-courses/get-courses.component';
import { AddCourseComponent } from './courseComponent/add-course/add-course.component';
import { EditCourseComponent } from './courseComponent/edit-course/edit-course.component';
import { GetClassesComponent } from './classComponent/get-classes/get-classes.component';
import { AddClassComponent } from './classComponent/add-class/add-class.component';
import { EditClassComponent } from './classComponent/edit-class/edit-class.component';
import { GetInstructorsComponent } from './instructorComponent/get-instructors/get-instructors.component';
import { AddInstructorComponent } from './instructorComponent/add-instructor/add-instructor.component';
import { EditInstructorComponent } from './instructorComponent/edit-instructor/edit-instructor.component';
import { AcademicActionCardComponent } from './academic-action-card/academic-action-card.component';
import { UpdateRatesComponent } from './update-rates/update-rates.component';
import { GetDropOffComponent } from './get-drop-off/get-drop-off.component';
import { PaymentRecordsComponent } from './paymentComponents/payment-records/payment-records.component';

// ANGULAR MATERIAL
import {MatButtonModule,
  MatCheckboxModule,
  MatTabsModule} from '@angular/material';
import {MatGridListModule} from '@angular/material/grid-list';

// FONT AWESOME
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';
import { fab } from '@fortawesome/free-brands-svg-icons';
import { CrmHeaderComponent } from './header/crm-header/crm-header.component';
library.add(fas, far, fab);




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    RegisterComponent,
    HomeComponent,
    AdminComponent,
    EditUserComponent,
    PmComponent,
    ResetPasswordComponent,
    AddUserComponent,
    LeadSignupComponent,
    GetLeadsComponent,
    EditLeadComponent,
    SidemenuSalesComponent,
    UserListComponent,
    AdminActionCardComponent,
    SidemenuAdminComponent,
    GetStudentsComponent,
    SidemenuAccountComponent,
    EditStudentComponent,
    GetInternsComponent,
    EditInternComponent,
    ClientResetPasswordComponent,
    WhatIamComponent,
    ClientFirstTimeUpdateComponent,
    SendEmailComponent,
    UploadFileComponent,
    GetResumesComponent,
    GetMocksComponent,
    EditResumeComponent,
    EditMockComponent,
    FileValidator,
    AccountActionCardComponent,
    GetAlumnusComponent,
    EditAlumniComponent,
    GetClientProfileComponent,
    GetPaymentsComponent,
    AddPaymentComponent,
    EditPaymentComponent,
    GetCoursesComponent,
    AddCourseComponent,
    EditCourseComponent,
    GetClassesComponent,
    AddClassComponent,
    EditClassComponent,
    GetInstructorsComponent,
    AddInstructorComponent,
    EditInstructorComponent,
    AcademicActionCardComponent,
    UpdateRatesComponent,
    GetDropOffComponent,
    PaymentRecordsComponent,
    CrmHeaderComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    HttpClientModule,
    AutoSizeInputModule,
    NgMultiSelectDropDownModule.forRoot(),
    FlexLayoutModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    FileUploadModule,
    MatButtonModule,
    MatCheckboxModule,
    MatTabsModule,
    MatGridListModule,
    NgbModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
