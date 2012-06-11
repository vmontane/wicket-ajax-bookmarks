package com.zenika.wicketajaxbookmarks.js;

/**
 * Ajout du listener par défaut, ne nécessitant pas de plugin externe (cf
 * {@link JqueryTokenValueChangeAjaxBehavior}. Disponible uniquement sur IE8,
 * Firefox 3.6+, et Google Chrome
 * 
 * @author vmontane
 * 
 */
public abstract class DefaultTokenValueChangeAjaxBehavior extends AbstractTokenValueChangeAjaxBehavior {

	public DefaultTokenValueChangeAjaxBehavior() {
		super();
	}

	protected String getHashTagListenerScript() {
		StringBuilder javascript = new StringBuilder();
		javascript.append("function locationHashChanged() {");
		javascript.append(getCallbackScript());
		javascript.append("}");
		javascript.append("window.onhashchange = locationHashChanged;");
		return javascript.toString();
	}

}
