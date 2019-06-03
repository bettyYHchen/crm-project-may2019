export class UserResponse {
  name: string;
  username: string;
  email: string;
  positions: string[];
  status: string;
  statusAsOfDay: string;

  constructor(name: string, username: string, email: string, positions: string[], status: string,statusAsOfDay: string) {
    this.name = name;
    this.username = username;
    this.email = email;
    this.positions = positions;
    this.status = status,
    this.statusAsOfDay = statusAsOfDay;
}



}
