package kr.co.itcen.jblog.vo;

public class CategoryVo {
	private Long no;
	private String name;
	private String contents;
	private String regDate;
	private String id;
	private int postCount;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", contents=" + contents + ", regDate=" + regDate + ", id="
				+ id + ", postCount=" + postCount + "]";
	}

	

}