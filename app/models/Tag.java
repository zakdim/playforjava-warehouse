/**
 * 
 */
package models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
 * @author 1615871
 *
 */
@Entity
public class Tag extends Model {

	@Id
	public Long id;
	
	@Constraints.Required
	public String name;
	
	@ManyToMany(mappedBy="tags")
	public List<Product> products;
	
	public static Model.Finder<Long, Tag> find = new Model.Finder<>(Long.class, Tag.class);
	
	private static Set<Tag> tags;
	static {
		tags = new HashSet<Tag>();
		tags.add(new Tag(1L, "Steel"));
		tags.add(new Tag(2L, "Plastic"));
		tags.add(new Tag(3L, "Metal"));
	}
	
	public Tag() {
	}
	
	public Tag(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static List<Tag> findAll() {
//		return new HashSet<Tag>(tags);
		return Tag.find.all();
	}
	
	public static Tag findById(Long id) {
//		for (Tag candidate : tags) {
//			if (candidate.id == id) {
//				return candidate;
//			}
//		}
//		return null;
		Tag tag = Tag.find.byId(id);
		return tag;
	}
	
	public String toString() {
		return id != null ? id.toString() : "-1";
	}
}
