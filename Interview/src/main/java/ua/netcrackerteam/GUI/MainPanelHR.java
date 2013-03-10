/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Link;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import ua.netcrackerteam.applicationForm.ReportAmountRegistrationForms;
import ua.netcrackerteam.applicationForm.TypeOfViewReport;

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
    private final MainPage mainPage;
    
    public MainPanelHR(final HeaderLayout hlayout, final MainPage mainPage) {
        super(hlayout,mainPage);
        this.mainPage = mainPage;
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
        final Component c2 = new VerticalLayout();
        tabSheet.addTab(c2,"Статистика");
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
                }  else if (source.getSelectedTab() == c2) {
                     reportsLo = new VerticalLayout();
                     fillReportsLayout();
                     source.replaceComponent(c2, reportsLo);
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

    

    private void fillSettingsLayout() {
        
    }*/
    
    ///// Test component for Tanya
    
    private void fillReportsLayout() {
        Link pdfLink = getPDFLink();
        reportsLo.addComponent(pdfLink);
    }
    private class PdfStreamSource implements StreamResource.StreamSource {
            
        @Override
        public InputStream getStream() {
            TypeOfViewReport report = ReportAmountRegistrationForms.getInstance();
            return new ByteArrayInputStream(report.viewReport());
        } 
    }
    private Link getPDFLink() {
        StreamResource resource = new StreamResource(new PdfStreamSource(), "form.pdf", mainPage);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String filename = "form-" + df.format(new Date()) + ".pdf";
        resource.setFilename(filename);
        resource.setCacheTime(0);
        Link pdfLink = new Link("Тест",resource);
        pdfLink.setTargetName("_blank");
        pdfLink.setTargetWidth(600);
        pdfLink.setTargetHeight(800);
        pdfLink.setTargetBorder(Link.TARGET_BORDER_NONE);
        pdfLink.setDescription("Временно");
        ThemeResource icon = new ThemeResource("icons/32/document-pdf.png");
        pdfLink.setIcon(icon);
        return pdfLink;
    }
}
