import { Position } from './position';

export class User {

  id: number;
  password: string;
  name: string;
  username: string;
  email: string;
  positions: Position[];
}
