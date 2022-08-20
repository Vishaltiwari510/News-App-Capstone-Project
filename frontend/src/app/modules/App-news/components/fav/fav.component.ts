import { Component, OnInit } from '@angular/core';
import { NewsService} from '../../app-news.service'
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'news-fav',
  templateUrl: './fav.component.html',
  styleUrls: ['./fav.component.css']
})
export class FavComponent implements OnInit {
  newsList : Array<any>;
  favCheck=true;
  message!: string;
  
  constructor(private newsService: NewsService, private snackBar : MatSnackBar) {
    this.newsList = [];
 
   }

  ngOnInit() {
    this.newsService.getFavouriteNewsList().subscribe((data)=>{
      this.newsList.push(...data);
    },
    error =>{
     this.snackBar.open(error['error'], '', {
      duration : 2000
    });
   });

  }

}
