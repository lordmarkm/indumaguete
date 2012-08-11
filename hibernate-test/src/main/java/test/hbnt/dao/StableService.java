package test.hbnt.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.hbnt.model.Horse;

@Service
public class StableService {
	static Logger log = LoggerFactory.getLogger(StableService.class);
	
	@Autowired StableDao stableDao;
	
	public void save(Horse horse) {
		log.info("Saving {}", horse);
		stableDao.save(horse);
	}
	
	public void delete(long horseId) {
		log.info("Deleting {}", horseId);
		stableDao.delete(horseId);
	}

	public int countHorseShoes() {
		return stableDao.countHorseShoes().size();
	}

	public void delete(Horse horse) {
		stableDao.delete(horse);
	}
}
