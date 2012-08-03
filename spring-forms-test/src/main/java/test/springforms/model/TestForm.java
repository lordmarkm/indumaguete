package test.springforms.model;

import java.util.List;

public class TestForm {
	private String name;
	private String clinic;
	private List<TestObject> testObjects;
	
	public String toString() {
		return "Name: [" + name + "] Clinic: [" + clinic + "] TestObjects: " + testObjects;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClinic() {
		return clinic;
	}
	public void setClinic(String clinic) {
		this.clinic = clinic;
	}

	public List<TestObject> getTestObjects() {
		return testObjects;
	}

	public void setTestObjects(List<TestObject> testObjects) {
		this.testObjects = testObjects;
	}
}
