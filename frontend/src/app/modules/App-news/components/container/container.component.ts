import { Component, OnInit, Input} from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import {NewsService} from '../../app-news.service'

@Component({
  selector: 'news-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {
  @Input()
  newsList!: Array<any>;

  @Input()
  technewsDisplay!: Array<any>;

  @Input()
  favCheck!: boolean;

  
  

  
  constructor(private newsService: NewsService, private snackBar : MatSnackBar) {
   }


  ngOnInit() {

  }

  addToFavouriteList(news: any)
  {

    console.log(news);

    this.newsService.addToNews(news).subscribe(()=>{
      console.log(news.title);
      this.snackBar.open(news.title+'added to favlist', '', {
        duration : 2000
      });
    },error =>{
      this.snackBar.open(error['error'], '', {
       duration : 2000
     });
    });

  }


  deleteNews(news: { title: string; } ){
    this.newsService.deleteFromFavouriteList(news).subscribe((data)=>{
    console.log(data);
  
    this.snackBar.open(news.title+' deleted', '', {
      duration : 2000
    });
    },error =>{
      const message=JSON.stringify(error.error.message);
      this.snackBar.open(message, '', {
       duration : 2000
     });
    });
    for( var i=0;i<this.newsList.length;i++){
      if(this.newsList[i].title===news.title){
        this.newsList.splice(i,1);
      }
    }
    
    for( var i=0;i<this.technewsDisplay.length;i++){
      if(this.technewsDisplay[i].title===news.title){
        this.technewsDisplay.splice(i,1);
      }
    }
  }

}
