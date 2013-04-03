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
        return null;
    }

    @Override
    public Embedded buildChart() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public GridLayout buildGrid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
