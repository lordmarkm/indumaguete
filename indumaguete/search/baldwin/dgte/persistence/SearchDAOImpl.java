package baldwin.dgte.persistence;

import java.util.List;

import javax.inject.Named;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import baldwin.dgte.model.Item;

@Named @Transactional
public class SearchDAOImpl implements SearchDAO {
	static Logger log = Logger.getLogger(SearchDAOImpl.class);
	
	@Autowired private SessionFactory factory;
	
	@SuppressWarnings("unchecked")
	@Override 
	public List<Item>[] search(String term) {
		List<Item> businessNameMatches = factory.getCurrentSession().createCriteria(Item.class)
				.add(Restrictions.like("businessName", "%" + term + "%")).list();

		List<Item> natureOfBusinessMatches = factory.getCurrentSession().createCriteria(Item.class)
				.add(Restrictions.like("natureOfBusiness", "%" + term + "%")).list();

		List<Item> descriptionMatches = factory.getCurrentSession().createCriteria(Item.class)
				.add(Restrictions.like("shortDescription", "%" + term + "%")).list();

		return new List[] {businessNameMatches, natureOfBusinessMatches, descriptionMatches};
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Item> search(String term, String subcategory) {
		List<Item> results;
		if("All".equals(subcategory)) {
			log.info("Searching all items for " + term);
			results = factory.getCurrentSession()
					.createQuery("from Item as item where item.name like :term order by item.modified desc")
					.setString("term", "%" + term + "%")
					.list();
		} else if(subcategory.startsWith("Any")) {
			String category = subcategory.replaceFirst("Any", "").trim();
			log.info("Searching category: " + category + ", for: " + term);
			results = factory.getCurrentSession().createQuery("from Item as item where category = :category and item.name like :term order by item.modified desc")
					.setString("category", category)
					.setString("term", "%" + term + "%")
					.list();
		} else {
			log.info("Searching subcategory: " + subcategory + " for " + term);
			results = factory.getCurrentSession()
					.createQuery("from Item as item where subcategory = :subcategory and item.name like :term order by item.modified desc")
					.setString("subcategory", subcategory)
					.setString("term", "%" + term + "%")
					.list();
		}
		log.info(results.size() + " results.");
		return results;
	}
}
