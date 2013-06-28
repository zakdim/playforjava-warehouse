/**
 * 
 */
package controllers;

import play.Logger;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

/**
 * @author 1615871
 *
 */
public class CatchAction extends Action<Catch> {

	@Override
	public Result call(Http.Context ctx) {
		Logger.info("catch action for " + ctx);
		try {
			return delegate.call(ctx);
		} catch (Throwable e) {
			if (configuration.send()) 
				Logger.info("emailing exception in " + ctx);
			else 	
				Logger.info("logging exception in " + ctx);
		}
		return null;
	}

}
