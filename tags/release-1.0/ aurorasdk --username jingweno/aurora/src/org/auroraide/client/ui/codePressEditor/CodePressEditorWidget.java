package org.auroraide.client.ui.codePressEditor;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.TextArea;

public class CodePressEditorWidget extends TextArea {

	private String elementID;
	private static CodePressEditor1 editor = null;
	private String language = "java";

	public CodePressEditor1 getCodePressEditor(String elementID) {
		if (editor == null) {
			editor = CodePressEditor1.create(elementID);
		}
		return editor;
	}

	public CodePressEditorWidget(String language) {
		super();
		this.language = language;
	}

	public CodePressEditorWidget() {
		super();

	}

	protected void onLoad() {

		elementID = "CodePressEditor" + System.identityHashCode(this);
		DOM.setElementAttribute(getElement(), "id", elementID);
		DOM.setElementAttribute(getElement(), "lang", "codepress " + language);
		DOM.setElementAttribute(getElement(), "wrap", "off");
		// DOM.setElementAttribute(getElement(), "style", "DISPLAY:none");
		System.out.println(getElement());
		DeferredCommand.addCommand(new BindCommand(elementID));
	}

	public String getCode() {
		return editor.getCode();
	}

	public void setCode(String code) {
		editor.setCode(code);
	}

	public void toggleLineNumbers() {
		editor.toggleLineNumbers();
	}

	public void edit(String language) {
		editor.edit(language);
	}

	public void toggleEditor() {
		editor.toggleEditor();
	}

	public void toogleReadOnly() {
		editor.toogleReadOnly();
	}

	public void toggleAutoComplete() {
		editor.toggleAutoComplete();
	}

	private static class BindCommand implements Command {

		private String elementID;

		/**
		 * Creates a new BindCommand instance to bind the TinyMCE instance to
		 * the newly created DIV after it's actually been attached to the DOM.
		 * 
		 * @param element
		 *            the Element to be replaced with the TinyMCE editor.
		 * @param elementID
		 *            the ID of the element being replaced.
		 */
		private BindCommand(String elementID) {
			// this.editor = editor;
			this.elementID = elementID;
		}

		/**
		 * Executes the actual command
		 */
		public void execute() {
			editor = CodePressEditor1.create(elementID);
		}

	}

	public String getElementID() {
		return elementID;
	}

}
