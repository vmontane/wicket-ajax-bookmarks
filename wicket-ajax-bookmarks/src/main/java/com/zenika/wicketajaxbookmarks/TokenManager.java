package com.zenika.wicketajaxbookmarks;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.util.string.JavaScriptUtils;

/**
 * This class allows you to change the current token, providing an
 * {@link AjaxRequestTarget}, and to add a behavior to your component to monitor
 * the history changes.
 * 
 * @author vmontane
 * 
 */
public class TokenManager {

	/**
	 * Change the url token.
	 * 
	 * @param token
	 *            the String to set the token to
	 * @param target
	 *            the AjaxRequestTarget that triggered this change
	 */
	public static void changeToken(String token, AjaxRequestTarget target) {
		changeToken(token, target, true);
	}

	/**
	 * Change the url token.
	 * 
	 * @param token
	 *            the String to set the token to
	 * @param target
	 *            the AjaxRequestTarget that triggered this change
	 * @param triggerEvent
	 *            set to false if you don't want the listeners to get the event
	 *            when the token is changed
	 */
	public static void changeToken(String token, AjaxRequestTarget target, boolean triggerEvent) {
		if (!triggerEvent) {
			target.appendJavaScript("tokenManagerSkipEvent = true;");
		}
		target.appendJavaScript("window.location.hash = '" + JavaScriptUtils.escapeQuotes(token) + "';");
	}

	/**
	 * Util class to easyly find how to add an
	 * {@link AbstractTokenValueChangeAjaxBehavior} to your component. Use
	 * directly myComponent.add(new DefaultTokenValueChangeAjaxBehavior());
	 * instead.
	 * 
	 * @param component
	 *            a page or a component to add the behavior
	 * @param behavior
	 *            the behavior to add
	 */
	public static void addTokenValueChangeBehavior(Component component, AbstractTokenValueChangeAjaxBehavior behavior) {
		component.add(behavior);
	}

}
