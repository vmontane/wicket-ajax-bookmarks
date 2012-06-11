package com.zenika.panels;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.zenika.model.UserModel;
import com.zenika.util.AjaxCallback;

/**
 * Empty Panel used when there is nothing to display
 * 
 * @author vmontane
 * 
 */
public class CallbackPanel extends Panel {

	private Component modelPanel;

	private InformationPanel informationPanel;

	private WebMarkupContainer greyWrapper;

	public CallbackPanel(String id, IModel<UserModel> userModel) {
		super(id);
		this.setOutputMarkupId(true);

		modelPanel = new ModelPanel("edit", userModel, new AjaxCallback<UserModel>() {

			public void onCallback(AjaxRequestTarget target, UserModel object) {
				modelPanel.setVisible(false);
				unMaskInformationPanel();
				target.add(CallbackPanel.this);
				System.out.println(object.toString());
			}
		}).setVisible(false);

		greyWrapper = new WebMarkupContainer("greyWrapper");
		greyWrapper.setOutputMarkupId(true);
		informationPanel = new InformationPanel("information", userModel, new AjaxCallback<UserModel>() {

			public void onCallback(AjaxRequestTarget target, UserModel object) {
				modelPanel.setVisible(true);
				maskInformationPanel();
				target.add(CallbackPanel.this);
			}
		});

		this.add(modelPanel);
		this.add(greyWrapper);
		greyWrapper.add(informationPanel);
	}

	private void maskInformationPanel() {
		greyWrapper.add(new AttributeModifier("class", new Model<String>("content-grey")));
	}

	private void unMaskInformationPanel() {
		greyWrapper.add(new AttributeModifier("class", new Model<String>("content-normal")));
	}

}
