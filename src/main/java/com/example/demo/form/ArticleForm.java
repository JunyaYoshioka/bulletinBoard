package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ArticleForm {

	
	/** 名前 */
	@NotBlank(message = "名前を入力してください")
	@Size(max = 50, message = "名前は50文字以下にしてください")
	private String name;
	/** 記事内容 */
	@NotBlank(message = "コメントを入力してください")
	private String content;
	
	@Override
	public String toString() {
		return "ArticleForm [name=" + name + ", content=" + content + ", getName()=" + getName() + ", getContent()="
				+ getContent() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
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
}
