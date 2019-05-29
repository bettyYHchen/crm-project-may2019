import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-sidemenu-admin',
  templateUrl: './sidemenu-admin.component.html',
  styleUrls: ['./sidemenu-admin.component.css']
})
export class SidemenuAdminComponent implements OnInit {

  constructor(route: ActivatedRoute) {

    route.params.subscribe(params => console.log("side menu id parameter", params['id']));

}

  ngOnInit() {
  }

}
