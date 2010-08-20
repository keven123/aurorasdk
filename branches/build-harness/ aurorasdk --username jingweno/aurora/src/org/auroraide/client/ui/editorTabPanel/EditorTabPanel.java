package org.auroraide.client.ui.editorTabPanel;

import org.auroraide.client.ui.codePressEditor.CodePressEditorWidget;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class EditorTabPanel extends TabPanel {

	public boolean remove(int index) {
		
		Widget widget = getWidget(index);
		
		if (widget instanceof CodePressEditorWidget) {
			
			Element element = DOM
					.getElementById(((CodePressEditorWidget) widget)
							.getElementID()
							+ "_cpf");
			
			DOM.removeChild(DOM.getParent(widget.getElement()), element);
			
		}
		
		return super.remove(index);
	}

	public boolean remove(Widget widget) {
		if (widget instanceof CodePressEditorWidget) {
			Element element = DOM
					.getElementById(((CodePressEditorWidget) widget)
							.getElementID()
							+ "_cpf");
			DOM.removeChild(DOM.getParent(widget.getElement()), element);
		}
		return super.remove(widget);
	}

	public EditorTabPanel() {
		super();
	}
}
