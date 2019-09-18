package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Comments;

@Repository
public class CommentRepository {

	@Autowired
    private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Comments> COMMENTS_ROW_MAPPER = (rs,i) -> {
		Comments comments = new Comments();
		
		comments.setId(rs.getInt("id"));
		comments.setName(rs.getString("name"));
		comments.setContent(rs.getString("content"));
		comments.setArticleId(rs.getInt("article_id"));
		
		return comments;
	};
	
	/**
	 * コメント一覧
	 * 
	 * @return コメント一覧
	 */
	public List<Comments> findByComment(Integer articleId){
		String sql = "SELECT * FROM comments WHERE article_id = :articleId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		List<Comments> commentsList = template.query(sql, param, COMMENTS_ROW_MAPPER);
		return commentsList;
	}
	
	
	/**
	 * @param コメント投稿
	 */
	public void insert(Comments comments) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(comments);
		String sql = "INSERT INTO comments(name, content, article_id) VALUES(:name, :content, :articleId);";
		template.update(sql, param);
	}
	
	/**
	 * コメント削除
	 * @param id
	 */
	public void deleteByCommentsID(Integer id) {
		String deleteSpl = "DELETE FROM comments WHERE article_id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(deleteSpl, param);
	}
	
//	/**
//	 * コメント削除
//	 * @param articleId
//	 */
//	public void deleteByCommentsArticle(Integer id) {
//		String deleteSpl = "DELETE FROM comments WHERE article_id = :articleId;";
//		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
//		template.update(deleteSpl, param);
//	}
	
}
