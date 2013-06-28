/**
 * 
 */
package controllers;

import java.util.List;

import models.StockItem;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.stockitems;

/**
 * @author 1615871
 *
 */
public class StockItems extends Controller {

	public static Result index() {
//		List<StockItem> items = StockItem.find().findList();
		List<StockItem> items = StockItem.find()
				.where()
				.ge("quantity", 190)
				.orderBy("quantity desc")
				.setMaxRows(2)
				.findList();
		
		return ok(stockitems.render(items));
	}
}
