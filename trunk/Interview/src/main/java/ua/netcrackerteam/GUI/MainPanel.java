/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Main panel with editable text 
 * @author Anna Kushnirenko
 */
public class MainPanel extends Panel {
    private final VerticalLayout layout;
    protected Label richText;
    protected VerticalLayout mainPageLo;
    protected TabSheet tabSheet;
    

    public MainPanel(HeaderLayout hlayout, MainPage mainPage) {
        setStyleName(Reindeer.PANEL_LIGHT);
        setWidth("800px");
        layout = (VerticalLayout) getContent();
        layout.setMargin(false);
        layout.setSpacing(true);
        layout.setWidth("100%");
        layout.addComponent(hlayout);
        String s = "";
        DataInputStream in;
        try {
            WebApplicationContext context = (WebApplicationContext) mainPage.getContext();
            File file = new File (context.getHttpSession().getServletContext().getRealPath("/WEB-INF/resources/main_page_text.txt") );
            in = new DataInputStream(new FileInputStream(file));
            byte[] array = new byte[in.available()];
            in.read(array);
            s = new String(array);
            in.close();
        } catch (IOException ex) {
            System.out.println("File test_text.txt is not found");
        }
        richText = new Label(s);
        richText.setContentMode(Label.CONTENT_XHTML);
        layout.addComponent(richText);
        layout.setComponentAlignment(richText,Alignment.BOTTOM_CENTER);
    }

    public VerticalLayout getUserLayout(HeaderLayout hlayout) {
        layout.removeAllComponents();
        layout.setSpacing(false);
        layout.addComponent(hlayout);
        hlayout.setStyleName("user");
        mainPageLo = new VerticalLayout();
        mainPageLo.addComponent(richText);
        layout.setStyleName("user");
        tabSheet = new TabSheet();
        layout.addComponent(tabSheet);
        tabSheet.addTab(mainPageLo,"Главная");
        return layout;
    }
}
