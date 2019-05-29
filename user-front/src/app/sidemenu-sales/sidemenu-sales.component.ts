import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-sidemenu-sales',
  templateUrl: './sidemenu-sales.component.html',
  styleUrls: ['./sidemenu-sales.component.css']
})
export class SidemenuSalesComponent implements OnInit {

  constructor(route: ActivatedRoute) {

    route.params.subscribe(params => console.log("side menu id parameter", params['id']));

}

  ngOnInit() {
  }

}
