import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HTTP_INTERCEPTORS,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthserviceService } from '../services/authservice.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authservice: AuthserviceService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    //add the JWT token which is stored in localStorage
    let authReq = req;
    const token = this.authservice.getToken();

    if (token != null) {
      authReq = authReq.clone({
        setHeaders: { Authorization: `Bearer ${token}` },
      });
    }
    return next.handle(authReq);
  }
}

export const authInterceptorProviders = [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true,
  },
];
