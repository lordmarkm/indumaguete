package baldwin.dgte.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity @Table(name="indumaguete_articles", schema="njfp")
public class Article {
	@Id @GeneratedValue long articleIndex;
	
	@Column(unique = true, nullable = false) 
	String domain;
	
	@Column String title;
	@Column String ancestry; //includes domain format: /Baldwinternet/deliveries/international/ <-- Each ancestory must be bookended by /
	
	@Transient List<Article> children; //generated on demand. Do not persist.
	
	public Article(){}
	
	public Article(String ancestry, String title, String domain) {
		this.ancestry = ancestry;
		this.title = title;
		this.domain = domain;
	}
	
	public long getArticleIndex() {
		return articleIndex;
	}
	public void setArticleIndex(long articleIndex) {
		this.articleIndex = articleIndex;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAncestry() {
		return ancestry;
	}
	public void setAncestry(String parentDomain) {
		this.ancestry = parentDomain;
	}
	public List<Article> getChildren() {
		return children;
	}
	public void setChildren(List<Article> children) {
		this.children = children;
	}
}
