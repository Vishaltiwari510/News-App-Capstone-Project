import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../user-auth/user-auth.service';
import { HTTP_OPTIONS } from 'src/app/header.config';

@Injectable({
  providedIn: 'root'
})
export class NewsService {
  api_key = '14bc0f7ff7ea47daaa0c9594eb84f120';
  springEndpoint : string;

  constructor(private http: HttpClient,private authservice :AuthenticationService) {
    this.springEndpoint = 'http://localhost:9001/api/news';
  }

  searchNews(query: string) {
    const url ='https://newsapi.org/v2/everything?' +
    'q=' + query +
    '&apiKey='+this.api_key;
    console.log(url);
    return this.http.get(url);
 }

 addToNews(news: any)
 {
    return this.http.post(this.springEndpoint, news, {
      headers: HTTP_OPTIONS.headers.set('Authorization',`Bearer ${this.authservice.getToken()}`)
 }); }

 getFavouriteNewsList():Observable<Array<any>>
 {
    return this.http.get<Array<any>>(this.springEndpoint, {
      headers: HTTP_OPTIONS.headers.set('Authorization',`Bearer ${this.authservice.getToken()}`)
 });
 }

 deleteFromFavouriteList(news: { title?: string; id?: any; })
 {
    return this.http.delete(this.springEndpoint + "/" + news.id,{
      headers: HTTP_OPTIONS.headers.set('Authorization',`Bearer ${this.authservice.getToken()}`)
 });
 }

 getTopNews()
 {
   const url ='https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=' +
   this.api_key;
   console.log(url);
   return this.http.get(url);
 }

 


}
