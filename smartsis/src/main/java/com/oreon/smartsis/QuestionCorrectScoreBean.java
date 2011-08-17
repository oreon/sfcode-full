package com.oreon.smartsis;

import java.math.BigInteger;

public class QuestionCorrectScoreBean {
	
	private String text;
	private BigInteger score;
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public QuestionCorrectScoreBean(String text, BigInteger score) {
		super();
		this.text = text;
		this.score = score;
	}
	public void setScore(BigInteger score) {
		this.score = score;
	}
	public BigInteger getScore() {
		return score;
	}

}
