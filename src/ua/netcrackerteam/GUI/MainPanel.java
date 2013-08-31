/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.terminal.ThemeResource;
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
@SuppressWarnings("serial")
public class MainPanel extends Panel {
    private final VerticalLayout layout;
    protected Label richText;
    protected Footer footer;
    protected VerticalLayout mainPageLo;
    protected TabSheet tabSheet;

    public TabSheet getTabSheet() {
        return tabSheet;
    }

    public void setTabSheet(TabSheet tabSheet) {
        this.tabSheet = tabSheet;
    }

    public MainPanel(HeaderLayout hlayout, MainPage mainPage) {
        setStyleName(Reindeer.PANEL_LIGHT);
        setScrollable(true);
        setWidth("800px");
        layout = (VerticalLayout) getContent();
        layout.setMargin(false);
        layout.setSpacing(true);
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
            System.out.println("File main_page_text.txt is not found");
        }
        richText = new Label(s);
        footer = new Footer();
        richText.setContentMode(Label.CONTENT_XHTML);
        richText.setStyleName("infotext");
        Embedded img = new Embedded(null, new ThemeResource("images/main-logo.png"));
        img.setStyleName("infotext");
        CssLayout wrapper = new CssLayout();
        wrapper.addComponent(img);
        wrapper.addComponent(richText);
        wrapper.setStyleName("form-info");
        layout.addComponent(wrapper);
        layout.addComponent(footer);
        //layout.setComponentAlignment(richText,Alignment.BOTTOM_CENTER);
    }

    public VerticalLayout getUserLayout(HeaderLayout hlayout) {
        layout.removeAllComponents();
        layout.addComponent(hlayout);
        Embedded img = new Embedded(null, new ThemeResource("images/main-logo.png"));
        img.setStyleName("infotext");
        CssLayout wrapper = new CssLayout();
        CssLayout inner = new CssLayout();
        inner.addComponent(img);
        inner.addComponent(richText);
        wrapper.addComponent(inner);
        wrapper.addComponent(footer);
        //wrapper.setStyleName("form-info");
        tabSheet = new TabSheet();
        layout.addComponent(tabSheet);
        tabSheet.addTab(wrapper,"Главная");
        return layout;
    }
}
