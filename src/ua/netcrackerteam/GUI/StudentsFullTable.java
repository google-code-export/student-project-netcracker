package ua.netcrackerteam.GUI;

import com.vaadin.data.Container;
import com.vaadin.ui.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Miralissa
 * Date: 26.08.13
 * Time: 15:43
 * To change this template use File | Settings | File Templates.
 */
public class StudentsFullTable extends Table {

    public Object[] NATURAL_COL_ORDER = new Object[]{
            "number2", "surname", "name", "secondName",
            "hr1", "result1", "calculableComment1",
            "hr2", "result2", "calculableComment2",
            "cource", "speciality", "highSchoolName",
            "email1", "email2", "telNumber"};
    public String[] COL_HEADERS_RUSSIAN = new String[]{
            "№ анкеты", "Фамилия", "Имя", "Отчество",
            "Интервьюер 1 (HR)", "Результат 1 (HR)", "Комментарий 1 (HR)",
            "Интервьюер 2 (Dev)", "Результат 2 (Dev)", "Комментарий 2 (Dev)",
            "Курс", "Специальность", "ВУЗ",
            "e-mail", "e-mail 2", "телефон"};

    public StudentsFullTable(Container dataSource, ValueChangeListener listener, boolean isMultiselect) {
        super();
        setWidth("100%");
        setHeight(300, UNITS_PIXELS);
        setSelectable(true);
        setImmediate(true);
        setMultiSelect(isMultiselect);
        setContainerDataSource(dataSource);
        setColumnReorderingAllowed(true);
        setColumnCollapsingAllowed(true);
        setVisibleColumns(NATURAL_COL_ORDER);
        setColumnHeaders(COL_HEADERS_RUSSIAN);
        addListener(listener);
    }

}

