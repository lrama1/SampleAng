import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { APP_BASE_HREF} from '@angular/common';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { CommentListComponent } from './comment/comment-list/comment-list.component';
import { CommentEditComponent } from './comment/comment-edit/comment-edit.component';
import {CommentService} from './comment/comment.service';
import { HomeComponent } from './home/home.component';

const appRoutes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'comments', component: CommentListComponent},
  { path: 'comment/:id', component: CommentEditComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    CommentListComponent,
    CommentEditComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    HttpModule
  ],
  providers: [{provide: APP_BASE_HREF, useValue: '/CommentAng4'}, CommentService],
  bootstrap: [AppComponent]
})
export class AppModule { }
