import { Observable } from 'rxjs';
import { AuthenticationService } from './../services/authentication.service';
import { Injectable } from "@angular/core";
import { HttpInterceptor, HttpEvent, HttpRequest, HttpHandler } from '@angular/common/http';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

    constructor(private service: AuthenticationService){}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
        if(this.service.tokenIsPresent()){
            request = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${this.service.getToken()}`
                }
            });
        }
        return next.handle(request);
    }
}