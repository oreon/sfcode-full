package com.hrb.tservices.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class Faq {

	private Long id;

	private String text;

	private String link;

	private String title;

	private Integer avgRating;

	private FaqList faqList;

	private Long categoryId;

	private Integer views;

	private String answer;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setAvgRating(Integer avgRating) {
		this.avgRating = avgRating;
	}

	public Integer getAvgRating() {
		return avgRating;
	}

	public void setFaqList(FaqList faqList) {
		this.faqList = faqList;
	}

	public FaqList getFaqList() {
		return faqList;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getViews() {
		return views;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}

}
