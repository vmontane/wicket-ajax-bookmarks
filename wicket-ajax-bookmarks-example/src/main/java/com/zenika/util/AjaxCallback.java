package com.zenika.util;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;

public interface AjaxCallback<T> extends Serializable {

	void onCallback(AjaxRequestTarget target, T object);

}
