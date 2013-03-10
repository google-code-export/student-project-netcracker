/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.Button;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 * Panel for HR view
 * @author Anna Kushnirenko, Filipenko
 */
public class MainPanelHR extends MainPanel{
    private VerticalLayout settingsLo;
    private VerticalLayout blanksLo;
    private VerticalLayout interviewsLo;
    private VerticalLayout reportsLo;
    private Button edit;
    private RichTextArea editor = new RichTextArea();
    private int height;
    private SettingsLayout settingsLayout;
    
    public MainPanelHR(final HeaderLayout hlayout, final MainPage mainPage) {
        super(hlayout,mainPage);
        setContent(getUserLayout(hlayout));
        WebApplicationContext context = (WebApplicationContext) mainPage.getContext();
        WebBrowser webBrowser = context.getBrowser();
        height = webBrowser.getScreenHeight()-300;
        /*edit = new Button("Редактировать");
        mainPageLo.addComponent(edit);
        mainPageLo.setComponentAlignment(edit, Alignment.TOP_CENTER);
        edit.addListener(this);*/
        blanksLo = new VerticalLayout();
        tabSheet.addTab(blanksLo,"Анкеты");
        interviewsLo = new VerticalLayout();
        tabSheet.addTab(interviewsLo,"Собеседования");
        reportsLo = new VerticalLayout();
        tabSheet.addTab(reportsLo,"Статистика");
        settingsLo = new VerticalLayout();
        tabSheet.addTab(settingsLo,"Настройки");
        tabSheet.addListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                final TabSheet source = (TabSheet) event.getSource();
                if(source.getSelectedTab() == blanksLo) {
                    HRBlankLayout blankLayout = new HRBlankLayout();
                    blankLayout.setHeight(height,UNITS_PIXELS);
                    blankLayout.setWidth("100%");
                    blankLayout.setMargin(true, false, false, false);
                    source.replaceComponent(blanksLo, blankLayout);
                }  else if (source.getSelectedTab() == interviewsLo) {
                   // settingsLayout = new SettingsLayout(hlayout.getUsername(), mainPage);
                    //source.replaceComponent(c2, settingsLayout);
                }  else if (source.getSelectedTab() == reportsLo) {
                    // settingsLayout = new SettingsLayout(hlayout.getUsername(), mainPage);
                    //source.replaceComponent(c2, settingsLayout);
                }  else if (source.getSelectedTab() == settingsLo) {
                    settingsLayout = new SettingsLayout(hlayout.getUsername(), mainPage);
                    source.replaceComponent(settingsLo, settingsLayout);
                }
            }
        });
    }
    
   /* public void buttonClick(Button.ClickEvent event) {
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
                    WebApplicationContext context = (WebApplicationContext) getApplication().getContext();
                    File file = new File (context.getHttpSession().getServletContext().getRealPath("/WEB-INF/resources/main_page_text.txt") );
                    FileOutputStream out = new FileOutputStream(file);
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

    private void fillInterviewsLayout(VerticalLayout interviewsLo) {


        
    }

    private void fillReportsLayout() {
        
    }

    private void fillSettingsLayout() {
        
    }*/
}
