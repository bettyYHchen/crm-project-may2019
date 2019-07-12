import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GeodataService {

  private geoDataUrl = 'https://geodata.solutions/restapi';
  info: any;

  constructor(private http: HttpClient) { }

  getCountries() {
    return this.http.get( this.geoDataUrl );
  }

  getStates(country: string) {
    return this.http.get(this.geoDataUrl + '?country=' + country);
  }

  getCities(country: string, state: string) {
    return this.http.get(this.geoDataUrl + '?country=' +
      country + '&state=' + state);
  }

}
