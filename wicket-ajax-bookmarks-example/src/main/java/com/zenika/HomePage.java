package com.zenika;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.zenika.panels.BlankPanel;
import com.zenika.wicketajaxbookmarks.js.DefaultTokenValueChangeAjaxBehavior;
import com.zenika.wicketajaxbookmarks.js.TokenManager;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	private static final String PANELS_IDS = "panel";

	private static final long serialVersionUID = 1L;

	protected static final String DIRECT_LINK_PARAMETER = "panel";

	private WebMarkupContainer currentPanel;

	protected Panel panelSelector = null;

	private List<Panel> panels;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		panels = PanelFactory.getPanels(PANELS_IDS);
		final DropDownChoice<Panel> dropDownChoice = new DropDownChoice<Panel>("panelSelector",
				new PropertyModel<Panel>(this, "panelSelector"), panels);
		add(dropDownChoice);
		dropDownChoice.setChoiceRenderer(new ChoiceRenderer<Panel>() {
			@Override
			public Object getDisplayValue(Panel panel) {
				return panel.getClass().getName();
			}
		});

		dropDownChoice.add(new AjaxFormComponentUpdatingBehavior("onchange") {

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				WebMarkupContainer newPanel = dropDownChoice.getModelObject();
				String currentPanelPosition = changeCurrentPanel(target, newPanel);
				// Changing the token
				TokenManager.changeToken(currentPanelPosition, target, true);
			}

		});
		// Adding a listener to monitor token changes
		dropDownChoice.add(new DefaultTokenValueChangeAjaxBehavior(true) {

			@Override
			public void onTokenChanged(AjaxRequestTarget target, String token) {
				target.appendJavaScript("alert('ontokenchanged:" + token + "');");

				// change the panel if the token is correct
				int tokenInt = -1;
				try {
					tokenInt = Integer.parseInt(token);
				} catch (NumberFormatException e) {
					// doing nothing
				}
				if (tokenInt >= 0) {
					changeCurrentPanel(target, panels.get(tokenInt));
				}
			}

		});
		currentPanel = new BlankPanel(PANELS_IDS);
		add(currentPanel);

	}

	private int getCurrentPanelPosition() {
		int matchingIndex = -1;
		if (currentPanel != null) {
			for (int i = 0; i < panels.size(); i++) {
				Panel panel = panels.get(i);
				if (panel != null && panel.getClass().equals(currentPanel.getClass())) {
					matchingIndex = i;
					break;
				}
			}
		}
		return matchingIndex;
	}

	private String changeCurrentPanel(AjaxRequestTarget target, WebMarkupContainer newPanel) {
		if (newPanel == null)
			newPanel = new BlankPanel(PANELS_IDS);
		newPanel.setOutputMarkupId(true);
		currentPanel.replaceWith(newPanel);
		currentPanel = newPanel;
		target.add(newPanel);
		String currentPanelPosition = Integer.toString(getCurrentPanelPosition());
		return currentPanelPosition;
	}

}
