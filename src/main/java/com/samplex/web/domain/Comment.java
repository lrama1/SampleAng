package com.samplex.web.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class Comment {
	@Id
	private String commentId;
	private String commentAction;
	private String commentResult;
	private String commentDateTime;
	private String commentLastName;
	private String commentTextHeader;
	private String commentTextDetail;

	public String getCommentId() {
		return commentId;
	}

	public String getCommentAction() {
		return commentAction;
	}

	public String getCommentResult() {
		return commentResult;
	}

	public String getCommentDateTime() {
		return commentDateTime;
	}

	public String getCommentLastName() {
		return commentLastName;
	}

	public String getCommentTextHeader() {
		return commentTextHeader;
	}

	public String getCommentTextDetail() {
		return commentTextDetail;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public void setCommentAction(String commentAction) {
		this.commentAction = commentAction;
	}

	public void setCommentResult(String commentResult) {
		this.commentResult = commentResult;
	}

	public void setCommentDateTime(String commentDateTime) {
		this.commentDateTime = commentDateTime;
	}

	public void setCommentLastName(String commentLastName) {
		this.commentLastName = commentLastName;
	}

	public void setCommentTextHeader(String commentTextHeader) {
		this.commentTextHeader = commentTextHeader;
	}

	public void setCommentTextDetail(String commentTextDetail) {
		this.commentTextDetail = commentTextDetail;
	}

	public void populateWithSample() {
		int appender = getNextNumber();
		commentId = "Sample Value commentId " + appender;
		commentAction = "Sample Value commentAction " + appender;
		commentResult = "Sample Value commentResult " + appender;
		commentDateTime = "Sample Value commentDateTime " + appender;
		commentLastName = "Sample Value commentLastName " + appender;
		commentTextHeader = "Sample Value commentTextHeader " + appender;
		commentTextDetail = "Sample Value commentTextDetail " + appender;
	}

	static int sampleCounter = 0;

	private static int getNextNumber() {
		sampleCounter++;
		return sampleCounter;
	}

	public String toString() {
		return "commentId = " + commentId + ", commentAction = " + commentAction + ", commentResult = " + commentResult
				+ ", commentDateTime = " + commentDateTime + ", commentLastName = " + commentLastName
				+ ", commentTextHeader = " + commentTextHeader + ", commentTextDetail = " + commentTextDetail;
	}
}
