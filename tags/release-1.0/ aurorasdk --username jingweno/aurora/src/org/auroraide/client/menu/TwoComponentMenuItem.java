package org.auroraide.client.menu;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class that extends the standard MenuItem widget to display
 * some menu text as well as a 2nd component afte the text.  
 *
 */public class TwoComponentMenuItem extends MenuItem{
	
	/**
	 * Use a HorizontalPanel to store the two components of the MenuItem
	 */
	private HorizontalPanel theMenuItem = new HorizontalPanel();

	/**
	 * Sets the 2nd component of the TwoComponentMenuItem by removing the
	 * initial 2nd item from the HorizontalPanel and then placing the 
	 * provided paramter as the new 2nd item.
	 * 
	 * Then, we set the MenuItem text to be the HTML representation of the 
	 * HorizontalPanel.
	 * 
	 * @param newComponent The widget to be placed as the 2nd item in the MenuItem.
	 */
	public void setSecondComponent(String newComponent){
		theMenuItem.remove(1);
		theMenuItem.add(new Label(newComponent));
		SimplePanel dummyContainer = new SimplePanel();
		dummyContainer.add(theMenuItem);
		String test = DOM.getInnerHTML(dummyContainer.getElement());
		this.setHTML(test);
	}
	
	/**
	 * Sets the 1st component of the TwoComponentMenuItem by removing the
	 * initial 1st item from the HorizontalPanel and then placing the 
	 * provided paramter as the text to a new Label Widget as the 1st item.
	 * 
	 * Then, we set the MenuItem text to be the HTML representation of the 
	 * HorizontalPanel.
	 * 
	 * @param newComponent The widget to be placed as the 1st item in the MenuItem.
	 */
	public void setFirstComponent(Widget newComponent){
		/*theMenuItem.remove(0);
		theMenuItem.insert(new Label(newComponent), 0);
		SimplePanel dummyContainer = new SimplePanel();
		dummyContainer.add(theMenuItem);
		String test = DOM.getInnerHTML(dummyContainer.getElement());
		this.setHTML(test);*/
		theMenuItem.remove(0);
		theMenuItem.insert(newComponent, 0);
		SimplePanel dummyContainer = new SimplePanel();
		dummyContainer.add(theMenuItem);
		String test = DOM.getInnerHTML(dummyContainer.getElement());
		this.setHTML(test);
	}
	
	/**
	 * Constructor of the TwoComponentMenuItem - the result is a HTML MenuItem
	 * that can be treated as such.
	 * 
	 * @param theText The text part of the menu item.
	 * @param secondComponent A widget which is placed to the right of the text.
	 * @param theCommand The GWT Command that will be executed when the menu item is selected.
	 */
	public TwoComponentMenuItem(String theText,
			                    Widget secondComponent,
			                    Command theCommand){
		super(theText,false,theCommand);
		theMenuItem.add(secondComponent);
		theMenuItem.add(new Label(" "+theText+" "));
		//theMenuItem.setWidth("100px");
		//theMenuItem.setHorizontalAlignment(HasHorizontalAlignment);
		//theMenuItem.setCellWidth(secondComponent,"100px");
		setStyleName(theMenuItem.getWidget(1).getElement(), "gwt-MenuItem", true);
		//setStyleName(theMenuItem.getWidget(0).getElement(), "image", true);
		//setStyleName(theMenuItem.getElement(),"holder",true);
		setFirstComponent(secondComponent);
	}
}
