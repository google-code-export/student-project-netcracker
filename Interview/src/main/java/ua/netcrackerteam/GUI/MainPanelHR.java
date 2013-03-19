/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.*;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.Runo;

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
         
         //Заголовок
         VerticalLayout vertical = new VerticalLayout();
         vertical.setHeight("95px");
         reportsLo.addComponent(vertical);        
         Label labelTitle = new Label(
                 "<div style=\"text-align:center;color:#99CC99;font-weight:bold;font-size:35px;\">" + "Статистика зарегестрированных студентов" + "</div>", Label.CONTENT_XHTML);
         reportsLo.addComponent(vertical);
         Label currentDate = new Label(new java.util.Date().toString());
         vertical.addComponent(labelTitle);  
         vertical.addComponent(currentDate);
         vertical.setComponentAlignment(labelTitle, Alignment.MIDDLE_CENTER);
         vertical.setComponentAlignment(currentDate, Alignment.BOTTOM_RIGHT);
         
         //Панель с отчетом в виде таблицы
         Panel panel = new Panel();
         reportsLo.addComponent(panel);
         
         TypeOfViewReport report = new ReportDynamicsOfIncreaseStudents();
         List reportData = report.dataReport();
         Table table = createTable(new String[]{"Дата собеседования", "Всего", "Зарегестрировано", "Свободно"}, reportData);
         panel.addComponent(table);
         
         
         
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
    private void fillUPPanel(){
       
        CssLayout layout = new CssLayout();
            ComboBox combobox = new ComboBox("Отчеты");
            combobox.setInvalidAllowed(false);
            combobox.setNullSelectionAllowed(false);

            combobox.addItem("Статистика увелечения записанных студентов");
            combobox.addItem("Общие итоги по собеседованиям");
            combobox.addItem("Промежуточные итоги по собеседованиям");
            combobox.addItem("Аналитика по зарегестрированным студентам");
            combobox.addItem("Список абитуриентов на заданное собеседование");
            combobox.addItem("Эффективность видов рекламы");
        layout.addComponent(combobox);
        Button bSave = new Button("Экспорт/Печать");
        bSave.setIcon(new ThemeResource("icons/32/save.png"));
      
       
        
    }

    
    private class ReportMenuBar extends MenuBar{
        private ReportMenuBar(){        
            
            MenuItem reports = this.addItem("Отчеты", null, null);
            
                MenuItem reportDynamicsIncreaseStudents = reports.addItem("Статистика увелечения записанных студентов", null);
                MenuItem reportAmountRegistrationForm = reports.addItem("Общие итоги по собеседованиям", null);
                MenuItem reportStudentsInterviews = reports.addItem("Итоги по собеседованиям", null);
                MenuItem reportAllStudents = reports.addItem("Аналитика по зарегестрированным студентам", null);
                    MenuItem reportStudentsInstitute = reportAllStudents.addItem("по институтам", null);
                    MenuItem reportStudentsInstituteFaculty = reportAllStudents.addItem("по институтам/факультетам", null);
                    MenuItem reportStudentsInstituteFacultyCathedra = reportAllStudents.addItem("по институтам/факультетам/кафедрам", null);
                    MenuItem reportStudentsInstituteCourse = reportAllStudents.addItem("по курсам", null);
               MenuItem reportStudentsInterview = reports.addItem("Список абитуриентов на заданное собеседование ", null);
               MenuItem reportAdvertisingEfficiency = reports.addItem("Эффективность видов рекламы", null);
        }
    }
        /*private class InterviewsTable extends Table{
        
        public String[] NATURAL_COL_ORDER = new String[] {
                "date", "startTime", "endTime", 
                "positionNum", "restOfPositions", "interviewersNum"};
        
        public String[] COL_HEADERS_RUSSIAN = new String[] {
                "Дата", "Время начала", "Время окончания",
                "Количество мест", "Остаток мест", "Количество интервьюеров"};
        
        private InterviewsTable() {
            super();
            List<HRInterview> interviews = HRPage.getInterviewsList();
            BeanItemContainer<HRInterview> bean = new BeanItemContainer(HRInterview.class, interviews);
            setContainerDataSource(bean);
            setWidth("100%");
            setHeight(300,UNITS_PIXELS);
            setSelectable(true);
            setImmediate(true);
            setColumnReorderingAllowed(true);
            setColumnCollapsingAllowed(true);
            setVisibleColumns(NATURAL_COL_ORDER);
            setColumnHeaders(COL_HEADERS_RUSSIAN);
        }
    }*/
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
