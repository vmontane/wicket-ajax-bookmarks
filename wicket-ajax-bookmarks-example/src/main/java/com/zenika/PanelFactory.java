package com.zenika;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

import com.zenika.model.UserModel;
import com.zenika.model.mock.UserModelStub;
import com.zenika.panels.CallbackPanel;
import com.zenika.panels.InformationPanel;
import com.zenika.panels.ModelPanel;

public class PanelFactory {

	public static List<Panel> getPanels(String panelsIds) {
		List<Panel> panels = new ArrayList<Panel>();

		IModel<UserModel> userModelModel = getUserModel(true);

		panels.add(new ModelPanel(panelsIds, userModelModel));
		panels.add(new InformationPanel(panelsIds, userModelModel));
		panels.add(new CallbackPanel(panelsIds, userModelModel));
		return panels;
	}

	/**
	 * Renvoie un Model classique, ou un LoadableDetachableModel. On remarque
	 * alors facilement que la taille de la Session est plus importante avec un
	 * Model simple, le LoadableDetachableModel ne persistant pas les objets.
	 * 
	 * @param useLoadableDetachableModel
	 * @return le Model à utiliser
	 */
	private static IModel<UserModel> getUserModel(boolean useLoadableDetachableModel) {
		IModel<UserModel> userModelModel = null;
		if (!useLoadableDetachableModel) {
			userModelModel = new Model<UserModel>(new UserModelStub());
		} else {
			userModelModel = new LoadableDetachableModel<UserModel>() {
				@Override
				protected UserModel load() {
					return new UserModelStub();
				}
			};
		}
		return userModelModel;
	}
}
