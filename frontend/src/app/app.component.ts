import { Component,OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import {AuthenticationService} from './modules/user-auth/user-auth.service'
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  ngOnInit(){

    
  }
  title = 'Newsapp';
 

  constructor(public router: Router ,public auth: AuthenticationService)
  {
   
  }

  logout()
  {
    this.auth.deleteToken();
    this.router.navigate(['/login'])
 
  }
}
