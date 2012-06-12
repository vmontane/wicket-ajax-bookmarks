package com.zenika.wicketajaxbookmarks.js;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.string.StringValue;

public abstract class AbstractTokenValueChangeAjaxBehavior extends AbstractDefaultAjaxBehavior {

	private static final String PARAM_TOKEN = "TOKEN";

	public AbstractTokenValueChangeAjaxBehavior() {
		super();
	}

	@Override
	protected void respond(AjaxRequestTarget target) {
		IRequestParameters requestParameters = RequestCycle.get().getRequest().getRequestParameters();
		StringValue tokenParameterValue = requestParameters.getParameterValue(PARAM_TOKEN);
		onTokenChanged(target, tokenParameterValue.toString());
		getComponent().send(getComponent().getPage(), Broadcast.BREADTH,
				new TokenChangedEvent(target, tokenParameterValue.toString()));
	}

	public abstract void onTokenChanged(AjaxRequestTarget target, String token);

	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		super.renderHead(component, response);
		StringBuilder onLoadJavaScript = new StringBuilder();
		onLoadJavaScript.append("tokenManagerSkipEvent = false;");
		onLoadJavaScript.append(getHashTagListenerScript());
		response.renderOnLoadJavaScript(onLoadJavaScript.toString());
	}

	/**
	 * Returns the Javascript code needed to create the hash change listener.
	 * Multiple implementations possible (html, jquery, ...).
	 * 
	 * @return Javascript
	 */
	protected abstract String getHashTagListenerScript();

	@Override
	protected CharSequence getCallbackScript() {
		StringBuilder callbackStript = new StringBuilder();
		callbackStript.append("if (tokenManagerSkipEvent) {");
		callbackStript.append("tokenManagerSkipEvent = false;");
		callbackStript.append("} else {");
		callbackStript.append(generateCallbackScript("wicketAjaxGet('" + getCallbackUrl() + "'"));
		callbackStript.append("}");
		return callbackStript;
	}

	@Override
	public CharSequence getCallbackUrl() {
		return super.getCallbackUrl() + "&" + PARAM_TOKEN + "=' + window.location.hash.replace('#', '') + '";
	}

}
