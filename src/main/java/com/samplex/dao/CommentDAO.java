package com.samplex.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.beans.factory.annotation.Autowired;
//import the domain
import com.samplex.web.domain.Comment;
import com.samplex.common.ListWrapper;
import com.samplex.dao.CommentDAO;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Repository
public class CommentDAO {

	@Autowired
	private MongoTemplate mongoTemplate;

	//private List<Comment> allComment = new ArrayList<Comment>();
	private Map<String, Comment> allData = new LinkedHashMap<String, Comment>();

	@PostConstruct
	public void init() {

	}

	public Comment getComment(String id) {
		Criteria criteria = new Criteria("commentId").is(id);
		Query query = new Query(criteria);
		return mongoTemplate.findOne(query, Comment.class);
	}

	public void saveNewComment(Comment comment) {
		mongoTemplate.save(comment);
	}

	public void saveComment(Comment comment) {
		mongoTemplate.save(comment);

		Criteria criteria = new Criteria("commentId").is(comment.getCommentId());
		Query query = new Query(criteria);

		Update update = new Update();
		update.set("commentId", comment.getCommentId());
		update.set("commentAction", comment.getCommentAction());
		update.set("commentResult", comment.getCommentResult());
		update.set("commentDateTime", comment.getCommentDateTime());
		update.set("commentLastName", comment.getCommentLastName());
		update.set("commentTextHeader", comment.getCommentTextHeader());
		update.set("commentTextDetail", comment.getCommentTextDetail());

		mongoTemplate.updateFirst(query, update, Comment.class);
	}

	public ListWrapper<Comment> getComments(int page, int pageSize, String sortByAttributeName, String sortDirection) {

		Criteria criteria = new Criteria();
		Query query = new Query(criteria).skip((page - 1) * pageSize).limit(pageSize);
		Long totalRows = mongoTemplate.count(query, Comment.class);
		List<Comment> partialPage = mongoTemplate.find(query, Comment.class);
		int totalPages = roundUp(totalRows.intValue(), pageSize);

		ListWrapper<Comment> listWrapper = new ListWrapper<Comment>();
		listWrapper.setRows(partialPage);
		listWrapper.setTotalRecords(totalRows.intValue());
		listWrapper.setLastPage(totalPages);
		return listWrapper;
	}

	private int roundUp(int num, int divisor) {
		return (num + divisor - 1) / divisor;
	}
}
