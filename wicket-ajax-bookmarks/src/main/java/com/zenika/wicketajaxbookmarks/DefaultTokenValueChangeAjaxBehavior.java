package com.zenika.wicketajaxbookmarks;

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
	 * Constructor.
	 * 
	 * @param getTokenOnStartup
	 *            if true, an ajax request will be sent to get the token as soon
	 *            as the page loads. Default to false.
	 */
	public DefaultTokenValueChangeAjaxBehavior(boolean getTokenOnStartup) {
		super(getTokenOnStartup);
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
