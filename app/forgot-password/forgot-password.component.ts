import { Component, OnInit } from '@angular/core';
import { CapstoreService } from '../service/capstore.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css'],
})
export class ForgotPasswordComponent implements OnInit {
  email;
  submitted = false;
  message = 'Your password has been sent. Please check your e-mail.';
  constructor(
    private _capstoreService: CapstoreService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  onSubmit(form) {
    this.submitted = true;
    this._capstoreService
      .forgotPassword(this.email)
      .subscribe((error) => console.log(error));
    this.email = ' ';
  }
}
