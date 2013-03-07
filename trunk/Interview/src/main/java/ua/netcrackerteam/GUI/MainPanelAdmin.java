/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 * Panel for Admin view
 * @author Anna Kushnirenko
 */
public class MainPanelAdmin extends MainPanel {
    private AdminUserManagement blankLayout;
    private TestLayout testLayout;
    private SettingsLayout settingsLayout;


    public MainPanelAdmin(final HeaderLayout hlayout,final MainPage mainPage) {
        super(hlayout,mainPage);
        setContent(getUserLayout(hlayout));
        final Component c1 = new VerticalLayout();
        final Component c2 = new VerticalLayout();
        final Component c3 = new VerticalLayout();
        tabSheet.addTab(c1,"User Management");
        tabSheet.addTab(c2,"Настройки");
        tabSheet.addTab(c3,"Test");
        tabSheet.addListener(new TabSheet.SelectedTabChangeListener() {

            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                final TabSheet source = (TabSheet) event.getSource();
                if(source.getSelectedTab() == c1) {
                    blankLayout = new AdminUserManagement(hlayout.getUsername(),mainPage);
                    source.replaceComponent(c1, blankLayout);
                }  else if (source.getSelectedTab() == c2) {
                    settingsLayout = new SettingsLayout(hlayout.getUsername());
                    source.replaceComponent(c2, settingsLayout);
                }  else if (source.getSelectedTab() == c3) {
                    testLayout = new TestLayout(hlayout.getUsername(), mainPage);
                    source.replaceComponent(c3, testLayout);
                }
            }
        });
    }
}
