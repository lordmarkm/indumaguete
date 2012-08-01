package baldwin.dgte.persistence;

import baldwin.dgte.model.Article;

public interface ArticleDao {
	/**
	 * @param ancestry - full ancestry path, up to homepage e.g. Baldwinternet/Deliveries/International
	 * @param title
	 * @param domain
	 * @return the persisted article data
	 */
	Article newArticle(String ancestry, String title, String domain);
	
	/**
	 * Get article, fetching children (findChildren = true)
	 * 
	 * @param domain
	 */
	Article getArticle(String domain);
	
	/**
	 * Get article, specify if children should be fetched or not
	 * 
	 * @param domain
	 * @param findChildren
	 */
	Article getArticle(String domain, boolean findChildren);
	
	/**
	 * Update an article with a new domain and title
	 * @param article
	 * @param newDomain
	 * @param newTitle
	 */
	void update(Article article, String newDomain, String newTitle);
}
