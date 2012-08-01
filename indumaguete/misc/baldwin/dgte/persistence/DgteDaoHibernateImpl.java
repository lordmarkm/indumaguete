package baldwin.dgte.persistence;

import java.util.List;

import javax.inject.Named;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import baldwin.dgte.model.Article;
import baldwin.dgte.model.Item;
import baldwin.dgte.utils.DgteUtil;
import baldwin.dgte.utils.DgteUtil.Filter;

@Named @Transactional
public class DgteDaoHibernateImpl implements DgteDao {
	static Logger log = Logger.getLogger(DgteDaoHibernateImpl.class);

	@Autowired private SessionFactory factory;
	@Autowired DgteUtil dgteUtil;

	@Override 
	public Item newItem(Item item) {
		factory.getCurrentSession().saveOrUpdate(item);
		return item;
	}

	@Override
	public Item getItem(String domain) {
		return (Item) factory.getCurrentSession().createQuery("from Item where domain = :domain")
				.setParameter("domain", domain).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getAllItems(Filter filter, int firstResult, int maxResult) {
		switch(filter) {
		case popular:
			return factory.getCurrentSession()
					.createCriteria(Item.class)
					.addOrder(Order.desc("popularity"))
					.setFirstResult(firstResult).setMaxResults(maxResult)
					.list();
		case recent:
			return factory.getCurrentSession()
					.createCriteria(Item.class)
					.addOrder(Order.desc("modified"))
					.setFirstResult(firstResult).setMaxResults(maxResult)
					.list();
		default: //default to alphabetical
			return factory.getCurrentSession()
					.createCriteria(Item.class)
					.addOrder(Order.desc("name"))
					.setFirstResult(firstResult).setMaxResults(maxResult)
					.list();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findByProperty(String property, String value) {
		return factory.getCurrentSession().createQuery("from Item where :property = :value")
				.setParameter("property", property)
				.setParameter("value", value)
				.list();
	}

	@Override
	public void updateItem(Item item) {
		factory.getCurrentSession().update(item);
	}

	/**
	 * Don't use PropertyUtils.copyProperties(..) as this will copy itemIndex and passwordHash and cause weird things to happen
	 * Should skip passwordhash and itemIndex
	 */
	@Override
	public void updatePrimaryDetails(Item item, Item newProperties) throws Exception {
		if(!item.getDomain().equals(newProperties.getDomain())) {
			updateAuxilliaryTables(item.getDomain(), newProperties.getDomain());
		}
		item.setDomain(newProperties.getDomain());
		item.setAddress(newProperties.getAddress());
		item.setCellphone(newProperties.getCellphone());
		item.setCellphoneVisible(newProperties.isCellphoneVisible());
		item.setEmail(newProperties.getEmail());
		item.setEmailVisible(newProperties.isEmailVisible());
		item.setImageUrl(newProperties.getImageUrl());
		item.setLandline(newProperties.getLandline());
		item.setLandlineVisible(newProperties.isLandlineVisible());
		item.setName(newProperties.getName());
		item.setSubcategory(newProperties.getSubcategory());
		item.setCategory(dgteUtil.determineCategory(newProperties.getSubcategory()));
		item.setPassword(newProperties.getPassword());
		item.setShortDescription(newProperties.getShortDescription());
		updateItem(item);
	}

	private void updateAuxilliaryTables(String oldParentDomain, String newParentDomain) {
		//Articles
		@SuppressWarnings("unchecked")
		List<Article> articles = factory.getCurrentSession().createQuery("from Article where ancestry like '%" + oldParentDomain + "%'")
		.list();

		for(Article article : articles) {
			String ancestry = article.getAncestry();
			String newAncestry = ancestry.replace("/" + oldParentDomain + "/", "/" + newParentDomain + "/");
			article.setAncestry(newAncestry);
			factory.getCurrentSession().update(article);
		}
	}

	@Override
	public void deleteItem(Item item) {
		factory.getCurrentSession().delete(item);
	}
}
