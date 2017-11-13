package com.samplex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.security.Principal;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//import the domain
import com.samplex.web.domain.Comment;
import com.samplex.service.CommentService;
import com.samplex.security.SampleUserDetails;
import com.samplex.common.ListWrapper;
import com.samplex.common.NameValuePair;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.ArrayList;

@RestController
public class CommentController {

	@Autowired
	CommentService commentService;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	@PreAuthorize("@sampleUserDetailsService.isAuthorizedToAccessData(#id)")
	@RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
	public Comment getComment(@PathVariable("id") String id, Principal principal) {
		Authentication authenticationToken = (Authentication) principal;
		SampleUserDetails user = (SampleUserDetails) authenticationToken.getPrincipal();
		Comment comment = commentService.getComment(id);
		if (comment == null)
			return new Comment();
		else
			return comment;
	}

	@PreAuthorize("@sampleUserDetailsService.isAuthorizedToAccessData(#comment)")
	@RequestMapping(value = "/comment", headers = { "accept=application/json" }, method = RequestMethod.POST)
	public Comment saveNewComment(@RequestBody Comment comment) {
		commentService.saveNewComment(comment);
		return comment;
	}

	@PreAuthorize("@sampleUserDetailsService.isAuthorizedToAccessData(#comment)")
	@RequestMapping(value = "/comment/{id}", headers = { "accept=application/json" }, method = RequestMethod.PUT)
	public Comment updateComment(@RequestBody Comment comment) {
		commentService.saveComment(comment);
		return comment;
	}

	@RequestMapping("/comments")
	public ListWrapper<Comment> getAllComments(@RequestParam("page") int pageNumber,
			@RequestParam("per_page") int pageSize,
			@RequestParam(value = "sort_by", required = false) String sortByAttributeName,
			@RequestParam(value = "order", required = false) String sortDirection) {
		return commentService.getComments(pageNumber, pageSize, sortByAttributeName, sortDirection);

	}

	//=============
}
