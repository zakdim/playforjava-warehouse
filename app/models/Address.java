package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.db.ebean.Model;

@Entity
public class Address extends Model {
	
	@Id
	public Long id;
	
	@OneToOne(mappedBy="address")
	public Warehouse warehouse;
	
	public String street;
	public String number;
	public String postalCode;
	public String city;
	public String country;	
}
