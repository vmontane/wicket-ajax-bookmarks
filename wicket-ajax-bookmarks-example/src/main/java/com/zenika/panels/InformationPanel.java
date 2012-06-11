package com.zenika.panels;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import com.zenika.model.UserModel;
import com.zenika.util.AjaxCallback;

/**
 * Empty Panel used when there is nothing to display
 * 
 * @author vmontane
 * 
 */
public class InformationPanel extends Panel {

	public InformationPanel(String id, IModel<UserModel> userModel) {
		this(id, userModel, null);
	}

	public InformationPanel(String id, IModel<UserModel> userModel,
			final AjaxCallback<UserModel> callback) {
		super(id);
		this.setOutputMarkupId(true);
		this.setDefaultModel(new CompoundPropertyModel<UserModel>(userModel));

		ResourceModel userLabelModel = new ResourceModel("userLabel");
		this.add(new Label("userLabel", userLabelModel));
		ResourceModel emailLabelModel = new ResourceModel("emailLabel");
		this.add(new Label("emailLabel", emailLabelModel));
		ResourceModel streetLabelModel = new ResourceModel("streetLabel");
		this.add(new Label("streetLabel", streetLabelModel));
		ResourceModel townLabelModel = new ResourceModel("townLabel");
		this.add(new Label("townLabel", townLabelModel));

		this.add(new Label("userId"));
		this.add(new Label("email"));
		this.add(new Label("address.street"));
		this.add(new Label("address.town"));

		this.add(new AjaxLink<String>("edit") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				if (callback == null) {
					target.appendJavaScript("alert('callback not set, no action done'");
				} else {
					callback.onCallback(target, null);
				}
			}

		}.setVisibilityAllowed(callback != null));
	}

}
