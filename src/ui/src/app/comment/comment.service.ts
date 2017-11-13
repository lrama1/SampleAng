import {Injectable, OnInit} from '@angular/core';
import { Comment } from './comment.model';
import {Http} from '@angular/http';

@Injectable()
export class CommentService implements OnInit{
  comments: Comment[] = [];

  constructor(private http: Http) {}

  ngOnInit() {
  }

  saveComment(commentToSave: Comment) {
    this.comments.push(commentToSave);
    return this.http.put('/CommentAng4/comment/' + commentToSave.commentId, commentToSave);
  }

  getComment (id: string) {
    return this.http.get('/CommentAng4/comment/' + id);
  }

  getComments (page: number, pageSize: number) {
    //return this.comments;
    return this.http.get('/CommentAng4/comments?page=' + page + '&per_page=' + pageSize);
  }
}
