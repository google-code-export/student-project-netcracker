/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI.Reports.TypeReports;

import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import ua.netcrackerteam.GUI.Reports.ReportBuilder;

/**
 *
 * @author Klitna Tetiana
 */
public class ReportBuilderAmountStudentsInstutute extends ReportBuilder{

    @Override
    public Label buildTitle() {
        return report.getTitle("Количество абитуриентов по институтам");
    }

    @Override
    public Table buildTable() {
        return report.getTable(new String[]{"Институт", "Пришедшие", "Не пришедшие", "Всего"});
    }

    @Override
    public Embedded buildChart() {
        return new Embedded();
    }

    @Override
    public GridLayout buildGrid() {
       return new GridLayout();
    }
    
}
