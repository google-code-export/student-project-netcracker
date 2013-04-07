/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI.Reports.TypeReports;

import com.vaadin.ui.Accordion;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import ua.netcrackerteam.GUI.Reports.ReportBuilder;

/**
 *
 * @author Klitna Tetiana
 */
public class ReportBuilderAmountStudentsFaculty extends ReportBuilder {

    @Override
    public Label buildTitle() {
        return report.getTitle("Количество абитуриентов по институтам-факультетам");
    }

    @Override
    public Table buildTable() {
        return null;
    }

    @Override
    public Embedded buildChart() {
        return null;
    }

    @Override
    public GridLayout buildGrid() {
        return report.getGridView(new String[]{"Факультет", "Пришедшие", "Не пришедшие", "Всего"});
    }
    
}
