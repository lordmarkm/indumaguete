package test.hbnt.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import test.hbnt.model.Horse;

@Repository @Transactional
public class StableDaoImpl implements StableDao {

	@Autowired SessionFactory sessionFactory;
	
	public void save(Horse horse) {
		sessionFactory.getCurrentSession()
			.saveOrUpdate(horse);
	}

	public void delete(long horseId) {
		sessionFactory.getCurrentSession()
			.createQuery("delete from Horse where horse_id = :horseId")
			.setLong("horseId", horseId)
			.executeUpdate();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List countHorseShoes() {
		return sessionFactory.getCurrentSession()
			.createQuery("from HorseShoe")
			.list();
	}

	@Override
	public void delete(Horse horse) {
		sessionFactory.getCurrentSession()
				.delete(horse);
	}
}
