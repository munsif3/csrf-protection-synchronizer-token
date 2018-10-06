package com.munsif.ssd.csrfsynchronizer.model;

public class Blog {

	private String postName;
	private String postBody;
	private String token;

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getPostBody() {
		return postBody;
	}

	public void setPostBody(String postBody) {
		this.postBody = postBody;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Blog [postName=" + postName + ", postBody=" + postBody + ", token=" + token + "]";
	}
	
	

}
