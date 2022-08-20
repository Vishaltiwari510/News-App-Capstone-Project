import { Injectable } from '@angular/core';
import { AuthenticationService } from '../user-auth/user-auth.service';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor{

  constructor(private authService : AuthenticationService) { }

  intercept(request : HttpRequest<any>, next : HttpHandler) : Observable<HttpEvent<any>>
  {
    request = request.clone({
      setHeaders : {
        authorization : 'Bearer '+this.authService.getToken(),
      }
    });

    return next.handle(request);
  }
}