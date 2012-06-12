package com.zenika.wicketajaxbookmarks.js;

import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * Event sent when the token has changed
 * 
 * @author vmontane
 * 
 */
public class TokenChangedEvent {

	private final AjaxRequestTarget target;
	private final String token;

	public TokenChangedEvent(AjaxRequestTarget target, String token) {
		this.target = target;
		this.token = token;
	}

	public AjaxRequestTarget getTarget() {
		return target;
	}

	public String getToken() {
		return token;
	}

}
