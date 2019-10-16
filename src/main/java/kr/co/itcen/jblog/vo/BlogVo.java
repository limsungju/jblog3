package kr.co.itcen.jblog.vo;

public class BlogVo {
	private String id;
	private String title;
	private String logo;
	
	public void DefaultBlogSetting(String id)
	{
		this.id = id;
		this.title = id + "님의 블로그";
		this.logo = "/assets/images/spring-logo.jpg";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", title=" + title + ", logo=" + logo + "]";
	}
	
	
}
