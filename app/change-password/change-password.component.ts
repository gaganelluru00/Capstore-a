import { Component, OnInit } from '@angular/core';
import { CapstoreService } from '../service/capstore.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-changepassword',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css'],
})
export class ChangePasswordComponent implements OnInit {
  currentPassword;
  newPassword;
  confirmPassword;
  email;
  submitted = false;
  message = 'Your password has been changed. Use your new password for Login.';
  constructor(
    private _capstoreService: CapstoreService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.email = this._capstoreService.email;
  }

  onSubmit(form) {
    if (this.newPassword == this.confirmPassword) {
      this._capstoreService
        .changePassword(
          this.currentPassword,
          this.newPassword,
          this._capstoreService.email
        )
        .subscribe((error) => console.log(error));
      this.submitted = true;
    }
  }
}
