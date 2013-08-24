package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;
import ua.netcrackerteam.DAO.Entities.EnrollmentScores;
import ua.netcrackerteam.controller.HRPage;
import ua.netcrackerteam.controller.bean.StudentsMarks;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Miralissa
 * Date: 24.08.13
 * Time: 19:06
 * To change this template use File | Settings | File Templates.
 */
public class MarksLayout extends VerticalLayout {
    private BeanItem<StudentsMarks> studentsMarksBeanItem;
    private MarksMode marksMode;
    private HorizontalLayout dropDownsLayout = new HorizontalLayout();
    private HRPage controller = new HRPage();
    public static enum MarksMode {
        HR, INTERVIEWER
    }

    public MarksLayout(StudentsMarks studentsMarks, MarksMode mode) {
        this.marksMode = mode;
        studentsMarksBeanItem = new BeanItem<StudentsMarks>(studentsMarks);
        setSpacing(true);
        setMargin(true);
        setWidth(100, UNITS_PERCENTAGE);
        addHeader();
        dropDownsLayout.setSpacing(true);
        addComponent(dropDownsLayout);
        addEnrollmentDropDown();
        addCommandWorkDropDown();
        if(mode.equals(marksMode.INTERVIEWER)) {
            addJavaKnowledgeField();
            addSQLKnowledgeField(); 
        }
        addCommentField();
    }

    private void addHeader() {
        Label header = new Label();
        if(marksMode.equals(marksMode.HR)) {
            header.setCaption("Оценка HR:");
        }  else {
            header.setCaption("Оценка технического интервьюера:");
        }
        addComponent(header);
    }

    private void addCommentField() {
        TextArea commentField = new TextArea();
        commentField.setWidth(100,UNITS_PERCENTAGE);
        commentField.setRows(3);
        commentField.setRequired(true);
        if(marksMode.equals(MarksMode.HR)){
            commentField.setCaption("Общие впечатления");
        } else {
            commentField.setCaption("Другие впечатления");
        }
        commentField.setPropertyDataSource((Property) studentsMarksBeanItem.getItemProperty("comment"));
        addComponent(commentField);
    }

    private void addSQLKnowledgeField() {
        TextArea sqlField = new TextArea("Знание SQL");
        sqlField.setRows(3);
        sqlField.setRequired(true);
        sqlField.setWidth(100,UNITS_PERCENTAGE);
        sqlField.setPropertyDataSource((Property) studentsMarksBeanItem.getItemProperty("sqlKnowledge"));
        addComponent(sqlField);
    }

    private void addJavaKnowledgeField() {
        TextArea javaField = new TextArea("Знания Java");
        javaField.setRows(3);
        javaField.setRequired(true);
        javaField.setWidth(100,UNITS_PERCENTAGE);
        javaField.setPropertyDataSource((Property) studentsMarksBeanItem.getItemProperty("javaKnowledge"));
        addComponent(javaField);
    }

    private void addCommandWorkDropDown() {
        ComboBox commandWork = new ComboBox("Готов ли к работе в команде");
        commandWork.setWidth(300,UNITS_PIXELS);
        commandWork.setRequired(true);
        commandWork.setPropertyDataSource((Property) studentsMarksBeanItem.getItemProperty("groupWork"));
        commandWork.setNullSelectionAllowed(false);

        boolean yesItem = true;
        boolean noItem = false;
        commandWork.addItem(yesItem);
        commandWork.addItem(noItem);
        commandWork.setItemCaption(yesItem,"Да");
        commandWork.setItemCaption(noItem,"Нет");
        dropDownsLayout.addComponent(commandWork);
    }

    private void addEnrollmentDropDown() {
        ComboBox enrollmentScores = new ComboBox("Зачисление");
        enrollmentScores.setWidth(300,UNITS_PIXELS);
        enrollmentScores.setRequired(true);
        enrollmentScores.setPropertyDataSource((Property) studentsMarksBeanItem.getItemProperty("enrollment"));
        enrollmentScores.setNullSelectionAllowed(false);
        List<EnrollmentScores> scoresList = controller.getEnrollmentScores();
        BeanItemContainer<EnrollmentScores> objects = new BeanItemContainer<EnrollmentScores>(EnrollmentScores.class, scoresList);
        enrollmentScores.setContainerDataSource(objects);
        dropDownsLayout.addComponent(enrollmentScores);
    }

}
