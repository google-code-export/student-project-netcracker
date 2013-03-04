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
        };;
    private final InlineDateField calendar;
    private final OptionGroup dates;
    private final Button saveEdit;
    private String userName;
    private int selectedInterviewID;

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
        List<StudentInterview> interviews = ua.netcrackerteam.controller.RegistrationToInterview.getInterviews();
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
        }
        dates.addListener(this);
        dates.setImmediate(true);
        
        StudentInterview selectedInterview = ua.netcrackerteam.controller.RegistrationToInterview.getInterview(userName);
        if(selectedInterview != null) {
            saveEdit = new Button("Редактировать");
            dates.setReadOnly(true);
            dates.setValue(selectedInterview);
        } else {
            saveEdit = new Button("Сохранить");
        }
        layout.addComponent(saveEdit,0,1);
        layout.setComponentAlignment(saveEdit, Alignment.TOP_CENTER);
        saveEdit.addListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                if(dates.isValid() && saveEdit.getCaption().equals("Сохранить")) {
                    ua.netcrackerteam.controller.RegistrationToInterview.updateRegistrationToInterview(userName, selectedInterviewID);
                    dates.setReadOnly(true);
                    saveEdit.setCaption("Редактировать");
                } else {
                    dates.setReadOnly(false);
                    saveEdit.setCaption("Сохранить");
                }
            }
        });
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
    
}
