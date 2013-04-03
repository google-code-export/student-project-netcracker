/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import ua.netcrackerteam.GUI.Reports.ReportBuilder;
import ua.netcrackerteam.GUI.Reports.TypeReports.ReportBuilderAdvertisingEfficiency;
import ua.netcrackerteam.GUI.Reports.TypeReports.ReportBuilderDynamicsOfIncreaseStudents;
import ua.netcrackerteam.GUI.Reports.ReportsCreator;
import ua.netcrackerteam.GUI.Reports.TypeReports.ReportBuilderResultOfInterviews;
import ua.netcrackerteam.GUI.Reports.TypeReports.ReportBuilderResultOfInterviewsDetail;
import ua.netcrackerteam.GUI.Reports.TypeReports.ReportBuilderStudentsToInterview;
import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateAdvertisingEfficiency;
import ua.netcrackerteam.applicationForm.Reports.ReportTemplateBuilder;
import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateDynamicsOfIncreaseStudents;
import ua.netcrackerteam.applicationForm.Reports.ReportsTemplateCreator;
import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateResultOfInterviews;
import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateResultOfInterviewsDetail;
import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateStudentsToInterview;

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
     
    ComboBox cbTypeReport;
    String selectReport;
    ReportTemplateBuilder template;
    ReportBuilder builder;
    ReportsCreator creator;
       
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
        
        selectReport = "Статистика увеличения записанных студентов на собеседования";
        template = new ReportTemplateDynamicsOfIncreaseStudents();
        builder = new ReportBuilderDynamicsOfIncreaseStudents(mainPage);
        creator = new ReportsCreator();
        
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
                } else if(source.getSelectedTab() == reportsLo){
                    fillReportsLayout();
                }  else if (source.getSelectedTab() == settingsLo) {
                    settingsLayout = new SettingsLayout(hlayout.getUsername(), mainPage);
                    source.replaceComponent(settingsLo, settingsLayout);
                }
            }
        });
    }
    
    
    private void fillReportsLayout() {
        
          
         reportsLo.removeAllComponents();
                         
         cbTypeReport = getComboBox();
      
         template.createReportPDFTemplate();
         Link pdfLink = getPDFLink(template);
       
         HorizontalLayout horizontal = new HorizontalLayout(); 
         horizontal.setHeight("60px");
         horizontal.addComponent(cbTypeReport); 
         horizontal.addComponent(pdfLink);
         horizontal.setComponentAlignment(pdfLink, Alignment.MIDDLE_CENTER);
         
         reportsLo.addComponent(horizontal); 
              
         //Заполнение отчета            
         builder.createReport(template);        
         
         creator.setReportBuilder(builder);     
         creator.setVerticalLayout(reportsLo);          
         creator.createReport();         
   
  } 
    
  private ComboBox getComboBox(){
      
         cbTypeReport = new ComboBox("Выберите отчет:");
         cbTypeReport.setImmediate(true);
         cbTypeReport.setInvalidAllowed(false);
         cbTypeReport.setNullSelectionAllowed(false);
         
         IndexedContainer container = new IndexedContainer();        
         container.addItem("Статистика увеличения записанных студентов на собеседования");
         container.addItem("Общие итоги по собеседованиям");
         container.addItem("Итоги по собеседованиям");   
         container.addItem("Кол-во абитуриентов по институтам"); 
         container.addItem("Кол-во абитуриентов по институтам-факультетам"); 
         container.addItem("Кол-во абитуриентов по институтам-факультетам-кафедрам"); 
         container.addItem("Кол-во абитуриентов по курсам"); 
         container.addItem("Список абитуриентов на заданное собеседование"); 
         container.addItem("Эффективность видов рекламы");         
        
         cbTypeReport.setContainerDataSource(container);
         cbTypeReport.setNullSelectionAllowed(false);        
        
         cbTypeReport.setValue(selectReport);
      
          Property.ValueChangeListener listener = new Property.ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {                
                selectReport = event.getProperty().getValue().toString(); 
                
                if(selectReport.equals("Статистика увеличения записанных студентов на собеседования")){                     
                     template = new ReportTemplateDynamicsOfIncreaseStudents();                     
                     builder = new ReportBuilderDynamicsOfIncreaseStudents(mainPage);                                 
                } else if(selectReport.equals("Эффективность видов рекламы")){                    
                     template = new ReportTemplateAdvertisingEfficiency();
                     builder = new ReportBuilderAdvertisingEfficiency(mainPage);                                
                } else if(selectReport.equals("Список абитуриентов на заданное собеседование")){
                    template = new ReportTemplateStudentsToInterview();                    
                    builder = new ReportBuilderStudentsToInterview();                   
                                      
                } else if(selectReport.equals("Общие итоги по собеседованиям")){
                    template = new ReportTemplateResultOfInterviews();
                    builder = new ReportBuilderResultOfInterviews();
                }/*else if(selectReport.equals("Кол-во абитуриентов по институтам")){
                    
                }else if(selectReport.equals("Кол-во абитуриентов по институтам-факультетам")){
                    
                }else if(selectReport.equals("Кол-во абитуриентов по институтам-факультетам-кафедрам")){
                    
                }else if(selectReport.equals("Кол-во абитуриентов по курсам")){
                    
                }*/else if(selectReport.equals("Итоги по собеседованиям")){
                   template = new ReportTemplateResultOfInterviewsDetail();
                   builder = new ReportBuilderResultOfInterviewsDetail();
                   creator = new ReportsCreator();
                }
                fillReportsLayout();
            }
        
         };
         cbTypeReport.addListener(listener);
         
         return cbTypeReport;
  }
    private class PdfStreamSource implements StreamResource.StreamSource { 
        
        private ReportTemplateBuilder builder;
        
        public PdfStreamSource(ReportTemplateBuilder builder){
            this.builder = builder;
        }
        
        @Override
        public InputStream getStream() {       
            ReportsTemplateCreator creator = new ReportsTemplateCreator();
            creator.setReportTemplateBuilder(builder);          
            return new ByteArrayInputStream(creator.viewReport());
        } 
    }
    private Link getPDFLink(ReportTemplateBuilder builder) {
        StreamResource resource = new StreamResource(new PdfStreamSource(builder), "Report.pdf", mainPage);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String filename = "Report-" + df.format(new Date()) + ".pdf";
        resource.setFilename(filename);
        resource.setCacheTime(0);
        Link pdfLink = new Link("Save/Print report", resource);
        pdfLink.setTargetName("_blank");
        pdfLink.setTargetWidth(600);
        pdfLink.setTargetHeight(800);
        pdfLink.setTargetBorder(Link.TARGET_BORDER_NONE);
        pdfLink.setDescription("pdf-формат отчета");
        ThemeResource icon = new ThemeResource("icons/32/document-pdf.png");
        pdfLink.setIcon(icon);
        
        return pdfLink;
    }
}
