package baldwin.dgte.persistence;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import baldwin.dgte.model.Article;

@Repository @Transactional
public class ArticleDaoHibernateImpl implements ArticleDao{
	static Logger log = Logger.getLogger(ArticleDaoHibernateImpl.class);
	
	@Autowired SessionFactory factory;
	
	@Override
	public Article newArticle(String ancestry, String title, String domain) {
		log.info("New article. Ancestry: [" + ancestry + "] Domain: [" + domain + "]");
		
		Article newArticle = new Article(ancestry, title, domain);
		factory.getCurrentSession().saveOrUpdate(newArticle);
		return newArticle;
	}
	
	
	@Override
	public Article getArticle(String domain) {
		return getArticle(domain, true);
	}
	@Override
	public Article getArticle(String domain, boolean findChildren) {
		Article main = (Article) factory.getCurrentSession().createQuery("from Article where domain = :domain")
				.setParameter("domain", domain)
				.uniqueResult();
		if(null != main && findChildren) findChildren(main);
		return main;
	}
	@SuppressWarnings("unchecked")
	private void findChildren(Article article) {
		List<Article> children = factory.getCurrentSession().createQuery("from Article where ancestry = :ancestry")
				.setParameter("ancestry", article.getAncestry() + "/" + article.getDomain())
				.list();
		if(null != children && children.size() > 0) {
			article.setChildren(children);
			for(Article child : children) {
				findChildren(child);
			}
		}
	}

	@Override
	public void update(Article article, String newDomain, String newTitle) {
		article.setDomain(newDomain);
		article.setTitle(newTitle);
		factory.getCurrentSession().update(article);
	}
}
