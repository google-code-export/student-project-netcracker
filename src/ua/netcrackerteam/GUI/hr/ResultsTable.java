package ua.netcrackerteam.GUI.hr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.netcrackerteam.controller.HRPage;
import ua.netcrackerteam.controller.bean.StudentsMarks;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class ResultsTable extends Panel {
	
	private static final Object[] NATURAL_COL_ORDER = new Object[] {"interviewerName",
			"javaKnowledge", "sqlKnowledge", "groupWork", "enrollment" };
	private static final String[] COL_HEADERS_RUSSIAN = new String[] {"Интервьюер", "Java", "SQL", "Работа в команде", "Общая оценка" };
	
	private FormsTable formsTable;
	private String username;
	private Table table = new Table();
	private Label markLabel = new Label("Введите оценку");
	private TextArea markTextArea = new TextArea();
	private Button markSaveButton = new Button("Сохранить");
	
	private Map<Integer, List<StudentsMarks>> results = new HashMap<Integer, List<StudentsMarks>>();
	
	public ResultsTable(FormsTable table, String username) {
		super();
		this.table.setContainerDataSource(new BeanItemContainer<StudentsMarks>(StudentsMarks.class));
		formsTable = table;
		this.username = username;
		initialize();
	}
	
	private void initialize() {
		
		table.setWidth("100%");
		table.setHeight("100");
		table.setSelectable(false);
		table.setImmediate(true);
		table.setColumnReorderingAllowed(true);
		table.setColumnCollapsingAllowed(true);
		table.setVisibleColumns(NATURAL_COL_ORDER);
		table.setColumnHeaders(COL_HEADERS_RUSSIAN);
		
		markTextArea.setRows(3);
		markTextArea.setColumns(40);
		markTextArea.setRequired(true);
		markSaveButton.setWidth("150");
		
		enableButtons(false);
		
		markSaveButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				int selectedFormId = formsTable.getCurrentValue().getIdForm();
				if (!markTextArea.getValue().equals("") && selectedFormId > 0) {
					HRPage.setStudentMark(selectedFormId, username, markTextArea.getValue().toString());
					getWindow().showNotification("Оценка выставлена!", "", 
							Window.Notification.TYPE_TRAY_NOTIFICATION);
					fillResults(selectedFormId);
				} else {
					getWindow().showNotification("Ошибка!", "Перед сохранением выберите анкету из списка и введите оценку!", 
							Window.Notification.TYPE_TRAY_NOTIFICATION);
				}
			}
		});
		
		addComponent(table);
		addComponent(markLabel);
		addComponent(markTextArea);
		addComponent(markSaveButton);

	}
	
	public void fillResults(int formId) {
		if (results.containsKey(formId)) {
			fillResultsTable(results.get(formId));
		} else {
			List<StudentsMarks> currMarks = HRPage.getStudentMark(formId);
			results.put(formId, currMarks);
			fillResultsTable(currMarks);
		}
	}
	
	private void fillResultsTable(List<StudentsMarks> marks) {
		table.setContainerDataSource(new BeanItemContainer<StudentsMarks>(StudentsMarks.class, marks));
		enableButtons(true);
	}
	
	public void enableButtons(boolean isMarkPresent) {
		if (isMarkPresent) {
			markSaveButton.setEnabled(false);
			markTextArea.setEnabled(false);
		} else {
			markSaveButton.setEnabled(true);
			markTextArea.setEnabled(true);
		}
	}
}
