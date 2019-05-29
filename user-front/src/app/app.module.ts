import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import {FlexLayoutModule} from '@angular/flex-layout';

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
import { CourseListComponent } from './course-list/course-list.component';
import { UserListComponent } from './user-list/user-list.component';
import { AdminActionCardComponent } from './admin-action-card/admin-action-card.component';
import { SidemenuAdminComponent } from './sidemenu-admin/sidemenu-admin.component';
import { GetStudentsComponent } from './studentComponents/get-students/get-students.component';
import { SidemenuAccountComponent } from './sidemenu-account/sidemenu-account.component';
import { EditStudentComponent } from './studentComponents/edit-student/edit-student.component';
import { GetInternsComponent } from './internComponents/get-interns/get-interns.component';
import { EditInternComponent } from './internComponents/edit-intern/edit-intern.component';


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
    CourseListComponent,
    UserListComponent,
    AdminActionCardComponent,
    SidemenuAdminComponent,
    GetStudentsComponent,
    SidemenuAccountComponent,
    EditStudentComponent,
    GetInternsComponent,
    EditInternComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgMultiSelectDropDownModule.forRoot(),
    FlexLayoutModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
