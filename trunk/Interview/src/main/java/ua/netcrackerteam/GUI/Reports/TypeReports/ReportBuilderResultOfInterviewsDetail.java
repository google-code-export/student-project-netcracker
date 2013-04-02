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
public class ReportBuilderResultOfInterviewsDetail extends ReportBuilder {

    @Override
    public Label buildTitle() {
        return report.getTitle("Итоги по собеседованиям");
    }

    @Override
    public Table buildTable() {
         return report.getTable(new String[]{"Дата", "Максимум", "Записалось", "Оценено", "Осталось"});
    }

    @Override
    public Embedded buildChart() {
        return new Embedded();
    }
    
}
