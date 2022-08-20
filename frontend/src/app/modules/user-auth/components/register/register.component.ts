import { Component, OnInit } from '@angular/core';
import {User} from '../../user'
import {AuthenticationService } from '../../user-auth.service'
import { Router } from '@angular/router';
import { FormGroup,Validators,  FormControl,FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'user-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  newUser: User;
  registerForm: FormGroup;
  constructor(private authService: AuthenticationService,
    private router: Router,private snackbar: MatSnackBar,private formBuilder: FormBuilder) { 
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      "firstName":new FormControl('',[ Validators.required]),
     "lastName":new FormControl('',[ Validators.required]),
      "userId":new FormControl('',[ Validators.required]),
      "password":new FormControl('',[ Validators.required]),
    
      })
  }



  registerUser() {
    if (this.registerForm.invalid) {
      return;
    };
    this.newUser = new User(this.registerForm.get('userId').value,this.registerForm.get('password').value,this.registerForm.get('firstName').value, this.registerForm.get('lastName').value);
    console.log("Register User data:", this.newUser);
    this.authService.registerUser(this.newUser).subscribe(data => {
      console.log("User registered", data);
      this.router.navigate(['/login']);
    },error =>{
      this.snackbar.open(error.error, '', {
       duration : 2000
     });
    });
    
  
}
}
