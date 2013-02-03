/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.ui.*;
import org.vaadin.addon.componentexport.java.PdfFromComponent;

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

    /*
        test implementation of form
     */
    private void fillBlankLayout() {
        Label label = new Label("Hello Vaadin user");
        blankLayout.addComponent(label);
        Table table = new Table();
        for (int i=0;i<50;i++){
            table.addItem("Item nro: "+i);
        }
        blankLayout.addComponent(table);
        Select s = new Select("select");
        for (int i=0;i<50;i++){
            s.addItem("Item nro: "+i);
        }
        blankLayout.addComponent(s);
        TextField t = new TextField("textfield");
        t.setValue("Hola");
        blankLayout.addComponent(t);
        Button b = new Button("PDF");

        b.addListener(new com.vaadin.ui.Button.ClickListener() {
            @Override
            public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
                PdfFromComponent factory = new PdfFromComponent();
                factory.setFileName("Анкета.pdf");
                factory.export(blankLayout);
            }
        });
        blankLayout.addComponent(b);
    }

    private void fillInterviewLayout() {
        
    }

    private void fillSettingsLayout() {
    }
}
