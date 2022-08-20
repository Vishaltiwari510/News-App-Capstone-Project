import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InterceptorService } from './interceptor.service';
import { SearchComponent } from './components/search/search.component';
import { ContainerComponent } from './components/container/container.component';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { Routes, RouterModule } from '@angular/router';
import { AngularMaterialModule } from '../common/angular-material.module';
import { FavComponent } from './components/fav/fav.component';
import { NewsService} from './app-news.service'
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AuthGuardService } from 'src/app/auth-guard.service';
import { TopNewsComponent } from './components/top-news/top-news.component';


const newsRoutes: Routes = [
  {
      path : 'news',
      children : [
     
          {
              path : 'search',
              canActivate : [AuthGuardService],
              component : SearchComponent
          },
          {
            path : 'favourites',
            canActivate : [AuthGuardService],
            component : FavComponent
          },
          {
            path : 'top',
            canActivate : [AuthGuardService],
            component : TopNewsComponent
        },
       
      ]
  }
];
@NgModule({
  declarations: [SearchComponent, ContainerComponent, ThumbnailComponent, FavComponent, TopNewsComponent ],
  imports: [
    RouterModule.forChild(newsRoutes),
    AngularMaterialModule,
    HttpClientModule
  ],
  providers:[
    NewsService, {
      provide : HTTP_INTERCEPTORS,
      useClass :  InterceptorService,
      multi : true
    }
  ],
  exports :[
    SearchComponent, ContainerComponent, ThumbnailComponent
  ]
})
export class NewsModule { }
