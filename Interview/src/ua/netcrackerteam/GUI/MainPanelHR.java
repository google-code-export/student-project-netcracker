/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Runo;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import ua.netcrackerteam.DAO.Entities.Interview;

import ua.netcrackerteam.GUI.Reports.ReportBuilder;
import ua.netcrackerteam.GUI.Reports.TypeReports.ReportBuilderAdvertisingEfficiency;
import ua.netcrackerteam.GUI.Reports.TypeReports.ReportBuilderDynamicsOfIncreaseStudents;
import ua.netcrackerteam.GUI.Reports.ReportsCreator;
import ua.netcrackerteam.GUI.Reports.TypeReports.ReportBuilderAmountStudentsCathedra;
import ua.netcrackerteam.GUI.Reports.TypeReports.ReportBuilderAmountStudentsCourse;
import ua.netcrackerteam.GUI.Reports.TypeReports.ReportBuilderAmountStudentsFaculty;
import ua.netcrackerteam.GUI.Reports.TypeReports.ReportBuilderAmountStudentsInstutute;
import ua.netcrackerteam.GUI.Reports.TypeReports.ReportBuilderResultOfInterviews;
import ua.netcrackerteam.GUI.Reports.TypeReports.ReportBuilderResultOfInterviewsDetail;
import ua.netcrackerteam.GUI.Reports.TypeReports.ReportBuilderStudentsToInterview;
import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateAdvertisingEfficiency;
import ua.netcrackerteam.applicationForm.Reports.ReportTemplateBuilder;
import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateDynamicsOfIncreaseStudents;
import ua.netcrackerteam.applicationForm.Reports.ReportsTemplateCreator;
import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateAmountStudentCathedra;
import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateAmountStudentCourse;
import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateAmountStudentFaculty;
import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateAmountStudentInstitute;
import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateResultOfInterviews;
import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateResultOfInterviewsDetail;
import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateStudentsToInterview;
import ua.netcrackerteam.controller.InterviewerPage;
import ua.netcrackerteam.controller.RegistrationToInterview;
import ua.netcrackerteam.controller.StudentInterview;
import ua.netcrackerteam.controller.StudentPage;

/**
 * Panel for HR view
 * @author Anna Kushnirenko, Filipenko, Klitna Tetiana
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
    ComboBox cbInterview;
    Button   btnRefresh;
    
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
        
        getCombCheckReport();
        getBtnRefresh();
        getCombCheckInterview();
        
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
            
         template.createReportPDFTemplate();
         Link pdfLink = getPDFLink(template);
       
         HorizontalLayout horizontal = new HorizontalLayout(); 
         horizontal.setHeight("60px");        
         horizontal.addComponent(cbTypeReport); 
         horizontal.addComponent(pdfLink);         
         horizontal.setComponentAlignment(pdfLink, Alignment.MIDDLE_CENTER);
         reportsLo.addComponent(horizontal); 
         
         VerticalLayout vertical = new VerticalLayout();
         vertical.addComponent(btnRefresh);
         vertical.setComponentAlignment(btnRefresh, Alignment.MIDDLE_RIGHT);        
         reportsLo.addComponent(vertical); 
         
         if(selectReport.equals("Список абитуриентов на заданное собеседование")){            
            vertical.addComponent(cbInterview);
            vertical.setComponentAlignment(cbInterview, Alignment.MIDDLE_LEFT);        
            reportsLo.addComponent(vertical); }
              
         //Заполнение отчета            
         builder.createReport(template);        
         
         creator.setReportBuilder(builder);     
         creator.setVerticalLayout(reportsLo);          
         creator.createReport();         
   
  } 
    
  private Button getBtnRefresh(){
        btnRefresh = new Button("Обновить");
        btnRefresh.setStyleName(Runo.BUTTON_LINK);
        btnRefresh.setIcon(new ThemeResource("icons/32/reload.png"));
        btnRefresh.addListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                fillReportsLayout();
            }
        });
        
        return btnRefresh;
  }
    
  private void getCombCheckReport(){
      
         cbTypeReport = new ComboBox("Выберите отчет:");
         cbTypeReport.setImmediate(true);
         cbTypeReport.setInvalidAllowed(false);
         cbTypeReport.setNullSelectionAllowed(false);
         
         IndexedContainer container = new IndexedContainer();        
         container.addItem("Статистика увеличения записанных студентов на собеседования");
         container.addItem("Общие итоги по собеседованиям");
         container.addItem("Итоги по собеседованиям");   
         container.addItem("Кол-во абитуриентов по институтам"); 
         container.addItem("Кол-во абитуриентов по факультетам"); 
         container.addItem("Кол-во абитуриентов по кафедрам"); 
         container.addItem("Кол-во абитуриентов по курсам"); 
         container.addItem("Список абитуриентов на заданное собеседование"); 
         container.addItem("Эффективность видов рекламы");         
        
         cbTypeReport.setContainerDataSource(container);        
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
                }else if(selectReport.equals("Кол-во абитуриентов по институтам")){
                    template = new ReportTemplateAmountStudentInstitute();
                    builder = new ReportBuilderAmountStudentsInstutute();
                }else if(selectReport.equals("Кол-во абитуриентов по факультетам")){
                    template = new ReportTemplateAmountStudentFaculty();
                    builder = new ReportBuilderAmountStudentsFaculty();
                }else if(selectReport.equals("Кол-во абитуриентов по кафедрам")){
                   template = new ReportTemplateAmountStudentCathedra();
                   builder = new  ReportBuilderAmountStudentsCathedra();
                }else if(selectReport.equals("Кол-во абитуриентов по курсам")){
                   template = new ReportTemplateAmountStudentCourse();
                   builder = new ReportBuilderAmountStudentsCourse(mainPage);
                }else if(selectReport.equals("Итоги по собеседованиям")){
                   template = new ReportTemplateResultOfInterviewsDetail();
                   builder = new ReportBuilderResultOfInterviewsDetail();
                }
                fillReportsLayout();
            }
        
         };
         cbTypeReport.addListener(listener);
         
  }
  
   private void getCombCheckInterview(){
       
        cbInterview = new ComboBox("Собеседование:");
        cbInterview.setImmediate(true);
        cbInterview.setInvalidAllowed(false);
        cbInterview.setNullSelectionAllowed(true);
         
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
         
         RegistrationToInterview registration = new RegistrationToInterview();
         List<StudentInterview>interviews = registration.getInterviews();         
       
         for(StudentInterview stInterview : interviews) {
            String strDate = formatter.format(stInterview.getInterviewStartDate()); 
            cbInterview.addItem(stInterview);
            cbInterview.setItemCaption(stInterview, strDate);
         }
     
             
          Property.ValueChangeListener listener = new Property.ValueChangeListener() {
           public void valueChange(ValueChangeEvent event) {
                StudentInterview selectedInterview = (StudentInterview) event.getProperty().getValue();
                if(selectedInterview != null){
                    template = new ReportTemplateStudentsToInterview(selectedInterview.getStudentInterviewId()); 
                }else{
                    template = new ReportTemplateStudentsToInterview();
                }                
                builder = new ReportBuilderStudentsToInterview(); 
                fillReportsLayout();                
           }

        
         };
         cbInterview.addListener(listener);
      
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
