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
  constructor(
    private userservice: UserserviceService,
    private router: Router
  ) {}
  register() {
    // Add your registration logic here
    this.user.name = this.username;
    this.user.email = this.email;
    this.user.password = this.password;
    console.log('Username:', this.username);
    console.log('Email:', this.email);
    console.log('Password:', this.password);
    console.log('Confirm Password:', this.confirmPassword);
    console.log('USER: ', this.user);

    if (this.confirmPassword !== this.password) {
      alert("Password and confirm password doesn't matches");
    } else {
      this.userservice.registerUser(this.user).subscribe((data) => {
        console.log("Success!")
        this.userservice.setUser(data);
        this.userservice.isLoggedIn = true;
        this.router.navigate(['login']);
      });
    }
  }
}
