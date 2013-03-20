/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.*;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.Runo;
import java.awt.Image;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import ua.netcrackerteam.applicationForm.Reports.Report;
import ua.netcrackerteam.applicationForm.Reports.ReportAdvertisingEfficiency;
import ua.netcrackerteam.applicationForm.Reports.ReportDynamicsOfIncreaseStudents;
import ua.netcrackerteam.applicationForm.Reports.TypeOfViewReport;
import ua.netcrackerteam.controller.HRInterview;
import ua.netcrackerteam.controller.HRPage;

/**
 * Panel for HR view
 * @author Anna Kushnirenko, Filipenko
 */
public class MainPanelHR extends MainPanel{
    private VerticalLayout settingsLo;
    private VerticalLayout blanksLo;
    private VerticalLayout interviewsLo;
    private VerticalLayout reportsLo;
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
        final Component c1 = new VerticalLayout();
        tabSheet.addTab(c1,"Собеседования");
        final Component c2 = new VerticalLayout();
        tabSheet.addTab(c2,"Статистика");
        settingsLo = new VerticalLayout();
        tabSheet.addTab(settingsLo,"Настройки");
        tabSheet.addListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                final TabSheet source = (TabSheet) event.getSource();
                if(source.getSelectedTab() == blanksLo) {
                    HRBlankLayout blankLayout = new HRBlankLayout(hlayout.getUsername(), mainPage);
                    blankLayout.setHeight(height,UNITS_PIXELS);
                    blankLayout.setWidth("100%");
                    blankLayout.setMargin(true, false, false, false);
                    source.replaceComponent(blanksLo, blankLayout);
                }  else if (source.getSelectedTab() == c1) {
                    interviewsLo = new HRInterviewsLayout(height);
                    source.replaceComponent(c1, interviewsLo);
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
    
    
    private void fillReportsLayout() {
      
         //Тип отчета              
         ComboBox cb = new ComboBox("Выберите отчет:");
         cb.setInvalidAllowed(false);
         cb.setNullSelectionAllowed(false);
         IndexedContainer container = new IndexedContainer();        
         container.addItem("Статистика зарегестрированных студентов");
         container.addItem("Общие итоги по собеседованиям");
         container.addItem("Аналитика по зарегестрированным студентам");     
         container.addItem("Список абитуриентов на заданное собеседование"); 
         container.addItem("Эффективность видов рекламы"); 
         cb.setContainerDataSource(container);
         cb.setNullSelectionAllowed(false);
         cb.setValue(cb.getItemIds().iterator().next());
                       
         //Заголовок
         Label labelTitle = new Label(
                 "<div style=\"text-align:center;color:#99CC99;font-weight:bold;font-size:30px;\">" + cb.getValue().toString()  + "</div>", Label.CONTENT_XHTML);
         labelTitle.setHeight("50px");
       
         Label currentDate = new Label(new java.util.Date().toString());
                
         //таблица отчета                  
         TypeOfViewReport report = new ReportDynamicsOfIncreaseStudents();
         List reportData = report.dataReport();
         Table table = createTable(new String[]{"Дата собеседования", "Всего", "Зарегестрировано", "Свободно"}, reportData);
                  
         //График
         StreamResource img = new StreamResource(new MyImageSource(report), "myimage.png", mainPage);
         Embedded emb = new Embedded("", img);         
                 
         reportsLo.addComponent(cb);
         reportsLo.addComponent(labelTitle);  
         reportsLo.addComponent(currentDate);
         reportsLo.addComponent(table);
         reportsLo.addComponent(emb);
       
         reportsLo.setComponentAlignment(cb, Alignment.TOP_RIGHT);
         reportsLo.setComponentAlignment(labelTitle, Alignment.BOTTOM_CENTER);
         
         reportsLo.setComponentAlignment(currentDate, Alignment.MIDDLE_RIGHT);
         reportsLo.setComponentAlignment(table, Alignment.MIDDLE_CENTER);
         reportsLo.setComponentAlignment(emb, Alignment.MIDDLE_CENTER);
  } 
    
    public class MyImageSource   implements StreamResource.StreamSource {
        TypeOfViewReport report;
        public MyImageSource(TypeOfViewReport report){
          this.report = report;  
        }
        public InputStream getStream() {      
            return new ByteArrayInputStream(report.getChart(400, 400));
        }
        
    }
    
    private Table createTable(String[] headerTable, List report){
        
        final Table table = new Table();
        for(int i = 0; i < headerTable.length; i++){
            table.addContainerProperty(headerTable[i], String.class, null);
        }
  
        ListIterator iterator = report.listIterator();
        for(int i = 0; iterator.hasNext(); i++){
            table.addItem((Object[])iterator.next(), i);
        }

        table.setPageLength(table.size());

        table.setSelectable(false);
        table.setNullSelectionAllowed(false);
        table.setImmediate(true);
        
        return table;
    }        
    private class PdfStreamSource implements StreamResource.StreamSource {
            
        @Override
        public InputStream getStream() {
            TypeOfViewReport report =new ReportAdvertisingEfficiency();
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
