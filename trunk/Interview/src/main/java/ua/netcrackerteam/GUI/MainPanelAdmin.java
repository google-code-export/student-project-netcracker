/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.ui.VerticalLayout;

/**
 * Panel for Admin view
 * @author Anna Kushnirenko
 */
public class MainPanelAdmin extends MainPanel {
    private VerticalLayout hrSettingsLo;
    private VerticalLayout interSettingsLo;
    private VerticalLayout settingsLo;
    
    public MainPanelAdmin(HeaderLayout hlayout,MainPage mainPage) {
        super(hlayout,mainPage);
        setContent(getUserLayout(hlayout));
        hrSettingsLo = new VerticalLayout();
        fillHRSetLayout();
        tabSheet.addTab(hrSettingsLo, "Настройки HR");
        interSettingsLo = new VerticalLayout();
        fillInterSetLayout();
        tabSheet.addTab(interSettingsLo, "Настройки интервьюеров");
        settingsLo = new VerticalLayout();
        fillSetLayout();
        tabSheet.addTab(settingsLo, "Настройки администратора");
    }

    private void fillHRSetLayout() {
        
    }

    private void fillInterSetLayout() {
        
    }

    private void fillSetLayout() {
        
    }
    
}
