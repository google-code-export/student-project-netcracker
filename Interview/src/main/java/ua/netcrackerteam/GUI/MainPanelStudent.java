/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.ui.VerticalLayout;



/**
 * Panel for Student view
 * @author Anna Kushnirenko
 */
public class MainPanelStudent extends MainPanel {
    private StudentBlank blankLayout;
    private VerticalLayout interviewLayout;
    private VerticalLayout settingsLo;
    
    
    public MainPanelStudent(HeaderLayout hlayout,MainPage mainPage) {
        super(hlayout,mainPage);
        setContent(getUserLayout(hlayout));
        blankLayout = new StudentBlank(hlayout.getUsername());
        tabSheet.addTab(blankLayout,"Анкета");
        interviewLayout = new VerticalLayout();
        tabSheet.addTab(interviewLayout,"Собеседование");
        fillInterviewLayout();
        settingsLo = new VerticalLayout();
        tabSheet.addTab(settingsLo,"Настройки");
        fillSettingsLayout();
    }

    private void fillInterviewLayout() {
        
    }

    private void fillSettingsLayout() {
    }
   
}


