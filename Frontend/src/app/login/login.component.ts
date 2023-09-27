import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserserviceService } from '../services/userservice.service';
import { Router } from '@angular/router';
import { AuthserviceService } from '../services/authservice.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  Email: string = '';
  password: string = '';
  user = new User();

  constructor(
    private userservice: UserserviceService,
    private router: Router,
    private authservice: AuthserviceService
  ) {}

  ngOnInit(): void {}

  login() {
    this.user.email = this.Email;
    this.user.password = this.password;

    localStorage.clear();
    const loginData = {
      userName: this.Email,
      password: this.password,
    };
    this.authservice.generateToken(loginData).subscribe(
      (data: any) => {
        this.authservice.loginUserToken(data.token);
        this.authservice.getCurrentUser().subscribe((user: any) => {
          this.userservice.setUser(user);
          this.router.navigate(['home']);
        });
      },
      (error) => {
        console.log('Error');
        alert('No such user exist');
      }
    );
  }
}
