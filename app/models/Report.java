package models;

import play.Logger;

public class Report {
	public String name;
	
	public Report(String name) {
		this.name = name;
	}
	
	public void execute() {
		Logger.info(String.format("starting intensive %s report", name));
		try {
			Thread.sleep(5000);
		} catch (Exception e) {}
		Logger.info(String.format("done with intensive %s report", name));
	}
	
	public String toString() {
		return name;
	}
}
