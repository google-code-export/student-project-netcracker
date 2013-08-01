/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

/**
 * Header of web site with logo, login and 
 * registration buttons
 * @author Anna Kushnirenko
 */
public class HeaderLayout extends GridLayout {
    private Embedded em = null;
    private VerticalLayout emlo;
    private String username = "";
    
    {   
        setColumns(3);
        setStyleName("header");
        setWidth("100%");
        setHeight("110");
        emlo = new VerticalLayout();
        em = new Embedded("", new ThemeResource("images/logo.png"));
        em.setStyleName("emblem");
        emlo.addComponent(em);
        emlo.setWidth("650");
        addComponent(emlo);
    }
    /**
     * Constructor for guest header view
     * @param enter button
     * @param registr button
     */
    public HeaderLayout(Button enter, Button registr) {
        enter.setWidth("50");
        registr.setWidth("100");
        addComponent(enter,1,0);
        setComponentAlignment(enter, Alignment.BOTTOM_CENTER);
        addComponent(registr,2,0);
        setComponentAlignment(registr, Alignment.BOTTOM_RIGHT);
        enter.setStyleName(BaseTheme.BUTTON_LINK);
        registr.setStyleName(BaseTheme.BUTTON_LINK);
    }
    
    /**
     * Constructor for registered persons header view
     * @param exit button
     * @param username string
     */

    public HeaderLayout(Button exit, String username) {
        this.username = username;
        setHeight("90");
        VerticalLayout exitlo = new VerticalLayout();
        VerticalLayout greetlo = new VerticalLayout();
        greetlo.setSizeFull();
        exitlo.setSizeFull();
        emlo.setWidth("580");
        exitlo.setWidth("70");
        exitlo.addComponent(exit);
        exitlo.setComponentAlignment(exit, Alignment.BOTTOM_CENTER);
        Button greet = new Button(username);
        greetlo.addComponent(greet);
        greetlo.setWidth("150");
        greetlo.setComponentAlignment(greet, Alignment.BOTTOM_RIGHT);
        addComponent(greetlo,1,0);
        addComponent(exitlo,2,0);
        greet.setStyleName(BaseTheme.BUTTON_LINK);
        exit.setStyleName(BaseTheme.BUTTON_LINK);
    }

    public String getUsername() {
        return username;
    }
}
