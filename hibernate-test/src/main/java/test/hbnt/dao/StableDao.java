package test.hbnt.dao;

import java.util.List;

import test.hbnt.model.Horse;

public interface StableDao {
	public void save(Horse horse);
	public void delete(long horseId);
	public List countHorseShoes();
	public void delete(Horse horse);
}
