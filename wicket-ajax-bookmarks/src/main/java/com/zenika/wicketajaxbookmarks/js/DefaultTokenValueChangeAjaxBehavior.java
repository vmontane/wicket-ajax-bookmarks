package com.zenika.wicketajaxbookmarks.js;

/**
 * Default token listener. Does not need an additional Javascript plugin (cf
 * {@link JqueryTokenValueChangeAjaxBehavior}. Will work only with IE8, Firefox
 * 3.6+, and Google Chrome 5+
 * 
 * @author vmontane
 * 
 */
public abstract class DefaultTokenValueChangeAjaxBehavior extends AbstractTokenValueChangeAjaxBehavior {

	/**
	 * Constructor.
	 */
	public DefaultTokenValueChangeAjaxBehavior() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	protected String getHashTagListenerScript() {
		StringBuilder javascript = new StringBuilder();
		javascript.append("function locationHashChanged() {");
		javascript.append(getCallbackScript());
		javascript.append("}");
		javascript.append("window.onhashchange = locationHashChanged;");
		return javascript.toString();
	}

}
