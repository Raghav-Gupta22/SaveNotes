import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
} from '@angular/router';
import { AuthserviceService } from '../services/authservice.service';
import { UserserviceService } from '../services/userservice.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(
    private authService: AuthserviceService,
    private router: Router,
    private userService: UserserviceService
  ) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    if (this.userService.isLogIn()) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }
}
