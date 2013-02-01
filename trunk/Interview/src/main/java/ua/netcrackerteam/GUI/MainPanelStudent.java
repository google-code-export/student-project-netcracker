/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 * Panel for Student view
 * @author Anna Kushnirenko
 */
public class MainPanelStudent extends MainPanel{
    private VerticalLayout mainPageLo;
    private VerticalLayout blankLayout;
    private VerticalLayout interviewLayout;
    private VerticalLayout settingsLo;

    public MainPanelStudent(HeaderLayout hlayout) {
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
        blankLayout = new VerticalLayout();
        fillBlankLayout();
        tabSheet.addTab(blankLayout,"Анкета");
        interviewLayout = new VerticalLayout();
        fillInterviewLayout();
        tabSheet.addTab(interviewLayout,"Собеседование");
        settingsLo = new VerticalLayout();
        fillSettingsLayout();
        tabSheet.addTab(settingsLo,"Настройки");
    }

    private void fillBlankLayout() {
        
    }

    private void fillInterviewLayout() {
        
    }

    private void fillSettingsLayout() {
        
    }
}
