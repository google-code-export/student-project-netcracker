/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

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
        setContent(getUserLayout(hlayout));
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
