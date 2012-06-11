package com.zenika.panels;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * Empty Panel used when there is nothing to display
 * @author vmontane
 *
 */
public class BlankPanel extends Panel {

	public BlankPanel(String id) {
		super(id);
		this.setOutputMarkupId(true);
	}

}
