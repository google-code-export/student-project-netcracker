/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Panel for HR view
 * @author Anna Kushnirenko
 */
public class MainPanelHR extends MainPanel implements Button.ClickListener{
    private VerticalLayout settingsLo;
    private VerticalLayout blanksLo;
    private VerticalLayout interviewsLo;
    private VerticalLayout reportsLo;
    private Button edit;
    private RichTextArea editor = new RichTextArea();
    
    public MainPanelHR(HeaderLayout hlayout) {
        super(hlayout);
        setContent(getUserLayout());
        blanksLo = new VerticalLayout();
        fillBlanksLayout();
        tabSheet.addTab(blanksLo,"Анкеты");
        interviewsLo = new VerticalLayout();
        fillInterviewsLayout();
        tabSheet.addTab(interviewsLo,"Собеседования");
        reportsLo = new VerticalLayout();
        fillReportsLayout();
        tabSheet.addTab(reportsLo,"Статистика");
        settingsLo = new VerticalLayout();
        fillSettingsLayout();
        tabSheet.addTab(settingsLo,"Настройки");
    }
    
    public void buttonClick(Button.ClickEvent event) {
        if("Редактировать".equals(edit.getCaption())) {
                editor.setWidth("100%");
                editor.setHeight("600");
                editor.setValue(richText.getValue());
                mainPageLo.replaceComponent(richText, editor);
                mainPageLo.setComponentAlignment(editor,Alignment.BOTTOM_CENTER);
                edit.setCaption("Сохранить");
            } else if ("Сохранить".equals(edit.getCaption())){
                richText.setValue(editor.getValue());
                String s = (String) editor.getValue();
                mainPageLo.replaceComponent(editor, richText);
                edit.setCaption("Редактировать");
                try {
                    FileOutputStream out = new FileOutputStream("test_text.txt");
                    out.write(s.getBytes());
                    out.close();
                } catch (IOException ex) {
                    Window.Notification error = new Window.Notification("Ошибка записи в файл!",Window.Notification.TYPE_TRAY_NOTIFICATION);
                    error.setPosition(Window.Notification.POSITION_CENTERED);
                    getWindow().showNotification(error);
                }                 
            }
    }

    private void fillBlanksLayout() {
        
    }

    private void fillInterviewsLayout() {
        
    }

    private void fillReportsLayout() {
        
    }

    private void fillSettingsLayout() {
        
    }
}
