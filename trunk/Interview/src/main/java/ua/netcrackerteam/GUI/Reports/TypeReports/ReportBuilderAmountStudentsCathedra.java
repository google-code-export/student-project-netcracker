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
public class ReportBuilderAmountStudentsCathedra extends ReportBuilder {

    @Override
    public Label buildTitle() {
        return report.getTitle("Количество абитуриентов по институтам-факультетам-кафедрам");
    }

    @Override
    public Table buildTable() {
        return null;
    }

    @Override
    public Embedded buildChart() {
        return new Embedded();
    }

    @Override
    public GridLayout buildGrid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
