package test.hbnt;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.hbnt.dao.StableService;
import test.hbnt.model.Horse;
import test.hbnt.model.HorseShoe;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:hbnt-ctx.xml")
public class CascadeTest {
	static Logger log = LoggerFactory.getLogger(CascadeTest.class);
	
	@Autowired StableService service;
	
	@Test
	public void testService() {
		HorseShoe horseShoe = new HorseShoe();
		Horse horse = new Horse(horseShoe);
		
		service.save(horse);
		service.delete(horse);
		
		//horseshoes should also be deleted when horse is deleted.
		assertEquals(0, service.countHorseShoes());
		
		log.debug("Cascade test complete.");
	}
}
