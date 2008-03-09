package org.auroraide.client.menu;



import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Widget;


/**
 * Class that extends the TwoComponentMenuItem (which extends the Menu Item)
 * Allows two widgets to be kept with the menu text, of which only one is 
 * displayed at a time and can be toggled between by calling the toggleMenuItem()
 * method.
 *
 */
public class ToggleMenuItem extends TwoComponentMenuItem{
	
	/**
	 * Inner class that contains user readible static values for states that 
	 * the ToggleMenuItem widget can contain.
	 */
	public static class ToggleMenuItemState{
		static public final boolean ON = true;
		static public final boolean OFF = false;
	}
	
	/**
	 * Variable storing the two widgets that the ToggleMenutItem widget can 
	 * display. 
	 */
	private Widget[] states;
	
	/**
	 * Stores the state of the toggle, initially set to ON.
	 */
	protected boolean state = ToggleMenuItemState.ON;
	
	/**
	 * Method to toggle the menu item.  It switches between the widgets
	 * shown after the menu text.
	 */
	public void toggle(){
		setFirstComponent(states[state?1:0]);
		state = !state;
	}
	
	public boolean isToggle(){
		return state;
	}
	
	/**
	 * Method to return the current state of the ToggleMenuItem to the user code. 
	 * @return The current state of the ToggleMenuItem 
	 */
	public boolean getState(){
		return state;
	}
	
	/**
	 * ToggleMenuItem constructor.
	 * 
	 * @param theText The menu text.
	 * @param state1 Widget for the first (and initial) state.
	 * @param state2 Widget for the second state.
	 * @param command GWT Command that is executed when the menu item is clicked.
	 */
	public ToggleMenuItem(	String theText, 
							Widget state1, 
							Widget state2, 
							Command command){
		super(theText, state1, command);
		states = new Widget[2];
		states[0] = state1;
		states[1] = state2;
	}	
}