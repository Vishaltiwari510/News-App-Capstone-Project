import { Component, OnInit } from '@angular/core';
import {NewsService} from '../../app-news.service'
@Component({
  selector: 'news-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  newsList!: Array<any>;
  constructor(private newsService: NewsService) { 

    
  }

  ngOnInit() {
    
  }

  onEnter(searchKey: string) {
    
    this.newsService.searchNews(searchKey).subscribe((data : any) => {
      this.newsList = data['articles'];
    
    });
  }

}
