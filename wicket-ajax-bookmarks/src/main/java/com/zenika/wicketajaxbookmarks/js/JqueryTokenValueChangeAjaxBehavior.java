package com.zenika.wicketajaxbookmarks.js;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * Jquery implementation of the {@link AbstractTokenValueChangeAjaxBehavior}.
 * Uses
 * 
 * @author vmontane
 * 
 */
public abstract class JqueryTokenValueChangeAjaxBehavior extends AbstractTokenValueChangeAjaxBehavior {

	private final boolean injectJquery;

	/**
	 * Contructor.
	 */
	public JqueryTokenValueChangeAjaxBehavior() {
		this(true);
	}

	/**
	 * Constructor.
	 * 
	 * @param injectJquery
	 *            set to <code>false</code> if Jquery is already present in your
	 *            application (and it should).
	 */
	public JqueryTokenValueChangeAjaxBehavior(boolean injectJquery) {
		super();
		this.injectJquery = injectJquery;
	}

	/**
	 * Adding jquery (if asked) to the header.
	 */
	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		if (injectJquery) {
			response.renderJavaScriptReference(new JavaScriptResourceReference(
					JqueryTokenValueChangeAjaxBehavior.class, "jquery-1.7.1.js"));
		}
		super.renderHead(component, response);
	}

	/**
	 * {@inheritDoc}
	 */
	protected String getHashTagListenerScript() {
		StringBuilder javascript = new StringBuilder("$(window).bind('hashchange', function() {");
		javascript.append(getCallbackScript());
		javascript.append("})");
		return javascript.toString();
	}

}
