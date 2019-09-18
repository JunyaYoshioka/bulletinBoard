package com.example.demo.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Article;
import com.example.demo.domain.Comments;


@Repository
public class ArticleRepositry {
	
	
	@Autowired
    private NamedParameterJdbcTemplate template;
	
	
//	private static final String TABEL_ARTICLE = "articles";
	
	
	/**
	 * Article
	 */
	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs,i) -> {
		Article article = new Article();
		
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));		
		
		return article;
	};
	
	private static final RowMapper<Comments> COMMENTS_ROW_MAPPER = (rs,i) -> {
		Comments comments = new Comments();
		
		comments.setId(rs.getInt("id"));
		comments.setName(rs.getString("name"));
		comments.setContent(rs.getString("content"));
		comments.setArticleId(rs.getInt("article_id"));
		
		return comments;
	};
	
	
	/**
	 * 全部の記事を取得する
	 * 
	 * @return 全記事一覧
	 */
	public List<Article> findAll() {
		String sql = "SELECT * FROM articles;";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		return articleList;
	}
	
	
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
	
	

////articlesとcommentsのテーブル結合
//String sql = "SELECT * FROM articles LEFT OUTER JOIN comments ON articles.id  = comments.article_id;";

/**
* @param article 記事投稿
//*/
	public void insert(Article article) {
			SqlParameterSource param = new BeanPropertySqlParameterSource(article);
			String sql = "INSERT INTO articles(name, content) VALUES(:name, :content);";
			template.update(sql, param);
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
	 * 投稿削除
	 * @param id
	 */
//	public void deleteByArticlesID(Integer id) {
//		String deleteSpl = "DELETE FROM articles WHERE id = :id;";
//		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
//		template.update(deleteSpl, param);
//	}
	
	/**
	 * コメント削除
	 * @param id
	 */
	public void deleteByCommentsID(Integer id) {
		String deleteSpl = "DELETE FROM comments WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(deleteSpl, param);
	}
	
	/**
	 * 投稿とコメントを一括で削除
	 * @param id
	 */
	public void deleteByCommentIdAndArticlesID(Integer id) {
		String deleteSql = "SELECT * FROM articles LEFT OUTER JOIN comments ON articles.id  = comments.article_id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(deleteSql, param);
	}
}

