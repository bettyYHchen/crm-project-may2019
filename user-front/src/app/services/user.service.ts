import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
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

const httpOptions = {
  'Content-Type': 'application/json'
};

@Injectable({
  providedIn: 'root'
})
export class UserService {
  apiUrl = environment.apiUrl;
  private userUrl = this.apiUrl + '/user';
  private pmUrl = this.apiUrl +'/pm';
  private adminUrl = this.apiUrl +'/admin';
  private leadSignUpUrl = this.apiUrl + '/api/auth/signupLead';
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
    //this is to get information to test in postman
    console.log(this.info.token);
    return this.http.get<ApiResponse>(this.adminUrl + "/users/"+ this.info.username);
  }

  getUserByUsername(username: string): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.adminUrl + "/user/" + username);
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

  updateLead(email: string, lead: Lead) {
    let body = JSON.stringify(lead);
    return this.http.put(this.pmUrl + '/leads/' + email, lead);
  }

  deleteLead(email: string) {
    return this.http.delete(this.pmUrl + '/leads/' + email);
  }

  changeLeadToStudent(email: string){
    return this.http.delete(this.pmUrl + '/changeLeadToStudent/' + email);
  }


  // getUserInfo(username: string): Observable<ApiResponse> {
  //   return this.http.get<ApiResponse>(this.userUrl + '/' + username);
  // }

  signUpLead(leadRequest: LeadRequest): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.leadSignUpUrl, leadRequest);
  }

  // get a list of students
  listStudents() {
    return this.http.get(this.userUrl + '/students');
  }

  listStudentByEmail(email: string) {
    return this.http.get(this.userUrl + '/students/' + email);
  }

  updateStudent(email: string, student: Student) {
    let body = JSON.stringify(student);
    return this.http.put(this.userUrl + '/students/' + email, student);
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

  updateIntern(email: string, intern: Intern) {
    let body = JSON.stringify(intern);
    return this.http.put(this.userUrl + '/interns/' + email, intern);
  }

  deleteIntern(email: string) {
    return this.http.delete(this.userUrl + '/interns/' + email);
  }

}
