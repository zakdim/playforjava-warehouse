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
public class VerboseAction extends Action<Verbose> {

	@Override
	public Result call(Http.Context ctx) throws Throwable {
		Logger.info("verbose action for " + ctx);
		return delegate.call(ctx);
	}

}
