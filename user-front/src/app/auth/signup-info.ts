export class SignUpInfo {
    name: string;
    username: string;
    email: string;
    positions: string[];
    password: string;

    constructor(name: string, username: string, email: string, password: string) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.positions = ['ROLE_USER,TEAM_UNASSIGNED'];

    }
}
