/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import ua.netcrackerteam.controller.StudentInterview;

/**
 * Panel for Interviewer view
 * @author Anna Kushnirenko
 */
public class MainPanelInterviewer extends MainPanel{
    private VerticalLayout interviewsLo;
    private Accordion accordion;
    private int height;
    private Embedded pdfForm;
    private final MainPage mainPage;
    
    public MainPanelInterviewer(HeaderLayout hlayout,MainPage mainPage) {
        super(hlayout,mainPage);
        this.mainPage = mainPage;
        setContent(getUserLayout(hlayout));
        WebApplicationContext context = (WebApplicationContext) mainPage.getContext();
        WebBrowser webBrowser = context.getBrowser();
        height = webBrowser.getScreenHeight()-250;
        
        final Component c = new VerticalLayout();
        tabSheet.addTab(c,"Собеседования");
        tabSheet.addListener(new TabSheet.SelectedTabChangeListener() {

            @Override
            public void selectedTabChange(SelectedTabChangeEvent event) {
                final TabSheet source = (TabSheet) event.getSource();
                if(source.getSelectedTab() == c) {
                    interviewsLo = new VerticalLayout();
                    interviewsLo.setHeight(height,UNITS_PIXELS);
                    interviewsLo.setWidth("100%");
                    interviewsLo.setMargin(true, false, false, false);
                    fillInterviewsLayout();
                    tabSheet.replaceComponent(c, interviewsLo);
                }
            }
        });
    }

    private void fillInterviewsLayout() {
        HorizontalSplitPanel splitH = new HorizontalSplitPanel();
        splitH.setStyleName(Runo.SPLITPANEL_REDUCED);
        splitH.setSplitPosition(200, Sizeable.UNITS_PIXELS);
        interviewsLo.addComponent(splitH);
        
        Panel sidebar = new Panel("Навигация");
        fillSideBar(sidebar);
        splitH.setFirstComponent(sidebar);
        
        Panel rightPanel = new Panel("Информация");
        fillRightPanel(rightPanel);
        splitH.setSecondComponent(rightPanel);
    }

    private void fillSideBar(Panel sidebar) {
        sidebar.setHeight("100%");
        accordion = new Accordion();
        accordion.setSizeFull();
                
        VerticalLayout searchLayout = new VerticalLayout();
        searchLayout.setSpacing(true);
        searchLayout.setMargin(true);
        TextField searchField = new TextField();
        searchField.setWidth("100%");
        Button searchButton = new Button("Найти");
        searchLayout.addComponent(searchField);
        searchLayout.addComponent(searchButton);
        searchLayout.setComponentAlignment(searchButton, Alignment.TOP_CENTER);
        
        accordion.addTab(getTreeMenu(), "Списки");
        accordion.addTab(searchLayout, "Быстрый поиск");
        sidebar.setContent(accordion);
    }

    private void fillRightPanel(Panel rightPanel) {
        rightPanel.setHeight("100%");
        VerticalLayout vl = (VerticalLayout) rightPanel.getContent();
        vl.setMargin(false);
        pdfForm = new Embedded();
        pdfForm.setWidth("600");
        pdfForm.setHeight(height-10,UNITS_PIXELS);
        StreamResource resource = new StreamResource(new PdfStreamSource(), "form.pdf", mainPage);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String filename = "form-" + df.format(new Date()) + ".pdf";
        resource.setFilename(filename);
        resource.setCacheTime(0);
        pdfForm.setType(Embedded.TYPE_BROWSER);
        pdfForm.setSource(resource);
        pdfForm.setMimeType("application/pdf");
        pdfForm.requestRepaint();
        rightPanel.addComponent(pdfForm);
    }

    private Tree getTreeMenu() {
        List<StudentInterview> interviews = ua.netcrackerteam.controller.RegistrationToInterview.getInterviews();
        Tree tree = new Tree();
        String caption = "Дата собеседования";
        tree.addItem(caption);
        tree.setItemIcon(caption,new ThemeResource("icons/32/calendar.png"));
        tree.setChildrenAllowed(caption, true);
        tree.expandItem(caption);
        for(StudentInterview stInterview : interviews) {
            Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");      
            String strDate = formatter.format(stInterview.getInterviewStartDate());
            tree.addItem(stInterview);
            tree.setItemCaption(stInterview,strDate);
            tree.setParent(stInterview, "Дата собеседования");
            
            String withMark = "С оценкой";
            String withoutMark = "Без оценки";
            tree.addItem(strDate+withMark);
            tree.setItemCaption(strDate+withMark, withMark);
            tree.addItem(strDate+withoutMark);
            tree.setItemCaption(strDate+withoutMark, withoutMark);
            
            tree.setParent(strDate+withMark,stInterview);
            tree.setParent(strDate+withoutMark,stInterview);
        }
        return tree;
    }
    
    public class PdfStreamSource implements StreamResource.StreamSource {
            
        @Override
        public InputStream getStream() {
            return new ByteArrayInputStream(new ua.netcrackerteam.applicationForm.ApplicationForm(0).pdfForView());
        }
    }

}
