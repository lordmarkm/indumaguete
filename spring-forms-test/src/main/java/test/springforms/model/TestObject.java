package test.springforms.model;

public class TestObject {
	private String weapon;
	private String style;
	
	public String toString() {
		return "Weapon: [" + weapon + "] Style: [" + style + "]";
	}
	
	public String getWeapon() {
		return weapon;
	}
	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
}
