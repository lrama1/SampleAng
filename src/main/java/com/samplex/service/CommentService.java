package com.samplex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import the domain
import com.samplex.web.domain.Comment;
import com.samplex.common.ListWrapper;
import com.samplex.dao.CommentDAO;

@Service
public class CommentService {

	@Autowired
	CommentDAO commentDAO;

	public ListWrapper<Comment> getComments(int pageNumber, int pageSize, String sortByAttribute,
			String sortDirection) {
		return commentDAO.getComments(pageNumber, pageSize, sortByAttribute, sortDirection);
	}

	public Comment getComment(String id) {
		return commentDAO.getComment(id);
	}

	public void saveNewComment(Comment comment) {
		commentDAO.saveNewComment(comment);
	}

	public void saveComment(Comment comment) {
		commentDAO.saveComment(comment);
	}
}
