package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.avaje.ebean.Ebean;

import play.data.validation.Constraints;
import play.data.validation.Constraints.Required;
import play.data.validation.Constraints.ValidateWith;
import play.db.ebean.Model;
import play.libs.F.Option;
import play.libs.F.Tuple;
import play.mvc.QueryStringBindable;

@Entity
public class Product extends Model implements QueryStringBindable<Product> {

	public static class EanValidator extends Constraints.Validator<String> {
		@Override
		public Tuple<String, Object[]> getErrorMessageKey() {
			return new Tuple<String, Object[]>("error.invalid.ean", new String[] {});
		}

		@Override
		public boolean isValid(final String val) {
			String pattern = "^[0-9]{13}$";
			Pattern regex = Pattern.compile(pattern);
						
			return val != null && regex.matcher(val).matches();
		}
	};
	
	@Id @GeneratedValue
	public Long id;
	
//	@ValidateWith(value=EanValidator.class)
	@Required
	public String ean;
	
	@Required
	public String name;
	
	public String description;
	
	@Lob
	public byte[] picture;
	
	@OneToMany(mappedBy="product")
	public List<StockItem> stockItems;
	
	@ManyToMany
	public List<Tag> tags;

	public static Model.Finder<Long, Product> find = new Model.Finder<>(Long.class, Product.class);
	
	public Product() { }

	@Override
	public Option<Product> bind(String key, Map<String, String[]> data) {
		return Option.Some(findById(Long.valueOf(data.get("id")[0])));
	}

	@Override
	public String javascriptUnbind() {
		return this.id.toString();
	}

	@Override
	public String unbind(String arg0) {
		return "id=" + this.id;
	}
	
	public String toString() {
		return String.format("%s - %s", ean, name);
	}

	public static List<Product> findAll() {
//		return new HashSet<Product>(products);
		return Product.find.all();
	}

//	public static Product findByEan(String ean) {
//		for (Product candidate : products) {
//			if (candidate.ean.equals(ean)) {
//				return candidate;
//			}
//		}
//		return null;
//	}

	public static Product findById(Long id) {
		Product product = Product.find.byId(id);
		return product;
	}
	
//	public static Set<Product> findByName(String term) {
//		final Set<Product> results = new HashSet<Product>();
//		for (Product candidate : products) {
//			if (candidate.name.toLowerCase().contains(term.toLowerCase())) {
//				results.add(candidate);
//			}
//		}
//		return results;
//	}

//	public static boolean remove(Product product) {
//		return products.remove(product);
//	}

//	@Override
//	public void save() {
//		products.remove(findByEan(this.ean));
//		products.add(this);
//	}
	
//	public static void add(Product product) {
//		products.remove(findByEan(product.ean));
//		products.add(product);
//	}
	
	public void saveOrUpdate() {
		if (id == null) {
			save();
		} else {
			update();
		}
		saveManyToManyAssociations("tags");
	}
}
