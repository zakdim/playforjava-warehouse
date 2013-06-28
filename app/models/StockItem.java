package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

/**
 * 
 */
@Entity 
public class StockItem extends Model {

    @Id @GeneratedValue
    public Long id;
    
    @ManyToOne
    public Warehouse warehouse;
    
    @ManyToOne
	public Product product;
    
	public Long quantity;
	
	/**
     * Generic query helper for entity Company with id Long
     */
    public static Finder<Long, StockItem> find() {
    	return new Finder<Long, StockItem>(Long.class, StockItem.class);
	}
    

	public String toString() {
		return String.format("StockItem %d = %dx product %s", 
				id, quantity, product == null ? null : product.id);
	}
}