import { Component, OnInit } from '@angular/core';
import { CapstoreService } from '../service/capstore.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  constructor(private _capstoreService:CapstoreService,private router:Router) { }
p;
  ngOnInit(): void {
    this.p=this._capstoreService.getSomeData();
  }
onSubmit()
{
  this._capstoreService.setLoggedIn(false);
  this.router.navigate(['login']);
}
}
