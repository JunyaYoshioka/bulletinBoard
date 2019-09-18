package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Article;
import com.example.demo.domain.Comments;
import com.example.demo.form.ArticleForm;
import com.example.demo.form.CommentsFrom;
import com.example.demo.service.ArticleService;

@Controller
@RequestMapping("/ArticleController")
public class ArticleController {

	
	@Autowired
	public ArticleService articleService;
	
	/**
	 * @return ArticleForm
	 */
	@ModelAttribute
	public ArticleForm setUpInsertAdministratorForm() {
		return new ArticleForm();
	}
	
	@ModelAttribute
	public CommentsFrom setUpInsertCommentsFrom() {
		return new CommentsFrom();
	}
	

	
	/**
	 * 記事を投稿する
	 * @param form
	 * @return
	 */
	@RequestMapping("/insert")
	public String articleInsert(@Validated ArticleForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return showArticleList(model);
		}
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleService.insert(article);
		return "redirect:/ArticleController";
	}
	
	/**
	 * コメントを投稿する
	 * @param form
	 * @return
	 */
	@RequestMapping("/commentsInsert")
	public String commentsInsert(@Validated CommentsFrom form, BindingResult result,Model model) {
		if(result.hasErrors()) {
			return showArticleList(model);
		}
		
		Comments comments = new Comments();
		BeanUtils.copyProperties(form, comments);
		articleService.insert(comments);
		
		return "redirect:/ArticleController";
	}
	
	/**
	 * 投稿削除
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(Integer id, Model model) {
		articleService.deleteComment(id);
		articleService.deleteArticle(id);
		
		return"redirect:/ArticleController";
	}
	
	/**
	 * コメント1件を削除
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteComment")
	public String deleteComment(Integer id, Model model) {
		articleService.deleteComments(id);
		return"redirect:/ArticleController";
	}

	
	
	/**
	 * 記事一覧表示
	 * @param model
	 * @return
	 */
	@RequestMapping("")
	public String showArticleList(Model model) {
		//1,Articleテーブルから記事情報を全件取ってくる
		List<Article> articleList = articleService.showArticleList();
		
		//2,commentテーブルから、記事に関連するコメント情報を取ってくる
		//  取得したコメントをArticleオブジェクトにsetする
		List<Comments> commentList = null;
		for (Article article : articleList) {
			//記事情報のコメントを取得する処理
			commentList = articleService.showCommentsList(article.getId());
			
			//コメントをArticleオブジェクトにsetする処理
			article.setCommentlist(commentList);
			commentList = new ArrayList<>();
		}
		
		model.addAttribute("articleList", articleList);
		
		
		return "bulletinBoard";
	}
	
	
}
