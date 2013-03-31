/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI.Reports.TypeReports;

import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import ua.netcrackerteam.GUI.Reports.ReportBuilder;

/**
 *
 * @author home
 */
public class ReportBuilderStudentsToInterview extends ReportBuilder{

    @Override
    public Label buildTitle() {
        return report.getTitle("Список абитуриентов на заданное собеседование");
    }

    @Override
    public Table buildTable() {
        return report.getTable(new String[]{"№", "Фамилия", "Имя", "ВУЗ", "Телефон"});
    }

    @Override
    public Embedded buildChart() {
        return new Embedded();
    }
    
}
