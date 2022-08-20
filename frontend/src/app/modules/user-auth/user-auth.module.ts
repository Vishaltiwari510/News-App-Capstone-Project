import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LogInComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import {AngularMaterialModule} from '../common/angular-material.module'
import { RouterModule, Routes} from '@angular/router';
import {FormsModule} from '@angular/forms'
import {ReactiveFormsModule} from '@angular/forms';
import { AuthenticationService } from './user-auth.service';
import {HttpClientModule} from '@angular/common/http';

const authRouter: Routes = [
  {
    path:'',
    children: [
      {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full'
      },
      {
        path: 'register',
        component: RegisterComponent,
      },
      {
        path: 'login',
        component: LogInComponent,
      }
    ]
  }
];
@NgModule({
  declarations: [LogInComponent, RegisterComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(authRouter),
    AngularMaterialModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    
  ],
  providers:[AuthenticationService],
  exports: [
    RouterModule,
    RegisterComponent,
    LogInComponent,
    AngularMaterialModule
  ]
})
export class AuthenticationModule { }
