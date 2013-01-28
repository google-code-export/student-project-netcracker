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
 *
 * @author akush_000
 */
public class HeaderLayout extends HorizontalLayout{
    
    public HeaderLayout(Button enter, Button registr) {
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
        addComponent(enter);
        setComponentAlignment(enter, Alignment.BOTTOM_RIGHT);
        setExpandRatio(enter, 1);
        addComponent(registr);
        setComponentAlignment(registr, Alignment.BOTTOM_RIGHT);
        setExpandRatio(registr, 1);
    }
    
}
