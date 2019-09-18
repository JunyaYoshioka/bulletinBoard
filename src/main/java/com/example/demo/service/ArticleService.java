package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Article;
import com.example.demo.domain.Comments;
import com.example.demo.repository.ArticleRepositry;
import com.example.demo.repository.CommentRepository;

@Service
public class ArticleService {
	@Autowired
	public ArticleRepositry articleRepositry;
	
	
	@Autowired
	public CommentRepository commentRepository;
	
	/**
	 * 全部の記事を取得します
	 * 
	 * @return 全記事
	 */
	public List<Article> showArticleList() {
		List<Article> articleList = articleRepositry.findAll();
		return articleList;
	}
	
	
	/**
	 * コメントを表示
	 * 
	 * @return　コメント
	 */
	public List<Comments> showCommentsList(Integer articleId) {
		List<Comments> comments = commentRepository.findAllComment(articleId);
		return comments;
	}
	
	/**
	 * @param 記事投稿
	 */
	public void insert(Article article) {
		articleRepositry.insert(article);
	}
	
	/**
	 * コメント投稿
	 * @param comments
	 */
	public void insert(Comments comments ) {
		commentRepository.insert(comments);
	}
	
	/**
	 * 投稿を削除した時のコメント削除
	 * @param articleId
	 */
	public void deleteComment(Integer id) {
		commentRepository.findByComments(id);
	}
	
	/**
	 * 投稿削除
	 * @param id
	 */
	public void deleteArticle(Integer id) {
		articleRepositry.deleteByArticles(id);
	}
	
	
	
	/**
	 * コメント削除
	 * @param id
	 */
	public void deleteComments(Integer id) {
		commentRepository.deleteByCommentsArticle(id);
		
	}
	
	/**
	 * 投稿とコメントの削除
	 * @param id
	 */
//	public void deleteByAll(Integer id) {
//		articleRepositry.deleteByArticlesID(id);
//		commentRepository.deleteByCommentsID(id);
//		
//	}
	
}
