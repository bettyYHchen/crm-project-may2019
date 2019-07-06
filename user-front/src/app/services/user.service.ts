import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, from } from 'rxjs';
import { environment } from '../../environments/environment';
import {ApiResponse} from "../services/api.response";
import { TokenStorageService } from '../auth/token-storage.service';
import { InternalFormsSharedModule } from '@angular/forms/src/directives';
import {User} from "../model/user.model";
import { UserRequest } from '../model/user-request';
import { LeadRequest } from '../model/lead-request';
import { Lead } from '../model/lead';
import { Student } from '../model/student';
import { Intern } from '../model/intern';
import { StudentRequest } from '../model/student-request';
import { InternRequest } from '../model/intern-request';
import { LeadClientRequest } from '../model/lead-client-request';
import { ClientRequest } from '../model/client-request';
import { Mail } from '../model/mail';
import { ResumeRequest } from '../model/resume-request';
import { MockRequest } from '../model/mock-request';
import { AlumniRequest } from '../model/alumni-request';
import { Course } from '../model/course';
import { TrainingClass } from '../model/training-class';
import { Instructor } from '../model/instructor';
import { ClassFinishedStatus } from '../model/class-finished-status';
import { SettingRequest } from '../model/setting-request';
import { PaymentMail } from '../model/payment-mail';

const httpOptions = {
  'Content-Type': 'application/json'
};

@Injectable({
  providedIn: 'root'
})
export class UserService {
  apiUrl = environment.apiUrl;
  private userUrl = this.apiUrl + '/user';
  private pmUrl = this.apiUrl + '/pm';
  private adminUrl = this.apiUrl + '/admin';
  private leadSignUpUrl = this.apiUrl + '/api/auth/signupLead';
  private myInfoUrl = this.apiUrl + '/api/auth/myInfo/';
  private changeLeadInfoUrl = this.apiUrl + '/api/auth/changeLeadInfo/';
  private changeClientInfoUrl = this.apiUrl + '/api/auth/changeClientInfo/';
  private sendEmailWithAttachmentUrl = this.apiUrl + '/sendEmailWithAttachment/';
  private sendEmailTemplateUrl = this.apiUrl + '/sendEmailWithTemplate/';
  private sendEmailForLatePaymentUrl = this.apiUrl + '/sendEmailForLatePayment/';
  private allTeamsUrl = this.apiUrl + '/allTeams';
  private paymentRecordsUrl = this.apiUrl + '/users/';
  private courseUrl = this.apiUrl + '/academic/courses';
  private classUrl = this.apiUrl + '/academic/classes';
  private classByNameUrl = this.apiUrl + '/academic/classByName';
  private instructorUrl = this.apiUrl + '/academic/instructors';
  private getSettingUrl = this.apiUrl + '/academic/getRates';
  private updateSettingUrl = this.apiUrl + '/academic/updateSetting';
  private dropOffUsersUrl = this.apiUrl + '/dropOffUsers';
  private getUserByIdUrl = this.apiUrl + '/getUserById/';
  info: any;

  constructor(private http: HttpClient, private token: TokenStorageService) { }

  getUserBoard(): Observable<string> {
    return this.http.get(this.userUrl, { responseType: 'text' });
  }

  getPMBoard(): Observable<string> {
    return this.http.get(this.pmUrl, { responseType: 'text' });
  }

  getAdminBoard(): Observable<string> {
    return this.http.get(this.adminUrl, { responseType: 'text' });
  }

