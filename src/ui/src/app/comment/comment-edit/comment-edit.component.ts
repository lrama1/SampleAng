import { Component, OnInit } from '@angular/core';
import { CommentService } from '../comment.service';
import { Comment } from '../comment.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-comment-edit',
  templateUrl: './comment-edit.component.html',
  styleUrls: ['./comment-edit.component.css']
})
export class CommentEditComponent implements OnInit {
  comment: Comment = new Comment(
        '' 
        , ''       , ''       , ''       , ''       , ''       , ''     );

  constructor(private commentService: CommentService, private route: ActivatedRoute) { }

  ngOnInit() {
    // param name should match what you defined in appRoutes
    console.log('loading comment:' + this.route.snapshot.params['id']);
    this.commentService.getComment(this.route.snapshot.params['id']).subscribe(
      (response) => { this.comment = response.json(); },
      (error) => { console.log(error); }
    );
  }

  saveComment() {
    this.commentService.saveComment(this.comment).subscribe(
      (response) => { this.comment = response.json(); },
      (error) => { console.log(error); }
    );
  }

}
