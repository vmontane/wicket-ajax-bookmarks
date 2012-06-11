
Wicket Ajax Bookmarks
============================

`Wicket Ajax Bookmarks` enables you to interact with the Browser history while using Ajax in Wicket.

It uses the URL hash

This first version has been released for Wicket `1.5.x` releases.


Features
--------

* Change the url token to any string (http://url.com/index.html#token)
* Trigger an Ajax request when the token changes (when the user presses the forward/back button on his browser)


How to use
--------

To change the current token just call
	TokenManager.changeToken(currentPanelPosition, target);
	
To add a listener and get the token as soon as it changes, add an AbstractTokenValueChangeAjaxBehavior to your component

	dropDownChoice.add(new DefaultTokenValueChangeAjaxBehavior() {

		@Override
		public void onTokenChanged(AjaxRequestTarget target, String token) {
			target.appendJavaScript("alert('ontokenchanged:" + token + "');");
		}

	});
	or 
	dropDownChoice.add(new JQueryTokenValueChangeAjaxBehavior() {

		@Override
		public void onTokenChanged(AjaxRequestTarget target, String token) {
			target.appendJavaScript("alert('ontokenchanged:" + token + "');");
		}

	});
	
The first one requires a recent version of Internet Explorer (IE8), and the second one requires JQuery.

When you change the token with TokenManager.changeToken, you can ask not to trigger an event.

	TokenManager.changeToken(currentPanelPosition, target, false);