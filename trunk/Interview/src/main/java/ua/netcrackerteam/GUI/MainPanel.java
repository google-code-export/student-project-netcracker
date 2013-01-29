/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Main panel with editable text 
 * @author Anna Kushnirenko
 */
public class MainPanel extends Panel implements Button.ClickListener{
    private final VerticalLayout layout;
    private Label richText;
    protected Button edit;
    private RichTextArea editor = new RichTextArea();

    public MainPanel(HeaderLayout hlayout) {
        setStyleName(Reindeer.PANEL_LIGHT);
        setSizeFull();
        layout = (VerticalLayout) getContent();
        layout.setMargin(false);
        layout.setSpacing(true);
        layout.setWidth("100%");
        layout.addComponent(hlayout);
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
        edit = new Button("Редактировать");
        edit.setVisible(false);
        edit.addListener((Button.ClickListener) this);
        layout.addComponent(edit);
        layout.setComponentAlignment(edit,Alignment.BOTTOM_CENTER);
    }

    public void buttonClick(ClickEvent event) {
        if("Редактировать".equals(edit.getCaption())) {
                editor.setWidth("100%");
                editor.setHeight("600");
                editor.setValue(richText.getValue());
                layout.replaceComponent(richText, editor);
                layout.setComponentAlignment(editor,Alignment.BOTTOM_CENTER);
                edit.setCaption("Сохранить");
            } else if ("Сохранить".equals(edit.getCaption())){
                richText.setValue(editor.getValue());
                String s = (String) editor.getValue();
                layout.replaceComponent(editor, richText);
                edit.setCaption("Редактировать");
                try {
                    FileOutputStream out = new FileOutputStream("test_text.txt");
                    out.write(s.getBytes());
                    out.close();
                } catch (IOException ex) {
                    richText.setValue("Ошибка записи в файл!");
                }                 
            }
    }
}
