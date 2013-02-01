/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 * Panel for Interviewer view
 * @author Anna Kushnirenko
 */
public class MainPanelInterviewer extends MainPanel{
    private VerticalLayout mainPageLo;
    private VerticalLayout interviewsLo;
    private VerticalLayout settingsLo;

    public MainPanelInterviewer(HeaderLayout hlayout) {
        super(hlayout);
        hlayout.setStyleName("user");
        hlayout.setHeight("130");
        mainPageLo = new VerticalLayout();
        mainPageLo.addComponent(richText);
        VerticalLayout layout = getClearField();
        layout.setStyleName("user");
        setContent(layout);
        TabSheet tabSheet = new TabSheet();
        layout.addComponent(tabSheet);
        tabSheet.addTab(mainPageLo,"Главная");
        interviewsLo = new VerticalLayout();
        fillInterviewsLayout();
        tabSheet.addTab(interviewsLo,"Собеседования");
        settingsLo = new VerticalLayout();
        fillSettingsLayout();
        tabSheet.addTab(settingsLo,"Настройки");
    }

    private void fillInterviewsLayout() {
        
    }

    private void fillSettingsLayout() {
        
    }
    
}
