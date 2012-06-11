package com.zenika.panels;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import com.zenika.model.UserModel;
import com.zenika.util.AjaxCallback;

/**
 * Empty Panel used when there is nothing to display
 * 
 * @author vmontane
 * 
 */
public class ModelPanel extends Panel {

	private FeedbackPanel feedbackPanel;

	private AjaxCallback<UserModel> callback;

	public ModelPanel(String id, IModel<UserModel> userModel) {
		this(id, userModel, null);
	}

	public ModelPanel(String id, IModel<UserModel> userModel, AjaxCallback<UserModel> onSubmitCallback) {
		super(id);
		this.callback = onSubmitCallback;
		this.setOutputMarkupId(true);
		this.add(new ModelPanelForm("form", new CompoundPropertyModel<UserModel>(userModel)));
		feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setOutputMarkupId(true);
		this.add(feedbackPanel);
	}

	private class ModelPanelForm extends Form<UserModel> {

		public ModelPanelForm(String id, IModel<UserModel> model) {
			super(id, model);
			ResourceModel userLabelModel = new ResourceModel("userLabel");
			this.add(new Label("userLabel", userLabelModel));
			ResourceModel emailLabelModel = new ResourceModel("emailLabel");
			this.add(new Label("emailLabel", emailLabelModel));
			ResourceModel streetLabelModel = new ResourceModel("streetLabel");
			this.add(new Label("streetLabel", streetLabelModel));
			ResourceModel townLabelModel = new ResourceModel("townLabel");
			this.add(new Label("townLabel", townLabelModel));

			this.add(new TextField<String>("userId").setRequired(true).setLabel(userLabelModel));
			this.add(new TextField<String>("email").add(EmailAddressValidator.getInstance()).setLabel(emailLabelModel));
			this.add(new RequiredTextField<String>("address.street").setLabel(streetLabelModel));
			this.add(new TextField<String>("address.town").setLabel(townLabelModel));

			this.add(new AjaxButton("submit") {
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					if (callback != null) {
						callback.onCallback(target, (UserModel) form.getModelObject());
					}
				}

				@Override
				protected void onError(AjaxRequestTarget target, Form<?> form) {
					// Il faut renvoyer le feedbackPanel, sinon l'utilisateur ne
					// sait pas que la validation a échoué
					target.add(feedbackPanel);
				}
			});
		}
	}

}
