package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentsFrom {
	
	/** コメント者の名前 */
	@NotBlank(message = "名前を入力してください")
	@Size(max = 50, message = "名前は50文字以下にしてください")
	private String name;
	/** コメント */
	@NotBlank(message = "コメントを入力してください")
	private String content;
	/** コメントID */
	private Integer articleId;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	@Override
	public String toString() {
		return "CommentsFrom [name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}
	
	
	
	
	
}
