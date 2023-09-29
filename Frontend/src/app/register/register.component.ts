import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserserviceService } from '../services/userservice.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  username: string = '';
  email: string = '';
  password: string = '';
  confirmPassword: string = '';
  user = new User();
  showPassword : Boolean = false;
  constructor(
    private userservice: UserserviceService,
    private router: Router
  ) {}
  register() {
    this.user.name = this.username;
    this.user.email = this.email;
    this.user.password = this.password;

    if (this.confirmPassword !== this.password) {
      alert("Password and confirm password doesn't matches");
    } else {
      this.userservice.registerUser(this.user).subscribe((data) => {
        this.userservice.setUser(data);
        this.userservice.isLoggedIn = true;
        this.router.navigate(['login']);
      });
    }
  }
}
