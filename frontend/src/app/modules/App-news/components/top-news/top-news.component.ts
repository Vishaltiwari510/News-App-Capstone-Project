import { Component, OnInit } from '@angular/core';
import { NewsService} from '../../app-news.service'
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
@Component({
  selector: 'news-top-news',
  templateUrl: './top-news.component.html',
  styleUrls: ['./top-news.component.css']
})
export class TopNewsComponent implements OnInit {
  newsList : Array<any>;

  constructor(private newsService: NewsService, private snackBar : MatSnackBar) {
    this.newsList = [];
   }

  ngOnInit() {
    this.newsService.getTopNews().subscribe((data:any)=>{
      this.newsList = data['articles'];
    
    },
    error =>{
     this.snackBar.open(error['error'], '', {
      duration : 2000
    });
   });
  }

}
