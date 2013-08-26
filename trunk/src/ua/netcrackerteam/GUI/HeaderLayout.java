/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.BaseTheme;

/**
 * Header of web site with logo, login and 
 * registration buttons
 * @author Anna Kushnirenko
 */
@SuppressWarnings("serial")
public class HeaderLayout extends CssLayout {
	
	private HorizontalLayout buttons = new HorizontalLayout();
	private Button enter;
	private Button register;
	private String username;
	
	public HeaderLayout(Button enter, Button register) {
		enter.setStyleName(BaseTheme.BUTTON_LINK);
		register.setStyleName(BaseTheme.BUTTON_LINK);
		
		this.enter = enter;
		this.register = register;
		
		buttons.addComponent(enter);
		buttons.addComponent(register);
		buttons.setStyleName("header-buttons");
		setStyleName("header");
		addComponent(buttons);
	}
	
	public void changeButtons(Button exit, String username) {
		this.username = username;
		buttons.replaceComponent(enter, new Label(username));
		exit.setStyleName(BaseTheme.BUTTON_LINK);
		buttons.replaceComponent(register, exit);
	}
	
	public String getUserName() {
		return username;
	}
}
