import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Course } from '../model/course';
import { Instructor } from '../model/instructor';

@Injectable({
  providedIn: 'root'
})
export class AcademicService {

  apiUrl = environment.apiUrl;
  private academicUrl = this.apiUrl + '/academic';

  constructor(private http: HttpClient) { }

  // operations that can be done on courses
  getCourses() {
    return this.http.get(this.academicUrl + '/courses');
  }


  getCourseById(id: number) {
    return this.http.get<Course>(this.academicUrl + '/courses/' + id);
  }

  addCourse(course: Course) {
    let body = JSON.stringify(course);
    return this.http.post(this.academicUrl + '/courses', body);

  }

  deleteCourse(id: number) {
    return this.http.delete(this.academicUrl + '/courses/' + id);
  }

  // operations that can be done on instructors
  getInstructors() {
    return this.http.get(this.academicUrl + '/instructors');
  }


  getInstructorById(id: number) {
    return this.http.get<Course>(this.academicUrl + '/instructors/' + id);
  }

  addInstructor(instructor: Instructor) {
    let body = JSON.stringify(instructor);
    return this.http.post(this.academicUrl + '/instructors', body);

  }

  deleteInstructor(id: number) {
    return this.http.delete(this.academicUrl + '/instructors/' + id);
  }



}
