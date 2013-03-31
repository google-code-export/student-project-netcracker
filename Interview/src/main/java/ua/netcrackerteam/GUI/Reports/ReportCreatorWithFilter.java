/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI.Reports;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Table;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import ua.netcrackerteam.DAO.Entities.Interview;
import ua.netcrackerteam.GUI.MainPage;
import ua.netcrackerteam.controller.InterviewerPage;
import ua.netcrackerteam.controller.RegistrationToInterview;
import ua.netcrackerteam.controller.StudentDataShort;
import ua.netcrackerteam.controller.StudentInterview;
import ua.netcrackerteam.controller.StudentPage;

/**
 *
 * @author Klitna Tetiana
 */
public class ReportCreatorWithFilter extends ReportsCreator{
    
    ComboBox checkInterview;
    
    @Override
    public void createReport() {
        
        setLogotip();        
              
        Table table = builder.buildTable();
        table.setWidth("100%");          
        reportsLo.addComponent(table);
        reportsLo.setComponentAlignment(table, Alignment.MIDDLE_CENTER);
    }

    
}
