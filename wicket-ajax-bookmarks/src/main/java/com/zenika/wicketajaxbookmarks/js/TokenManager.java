package com.zenika.wicketajaxbookmarks.js;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.util.string.JavaScriptUtils;

public class TokenManager {

	public static void changeToken(String token, AjaxRequestTarget target) {
		changeToken(token, target, true);
	}

	public static void changeToken(String token, AjaxRequestTarget target, boolean triggerEvent) {
		if (!triggerEvent) {
			target.appendJavaScript("tokenManagerSkipEvent = true;");
		}
		target.appendJavaScript("window.location.hash = '" + JavaScriptUtils.escapeQuotes(token) + "';");
	}

	public static void addTokenValueChangeBehavior(Component component, AbstractTokenValueChangeAjaxBehavior behavior) {
		component.add(behavior);
	}

}