  getUsersByTeam(): Observable<ApiResponse> {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    }
    // this is to get information to test in postman
    console.log(this.info.token);
    return this.http.get<ApiResponse>(this.adminUrl + "/users/"+ this.info.username);
  }

  getUserByUsername(username: string): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.myInfoUrl + username);
  }

  updateUser(username: string, user: UserRequest): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(this.adminUrl +"/user/" + username, user);
  }

  deleteUser(username: string): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(this.adminUrl + "/user/" + username);
  }


  // getPmUserByTeam(): Observable<ApiResponse> {
  //   this.info = {
  //     token: this.token.getToken(),
  //     username: this.token.getUsername(),
  //     authorities: this.token.getAuthorities()
  //   }
  //   return this.http.get<ApiResponse>(this.pmUrl + "/users/"+ this.info.username);
  // }

  // PM service to manage leads

  // get a list of leads
  listLeads() {
    // this.info = {
    //   token: this.token.getToken(),
    //   username: this.token.getUsername(),
    //   authorities: this.token.getAuthorities()
    // };
    return this.http.get(this.pmUrl + '/leads');
  }

  listLeadByEmail(email: string) {
    return this.http.get(this.pmUrl + '/leads/' + email);
  }

  updateLead(email: string, leadRequest: LeadRequest) {
    let body = JSON.stringify(leadRequest);
    return this.http.put(this.pmUrl + '/leads/' + email, leadRequest);
  }

  deleteLead(email: string) {
    return this.http.delete(this.pmUrl + '/leads/' + email);
  }

  changeLeadToStudent(email: string) {
    return this.http.delete(this.pmUrl + '/changeLeadToStudent/' + email);
  }


  // getUserInfo(username: string): Observable<ApiResponse> {
  //   return this.http.get<ApiResponse>(this.userUrl + '/' + username);
  // }

  signUpLead(leadRequest: LeadRequest): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.leadSignUpUrl, leadRequest);
  }

  // send welcome package: instructor and course info
  sendTemplateEmail(mail: Mail) {
    return this.http.post(this.sendEmailTemplateUrl, mail);
  }

  // send portal link and agreement to sign
  sendEmailWithAttachment(email: string) {
    return this.http.get(this.sendEmailWithAttachmentUrl + email);
  }

  // send email for late payment
  sendEmailForLatePayment(paymentMail: PaymentMail) {
    return this.http.post(this.sendEmailForLatePaymentUrl, paymentMail);
  }

  // get a list of students
  listStudents() {
    return this.http.get(this.userUrl + '/students');
  }

  listStudentByEmail(email: string) {
    return this.http.get(this.userUrl + '/students/' + email);
  }

  updateStudent(email: string, studentRequest: StudentRequest) {
    let body = JSON.stringify(studentRequest);
    return this.http.put(this.userUrl + '/students/' + email, studentRequest);
  }

  deleteStudent(email: string) {
    return this.http.delete(this.userUrl + '/students/' + email);
  }

  changeStudentToIntern(email: string) {
    return this.http.delete(this.userUrl + '/changeStudentToIntern/' + email);
  }

  // get a list of interns
  listInterns() {
    return this.http.get(this.userUrl + '/interns');
  }

  listInternByEmail(email: string) {
    return this.http.get(this.userUrl + '/interns/' + email);
  }

  updateIntern(email: string, internRequest: InternRequest) {
    let body = JSON.stringify(internRequest);
    return this.http.put(this.userUrl + '/interns/' + email, internRequest);
  }

  deleteIntern(email: string) {
    return this.http.delete(this.userUrl + '/interns/' + email);
  }

  changeInternToResume(email: string) {
    return this.http.delete(this.userUrl + '/changeInternToResume/' + email);
  }

  // get lead for first time client info update
  listLeadClientByEmail(email: string) {
    return this.http.get(this.changeLeadInfoUrl + email);
  }

  updateLeadClient(email: string, leadClientRequest: LeadClientRequest) {
    let body = JSON.stringify(leadClientRequest);
    return this.http.put(this.changeLeadInfoUrl + email, leadClientRequest);
  }

  // get client in the personal page for updating his info
  listClientByEmail(email: string) {
    return this.http.get(this.changeClientInfoUrl + email);
  }

  updateClient(email: string, clientRequest: ClientRequest) {
    let body = JSON.stringify(clientRequest);
    return this.http.put(this.changeClientInfoUrl + email, clientRequest);
  }

  // services for resumes
  listResumes() {
    return this.http.get(this.allTeamsUrl + '/resumes');
  }

  listResumeByEmail(email: string) {
    return this.http.get(this.allTeamsUrl + '/resumes/' + email);
  }

  updateResume(email: string, resumeRequest: ResumeRequest) {
    let body = JSON.stringify(resumeRequest);
    return this.http.put(this.allTeamsUrl + '/resumes/' + email, resumeRequest);
  }

  deleteResume(email: string) {
    return this.http.delete(this.allTeamsUrl + '/resumes/' + email);
  }

  changeResumeToMock(email: string) {
    return this.http.delete(this.allTeamsUrl + '/changeResumeToMock/' + email);
  }

  // services for mocks
  listMocks() {
    return this.http.get(this.allTeamsUrl + '/mocks');
  }

  listMockByEmail(email: string) {
    return this.http.get(this.allTeamsUrl + '/mocks/' + email);
  }

  updateMock(email: string, mockRequest: MockRequest) {
    let body = JSON.stringify(mockRequest);
    return this.http.put(this.allTeamsUrl + '/mocks/' + email, mockRequest);
  }

  deleteMock(email: string) {
    return this.http.delete(this.allTeamsUrl + '/mocks/' + email);
  }

  changeMockToAlumni(email: string) {
    return this.http.delete(this.allTeamsUrl + '/changeMockToAlumni/' + email);
  }

  // services for alumnus
  listAlumnus() {
    return this.http.get(this.allTeamsUrl + '/alumnus');
  }

  listAlumniByEmail(email: string) {
    return this.http.get(this.allTeamsUrl + '/alumnus/' + email);
  }

  updateAlumni(email: string, alumniRequest: AlumniRequest) {
    let body = JSON.stringify(alumniRequest);
    return this.http.put(this.allTeamsUrl + '/alumnus/' + email, alumniRequest);
  }

  deleteAlumni(email: string) {
    return this.http.delete(this.allTeamsUrl + '/alumnus/' + email);
  }

  // crud service for payments
  listPaymentsByUserId(userId: number) {
    return this.http.get(this.paymentRecordsUrl + userId + '/payments');
  }

  listPaymentByUserIdAndPaymentId(userId: number, paymentId: number) {
    return this.http.get(this.paymentRecordsUrl + userId + '/payments/' + paymentId);
  }

  createPayment(userId: number, paymentRequest: PaymentRequest) {
    return this.http.post(this.paymentRecordsUrl + userId + '/payments', paymentRequest);
  }

  updatePayment(userId: number, paymentId: number, paymentRequest: PaymentRequest) {
    return this.http.put(this.paymentRecordsUrl + userId + '/payments/' + paymentId,
    paymentRequest);
  }

  deletePayment(userId: number, paymentId: number) {
    return this.http.delete(this.paymentRecordsUrl + userId + '/payments/' + paymentId);
  }

  updateLeadPaymentStatus(userId: number) {
    return this.http.get(this.apiUrl + '/updateLeadPaymentStatus/' + userId);
  }

  updatePaidAmount(userId: number) {
    return this.http.get(this.apiUrl + '/updatePaidAmount/' + userId);
  }

  // services for academic
  // get list
  listCourses() {
    return this.http.get(this.courseUrl);
  }

  listClasses() {
    return this.http.get(this.classUrl);
  }

  listInstructors() {
    return this.http.get(this.instructorUrl);
  }

  // get particular
  listCourseByName(name: string) {
    return this.http.get(this.courseUrl + '/' + name);
  }

  listClassByName(name: string) {
    return this.http.get(this.classByNameUrl + '/' + name);
  }

  listClassById(id: number) {
    return this.http.get(this.classUrl + '/' + id);
  }

  listInstructorById(id: number) {
    return this.http.get(this.instructorUrl + '/' + id);
  }

  // create particular
  createCourse(course: Course) {
    return this.http.post(this.courseUrl, course);
  }

  createClass(trainingClass: TrainingClass) {
    console.log(this.classUrl);
    return this.http.post(this.classUrl, trainingClass);
  }

  createInstructor(instructor: Instructor) {
    return this.http.post(this.instructorUrl, instructor);
  }

  // update particular
  updateCourse(name: string, course: Course) {
    return this.http.put(this.courseUrl + '/' + name, course);
  }

  // delete particular
  deleteCourse(id: number) {
    return this.http.delete(this.courseUrl + '/' + id);
  }

  deleteClass(id: number) {
    return this.http.delete(this.classUrl + '/' + id);
  }

  deleteInstructor(id: number) {
    return this.http.delete(this.instructorUrl + '/' + id);
  }

  // get classes based on course name
  getClassesByCourse(name: string) {
    return this.http.get(this.courseUrl + '/courses/classes/' + name);
  }

  // update class status
  updateClassFinishedStatus(id: number, classFinishedStatus: ClassFinishedStatus) {
    console.log(classFinishedStatus);
    return this.http.put(this.classUrl + '/' + id, classFinishedStatus);
  }

  // get late fee rate and tax rate in system
  getSetting() {
    return this.http.get(this.getSettingUrl);
  }
  // update late fee rate and tax rate
  updateSetting(settingRequest: SettingRequest) {
    return this.http.put(this.updateSettingUrl, settingRequest);
  }

  // get a list of drop-off users
  getDropOff() {
    return this.http.get(this.dropOffUsersUrl);
  }

  // get user by id
  getUserById(id: number) {
    return this.http.get(this.getUserByIdUrl + id);
  }

}
