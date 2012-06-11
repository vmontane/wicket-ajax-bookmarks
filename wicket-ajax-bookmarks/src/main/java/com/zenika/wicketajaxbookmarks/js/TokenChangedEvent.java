package com.zenika.wicketajaxbookmarks.js;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class TokenChangedEvent {

	private final AjaxRequestTarget target;
	private final String string;

	public TokenChangedEvent(AjaxRequestTarget target, String string) {
		this.target = target;
		this.string = string;
	}

	public AjaxRequestTarget getTarget() {
		return target;
	}

	public String getString() {
		return string;
	}

}
