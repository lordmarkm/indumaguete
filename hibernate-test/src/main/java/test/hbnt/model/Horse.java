package test.hbnt.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity @Table(name="horse")
public class Horse {
	@Id @GeneratedValue @Column(name="horse_id")
	private long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="shoe_id")
	private HorseShoe horseShoe;
	
	public Horse() {
		
	}
	
	public Horse(HorseShoe horseShoe) {
		this.horseShoe = horseShoe;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public HorseShoe getHorseShoe() {
		return horseShoe;
	}

	public void setHorseShoe(HorseShoe horseShoe) {
		this.horseShoe = horseShoe;
	}
}
