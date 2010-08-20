package org.auroraide.client.ui.codePressEditor;

import org.auroraide.client.ui.codePressEditor.impl.CodePressEditorImpl;

import com.google.gwt.core.client.JavaScriptObject;

public class CodePressEditor1 extends JavaScriptObject {

	private static CodePressEditorImpl impl = new CodePressEditorImpl();

	public static CodePressEditor1 create(String elementID) {
		return impl.create(elementID);
	}

	public String getCode() {
		return impl.getCode(this);
	}

	public void setCode(String code) {
		impl.setCode(this, code);
	}

	public void toggleLineNumbers() {
		impl.toggleLineNumbers(this);
	}

	public void edit(String language) {
		impl.edit(this, language);
	}

	public void toggleEditor() {
		impl.toggleEditor(this);
	}

	public void toogleReadOnly() {
		impl.toggleReadOnly(this);
	}

	public void toggleAutoComplete() {
		impl.toggleAutoComplete(this);
	}

}
