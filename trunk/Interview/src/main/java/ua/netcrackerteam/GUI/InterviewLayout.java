/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.text.DateFormatSymbols;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import ua.netcrackerteam.applicationForm.CreateLetterWithPDF;
import ua.netcrackerteam.controller.StudentInterview;

/**
 *
 * @author Anna Kushnirenko
 */
class InterviewLayout extends VerticalLayout implements Property.ValueChangeListener {
    
    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols(){
            @Override
            public String[] getMonths() {
                return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
            }
        };
    private final InlineDateField calendar;
    private final OptionGroup dates;
    private final Button saveEdit;
    private final Button print;
    private String userName;
    private int selectedInterviewID;
    
   private ua.netcrackerteam.controller.RegistrationToInterview registration = new ua.netcrackerteam.controller.RegistrationToInterview();

    public InterviewLayout(String username, MainPage mainPage) {
        this.userName = username;
        WebApplicationContext context = (WebApplicationContext) mainPage.getContext();
        WebBrowser webBrowser = context.getBrowser();
        setHeight(webBrowser.getScreenHeight()-200,UNITS_PIXELS);
        setMargin(true);
        setSpacing(true);
        Panel panel = new Panel("Выберите дату собеседования");
        panel.setWidth("100%");
        addComponent(panel);
        GridLayout layout = new GridLayout(2,2);
        layout.setSizeFull();
        panel.setContent(layout);
        layout.setSpacing(true);
        layout.setMargin(true);
        calendar = new InlineDateField();
        calendar.setImmediate(true);
        calendar.setValue(new Date());
        calendar.setResolution(InlineDateField.RESOLUTION_DAY);
        layout.addComponent(calendar,0,0);
        layout.setComponentAlignment(calendar, Alignment.TOP_CENTER);       
        List<StudentInterview> interviews = registration.getInterviews();
        int selectedInterview = registration.getInterview(userName);
        dates = new OptionGroup("Доступные даты:");
        dates.setRequired(true);
        layout.addComponent(dates,1,0);
        for(StudentInterview stInterview : interviews) {
            String strDate = getStrFromDate(stInterview.getInterviewStartDate(), 
                    stInterview.getInterviewEndDate(), stInterview.getRestOfPositions());
            dates.addItem(stInterview);
            dates.setItemCaption(stInterview, strDate);
            if (stInterview.getRestOfPositions() == 0) {
                dates.setItemEnabled(stInterview, false);
            }
            if(selectedInterview != 0) {
                if(selectedInterview == stInterview.getStudentInterviewId()) {
                    dates.setValue(stInterview);
                    calendar.setValue(stInterview.getInterviewStartDate());
                }
            }
        }
        dates.addListener(this);
        dates.setImmediate(true);
        print = new Button("Отправить PDF");
        print.setWidth("150");
        layout.addComponent(print,1,1);
        layout.setComponentAlignment(print, Alignment.TOP_CENTER);
        print.addListener(new ButtonsListener());
        if(selectedInterview != 0) {
            saveEdit = new Button("Редактировать");
            dates.setReadOnly(true);
        } else {
            saveEdit = new Button("Сохранить");
            print.setVisible(false);
        }
        layout.addComponent(saveEdit,0,1);
        saveEdit.setWidth("150");
        layout.setComponentAlignment(saveEdit, Alignment.TOP_CENTER);
        saveEdit.addListener(new ButtonsListener());
        
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        StudentInterview itemID = (StudentInterview) event.getProperty().getValue();
        selectedInterviewID = itemID.getStudentInterviewId();
        calendar.setValue(itemID.getInterviewStartDate());
    }

    private String getStrFromDate(Date startDate, Date endDate, int availability) {
        Format formatter = new SimpleDateFormat("dd MMMM HH:mm",myDateFormatSymbols);
        String strDate = formatter.format(startDate);
        formatter = new SimpleDateFormat("-HH:mm");
        strDate = strDate + formatter.format(endDate);
        strDate = strDate + ". Осталось мест: " + availability;
        return strDate;
    }
    
    private class ButtonsListener implements Button.ClickListener {

        @Override
        public void buttonClick(ClickEvent event) {
            Button source = event.getButton();
            if(source == saveEdit) {
                if(dates.isValid() && saveEdit.getCaption().equals("Сохранить")) {
                registration.updateRegistrationToInterview(userName, selectedInterviewID);
                dates.setReadOnly(true);
                print.setVisible(true);
                saveEdit.setCaption("Редактировать");
                } else {
                    print.setVisible(false);
                    dates.setReadOnly(false);
                    saveEdit.setCaption("Сохранить");
                }
            } else {
                CreateLetterWithPDF letter =new CreateLetterWithPDF(userName);
                letter.sendPDFToStudent();
            }
        }
    }
    
    
}
