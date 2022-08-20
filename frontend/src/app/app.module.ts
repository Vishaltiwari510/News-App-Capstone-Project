import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { AuthenticationModule} from './modules/user-auth/user-auth.module'
import {NewsModule} from './modules/App-news/app-news.module'
import {AngularMaterialModule} from './modules/common/angular-material.module'

const appRoutes: Routes =[
  {
    path: '',
    redirectTo: '/news/top',
    pathMatch: 'full'
  }
]
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    NewsModule,
    AngularMaterialModule,
    AuthenticationModule,
    RouterModule.forRoot(appRoutes),

    
  ],
  exports: [RouterModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
