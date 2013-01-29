/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.BaseTheme;

/**
 * Header of web site with logo, login and 
 * registration buttons
 * @author Anna Kushnirenko
 */
public class HeaderLayout extends HorizontalLayout{
    
    {   
        setStyleName("header");
        setWidth("100%");
        setHeight("150");
        setMargin(true);
        setSpacing(true);
        Embedded em = new Embedded("", new ThemeResource("images/logo.png"));
        em.setStyleName("emblem");
        addComponent(em);
        setComponentAlignment(em, Alignment.TOP_LEFT);
        setExpandRatio(em, 100);
    }
    /**
     * Constructor for guest header view
     * @param enter button
     * @param registr button
     */
    public HeaderLayout(Button enter, Button registr) {
        addComponent(enter);
        setComponentAlignment(enter, Alignment.BOTTOM_RIGHT);
        setExpandRatio(enter, 1);
        addComponent(registr);
        setComponentAlignment(registr, Alignment.BOTTOM_RIGHT);
        setExpandRatio(registr, 1);
        enter.setStyleName(BaseTheme.BUTTON_LINK);
        registr.setStyleName(BaseTheme.BUTTON_LINK);
    }
    
    /**
     * Constructor for registered users header view
     * @param exit button
     * @param username string
     */

    public HeaderLayout(Button exit, String username) {
        Button greet = new Button(username);
        addComponent(greet);
        setComponentAlignment(greet, Alignment.BOTTOM_RIGHT);
        setExpandRatio(greet, 1);
        addComponent(exit);
        setComponentAlignment(exit, Alignment.BOTTOM_RIGHT);
        setExpandRatio(exit, 1);
        greet.setStyleName(BaseTheme.BUTTON_LINK);
        exit.setStyleName(BaseTheme.BUTTON_LINK);
    }

}
