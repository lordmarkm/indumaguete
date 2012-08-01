package baldwin.dgte.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baldwin.dgte.model.Article;
import baldwin.dgte.model.Item;
import baldwin.dgte.persistence.ArticleDao;
import baldwin.dgte.persistence.DgteDao;
import baldwin.dgte.utils.DgteUtil.Filter;

@Service
public class ReadOnlyService {
	static Logger log = Logger.getLogger(ReadOnlyService.class);

	@Autowired private ArticleDao articleDb;
	@Autowired private DgteDao db;

	public Article getArticle(String domain) {
		return articleDb.getArticle(domain);
	}
	
	public Item getItem(String domain) {
		return db.getItem(domain);
	}
	
	public List<Item> getAllItems(Filter filter, int firstResult, int maxResult) {
		return db.getAllItems(filter, firstResult, maxResult);
	}
}
