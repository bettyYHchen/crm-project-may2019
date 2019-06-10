import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { PmComponent } from './pm/pm.component';
import { AdminComponent } from './admin/admin.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { AddUserComponent } from './add-user/add-user.component';
import { LeadSignupComponent } from './lead-signup/lead-signup.component';
import { GetLeadsComponent } from './leadComponents/get-leads/get-leads.component';
import { EditLeadComponent } from './leadComponents/edit-lead/edit-lead.component';
import { SidemenuSalesComponent } from './sidemenu-sales/sidemenu-sales.component';
import { AdminActionCardComponent } from './admin-action-card/admin-action-card.component';
import { SidemenuAdminComponent } from './sidemenu-admin/sidemenu-admin.component';
import { UserListComponent } from './user-list/user-list.component';
import { GetStudentsComponent } from './studentComponents/get-students/get-students.component';
import { SidemenuAccountComponent } from './sidemenu-account/sidemenu-account.component';
import { EditStudentComponent } from './studentComponents/edit-student/edit-student.component';
import { EditInternComponent } from './internComponents/edit-intern/edit-intern.component';
import { GetInternsComponent } from './internComponents/get-interns/get-interns.component';
import { ClientResetPasswordComponent } from './leadPortalComponents/client-reset-password/client-reset-password.component';
import { WhatIamComponent } from './what-iam/what-iam.component';
import { ClientFirstTimeUpdateComponent } from './leadPortalComponents/client-first-time-update/client-first-time-update.component';
import { SendEmailComponent } from './leadComponents/send-email/send-email.component';
import { UploadFileComponent } from './upload-file/upload-file.component';
import { GetResumesComponent } from './resumeComponents/get-resumes/get-resumes.component';
import { GetMocksComponent } from './mockComponents/get-mocks/get-mocks.component';
import { EditResumeComponent } from './resumeComponents/edit-resume/edit-resume.component';
import { EditMockComponent } from './mockComponents/edit-mock/edit-mock.component';
import { AccountActionCardComponent } from './account-action-card/account-action-card.component';
import { GetAlumnusComponent } from './alumniComponents/get-alumnus/get-alumnus.component';
import { EditAlumniComponent } from './alumniComponents/edit-alumni/edit-alumni.component';
import { GetClientProfileComponent } from './get-client-profile/get-client-profile.component';



const routes: Routes = [
    {
      path: 'alumnus',
      component: GetAlumnusComponent
    },
    {
      path: 'alumnus/view/:email',
      component: EditAlumniComponent
    },
    {
      path: 'whatIam/:username',
      component: WhatIamComponent
    },
    {
      path: 'myProfile',
      component: GetClientProfileComponent
    },
    {
      path: 'client/uploadFile/:email',
      component: UploadFileComponent
    },
    {
      path: 'client/sendPortalLink/:email',
      component: SendEmailComponent
    },
    {
      path: 'client/resetPassword/:email',
      component: ClientResetPasswordComponent
    },
    {
      path: 'client/register/:email',
      component: ClientFirstTimeUpdateComponent
    },
    {
      path: 'resumes/view/:email',
      component: EditResumeComponent
    },
    {
      path: 'resumes',
      component: GetResumesComponent
    },
    {
      path: 'mocks/view/:email',
      component: EditMockComponent
    },
    {
      path: 'mocks',
      component: GetMocksComponent
    },
    {
      path: 'interns/view/:email',
      component: EditInternComponent
    },
    {
      path: 'interns',
      component: GetInternsComponent
    },
    {
      path: 'students/view/:email',
      component: EditStudentComponent
    },
    {
      path: 'students',
      component: GetStudentsComponent
    },
    {
      path: 'leads/view/:email',
      component: EditLeadComponent,
    },
    {
      path: 'leads',
      component: GetLeadsComponent
    },
    {
      path: 'leads/view/:email',
      component: EditLeadComponent,
    },
    {
      path: 'leads',
      component: GetLeadsComponent
    },
    {
        path: 'signUpLead',
        component: LeadSignupComponent
    },
    {
        path: 'home',
        component: HomeComponent
    },
    {
        path: 'adduser',
        component: AddUserComponent
    },
    {
        path: 'user-list',
        component: UserListComponent
    },
    {
        path: 'resetPassword',
        component: ResetPasswordComponent
    },
    {
        path: 'pm',
        component: PmComponent,
        children: [
          {
            path: '',
            component: GetLeadsComponent
          },
          {
            path: '',
            outlet: 'sidemenu-sales',
            component: SidemenuSalesComponent
        }
        ]
    },
    {
        path: 'user',
        component: UserComponent,
        children: [
          {
            path: '',
            component: AccountActionCardComponent
          },
          {
            path: '',
            outlet: 'sidemenu-account',
            component: SidemenuAccountComponent
          }
        ]
    },
    {
        path: 'admin',
        component: AdminComponent,
        children: [
          {
            path: '',
            component: AdminActionCardComponent
          },
          {
            path: '',
            outlet: 'sidemenu-admin',
            component: SidemenuAdminComponent
        }
        ]
    },
    {
        path: 'auth/login',
        component: LoginComponent
    },
    {
        path: 'users/view/:username',
        component: EditUserComponent
    },
    {
        path: 'signup',
        component: RegisterComponent
    },

    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
