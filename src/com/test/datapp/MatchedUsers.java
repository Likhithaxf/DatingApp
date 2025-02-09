package com.test.datapp;

import java.util.List;

public class MatchedUsers {
	
	String user1_id;
	String user2_id;
	String name1;
	String name2;
	double score;
	
	
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}

	public String getUser1_id() {
		return user1_id;
	}
	public void setUser1_id(String user1_id) {
		this.user1_id = user1_id;
	}
	public String getUser2_id() {
		return user2_id;
	}
	public void setUser2_id(String user2_id) {
		this.user2_id = user2_id;
	}
	
	@Override
	public String toString() {
		return name1 + " & " + name2 + " - "+score;
	}

}

