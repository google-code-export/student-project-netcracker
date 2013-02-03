/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Main panel with editable text 
 * @author Anna Kushnirenko
 */
public class MainPanel extends Panel {
    private final VerticalLayout layout;
    protected Label richText;
    private HeaderLayout hlayout;
    protected VerticalLayout mainPageLo;
    protected TabSheet tabSheet;
    

    public MainPanel(HeaderLayout hlayout) {
        this.hlayout = hlayout;
        setStyleName(Reindeer.PANEL_LIGHT);
        setSizeFull();
        layout = (VerticalLayout) getContent();
        layout.setMargin(false);
        layout.setSpacing(true);
        layout.setWidth("100%");
        layout.addComponent(this.hlayout);
        layout.setSpacing(true);
        String s = "";
        FileInputStream in;
        try {
            in = new FileInputStream("test_text.txt");
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

    public VerticalLayout getUserLayout() {
        layout.removeAllComponents();
        layout.addComponent(hlayout);
        hlayout.setStyleName("user");
        hlayout.setHeight("130");
        mainPageLo = new VerticalLayout();
        mainPageLo.addComponent(richText);
        layout.setStyleName("user");
        tabSheet = new TabSheet();
        layout.addComponent(tabSheet);
        tabSheet.addTab(mainPageLo,"Главная");
        return layout;
    }
}
