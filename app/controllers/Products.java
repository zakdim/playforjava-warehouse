/**
 * 
 */
package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.avaje.ebean.Ebean;

import models.Product;
import models.StockItem;
import models.Tag;
import play.Logger;
import play.api.templates.Html;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import views.html.products.details;
import views.html.products.list;

/**
 * @author 1615871
 *
 */
public class Products extends Controller {
	
	private static final Form<Product> productForm = 
			Form.form(Product.class);
	
	public static Result index() {
		return redirect(routes.Products.list());
	}
	
	/**
	 * List all products.
	 * @return
	 */
	@Catch
	public static Result list() {
//		RequestBody requestBody = request().body();
//		Set<Product> products = Product.findAll();
		List<Product> allProducts = Product.find.all();
//		Html renderedTemplate = list.render(allProducts, requestBody.toString());
		Html renderedTemplate = list.render(allProducts);

		
		return ok(renderedTemplate);
	}
	
	/**
	 * Show blank product form.
	 * @return
	 */
	public static Result newProduct() {
		return ok(details.render(productForm));
	}
	
//	public static Result details(Long ean) {
//		final Product product = Product.findByEan(ean);
//		if (product == null) {
//			return notFound(String.format("Product %s does not exist.", ean));
//		}
//		
//		Form<Product> filledForm = productForm.fill(product);
//		return ok(details.render(filledForm));
//	}

	public static Result details(Product product) {
		Form<models.Product> filledForm = Form.form(models.Product.class);
		filledForm = filledForm.fill(product);
		return ok(details.render(filledForm));
	}
	
	
	/**
	 * Save a product.
	 * @return
	 */
	public static Result save() {
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart picture = body.getFile("picture");
		Form<Product> boundForm = Form.form(Product.class).bindFromRequest();

		if (boundForm.hasErrors()) {
			flash("error", "Please correct the form below");
			return badRequest(details.render(boundForm));
		}
		Product newProduct = boundForm.get();		
		
		if (picture != null) {
			String fileName = picture.getFilename();
			String contentType = picture.getContentType();
			Logger.info(String.format("uploaded picture: %s, %s", contentType, fileName));
			File file = picture.getFile();
			try {
				newProduct.picture = IOUtils.toByteArray(new FileInputStream(file));
			} catch (Exception e) {
				Logger.info("error retrieving uploaded picture", e);
			}
		} else {
			Logger.info("no picture uploaded");
		}

		List<Tag> tags = new ArrayList<Tag>();
		if (newProduct.tags != null) {
			for (Tag tag : newProduct.tags) {
				// Load our tag
				if (tag.id != null && tag.id != -1) {
					tags.add(Tag.findById(tag.id));
				}
			}
		}
		newProduct.tags = tags;
//		Product.add(newProduct);
		
		// Create StockItem with 0 quantity.
		StockItem item = new StockItem();
		item.quantity = 0L;
		item.product = newProduct;
//		item.warehouse = warehouse;
//		warehouse.stock.add(item);
				
		newProduct.saveOrUpdate();
//		item.save();
		
		flash("success", String.format("Successfully added product %s", newProduct));
		
		return redirect(routes.Products.list());
	}

	public static Result picture(String id) {
		Product product = Product.findById(Long.valueOf(id));
		return ok(product.picture);
	}
}

