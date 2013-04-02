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
 * @author Klitna Tetiana
 */
public class ReportBuilderResultOfInterviews extends ReportBuilder{

    @Override
    public Label buildTitle() {
        return report.getTitle("Общие итоги по собеседованиям");
    }

    @Override
    public Table buildTable() {
        return report.getTable(new String[]{"Всего анкет", "Всего на собеседования", "Прошло собеседование"});
    }

    @Override
    public Embedded buildChart() {
        return new Embedded();
    }
    
}
