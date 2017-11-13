import { Component, OnInit } from '@angular/core';
import {CommentService} from "../comment.service";
import {Comment} from "../comment.model";

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.css']
})
export class CommentListComponent implements OnInit {

  listOfComments: Comment[];
  currentPage = 1;
  pageSize = 5;
  lastPage = 1;
  
  constructor(private commentService: CommentService) { }

  ngOnInit() {
    console.log('invoking Comment Service');
    //this.listOfComments = this.commentService.getAllComments();
    this.commentService.getComments(this.currentPage, this.pageSize).subscribe(
      (response) => {
        this.listOfComments = response.json().rows;
        this.lastPage = response.json().lastPage;
        console.log('start of results..');
        console.log(response.json().rows);
        console.log('end of results.');
        },
      (error) => { console.log(error); }
    );
  }

  nextPage() {
    this.currentPage++;
    console.log('invoking Comment Service');
    //this.listOfComments = this.commentService.getAllComments();
    this.commentService.getComments(this.currentPage, this.pageSize).subscribe(
      (response) => {
        this.listOfComments = response.json().rows;
        this.lastPage = response.json().lastPage;
        console.log('start of results..');
        console.log(response.json().rows);
        console.log('end of results.');
        },
      (error) => { console.log(error); }
    );
  }
  
  previousPage() {
    this.currentPage--;
    console.log('invoking Comment Service');
    //this.listOfComments = this.commentService.getAllComments();
    this.commentService.getComments(this.currentPage, this.pageSize).subscribe(
      (response) => {
        this.listOfComments = response.json().rows;
        this.lastPage = response.json().lastPage;
        console.log('start of results..');
        console.log(response.json().rows);
        console.log('end of results.');
        },
      (error) => { console.log(error); }
    );
  }
  
}
