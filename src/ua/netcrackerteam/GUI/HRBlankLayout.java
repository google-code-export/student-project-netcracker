package ua.netcrackerteam.GUI;

import java.util.List;

import ua.netcrackerteam.GUI.hr.FormsTable;
import ua.netcrackerteam.GUI.hr.FormsTable.DataLoader;
import ua.netcrackerteam.GUI.hr.NavigationPanel.State;
import ua.netcrackerteam.GUI.hr.NavigationPanel;
import ua.netcrackerteam.GUI.hr.ResultsTable;
import ua.netcrackerteam.controller.HRPage;
import ua.netcrackerteam.controller.bean.StudentDataShort;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Runo;

@SuppressWarnings("serial")
public class HRBlankLayout extends HorizontalSplitPanel {
	
	private Panel mainPanel = new Panel("Информация");
	private final String username;
	private DataLoader dataLoader = new DataLoader(){

		@Override
		public List<StudentDataShort> getData(State state) {
			if (state == State.VALIDATED) {
				mainPanel.addComponent(resultsTable);
				return HRPage.getAllForms();
			}
			
			if (state == State.NOT_APPLIED) {
				mainPanel.removeComponent(resultsTable);
				return HRPage.getBlankWithoutInterview();
			}
			
			if (state == State.NOT_CHECKED) {
				mainPanel.removeComponent(resultsTable);
				return HRPage.getNonVerificatedForms();
			}
			
			return null;
		}};
		
	private State currentState;
	private FormsTable formTable = new FormsTable(dataLoader);
	private NavigationPanel navPanel = new NavigationPanel(formTable);
	private ResultsTable resultsTable;


	public HRBlankLayout(String userName) {
		this.username = userName;
		resultsTable = new ResultsTable(formTable, username);
		initialize();
	}
	
	private void initialize() {
		setStyleName(Runo.SPLITPANEL_REDUCED);
		setSplitPosition(200, Sizeable.UNITS_PIXELS);
		
		mainPanel.addComponent(formTable);
		mainPanel.addComponent(resultsTable);
		
		addComponent(navPanel);
		addComponent(mainPanel);
		
		initButtons();
	}
	
	private void initButtons() {

		navPanel.setDeleteButtonListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (formTable.getCurrentValue() != null) {
					StudentDataShort selectedValue = formTable.getCurrentValue();
					showPopup(selectedValue);
				} else {
					showNotification("Ошибка!", "Перед удалением выберите анкету из списка!");
				}
			}
		});

		navPanel.setEditButtonListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (formTable.getCurrentValue() != null) {
					final Window window = new Window("Window");
					StudentDataShort selectedValue = formTable.getCurrentValue();
					StudentBlank studentBlank = new StudentBlank(HRPage.getUserNameByFormId(selectedValue.getIdForm()), MainPage.getInstance(), username);
					window.setCaption("Просмотр анкеты");
					window.setContent(studentBlank);
					window.setHeight("80%");
					window.setWidth("80%");
					getWindow().addWindow(window);
				} else {
					showNotification("Ошибка!", "Перед редактированием выберите анкету из списка!");
				}
			}
		});

		navPanel.setApproveButtonListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (formTable.getCurrentValue() != null) {
					StudentDataShort selectedValue = formTable.getCurrentValue();
					HRPage.verificateForm(selectedValue.getIdForm());
					showNotification("Анкета подтверждена!", "");
					formTable.setCurrentState(State.VALIDATED);;
				} else {
					showNotification("Ошибка!", "Перед подтверждением выберите анкету из списка!");
				}
			}
		});
		
		navPanel.setSearchButtonListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				String criteria = navPanel.getSearchCriteria();
				String input = navPanel.getSearchInput();
				if (!input.equals("")) {
					List<StudentDataShort> data = HRPage.searchStudents(criteria, input);
					formTable.setCurrentState(State.VALIDATED);
				}
			}
		});
		

	}
	
	/*private void fillTable(Object id) {
		List<StudentDataShort> data = null;
		if (id == null || id.equals("1") ) {
			data = HRPage.getAllForms();
			navPanel.disableApproveButton(true);
			navPanel.disableEditButton(false);
			markTextArea.setEnabled(false);
			markSaveButton.setEnabled(false);
		} else if (id.equals("2")) {
			data = HRPage.getNonVerificatedForms();
			navPanel.disableApproveButton(false);
			navPanel.disableEditButton(true);
			markTextArea.setEnabled(false);
			markSaveButton.setEnabled(false);
			markTextArea.setEnabled(false);
		} else if (id.equals("3")) {
			data = HRPage.getBlankWithoutInterview();
			navPanel.disableApproveButton(true);
			navPanel.disableEditButton(true);
			markTextArea.setEnabled(false);
			markSaveButton.setEnabled(false);
			markTextArea.setEnabled(false);
		}
	}*/
	
	private void showNotification(String header, String message) {
		getWindow().showNotification(header, message, Window.Notification.TYPE_TRAY_NOTIFICATION);
	}
	
	private void showPopup(final StudentDataShort data) {
		
		GridLayout layout = new GridLayout(2,1);
		Button ok = new Button("OK");
		Button cancel = new Button("Отмена");
		final Window popupView = new Window("Подтвердите удаление", layout);
		layout.addComponent(ok);
		layout.addComponent(cancel);
		popupView.center();
		popupView.setResizable(false);
		
		ok.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				HRPage.deleteStudentBlank(data.getIdForm());
				showNotification("Анкета удалена!", "");
				formTable.refresh();
				popupView.setVisible(false);
			}
		});
		cancel.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				popupView.setVisible(false);
			}
		});
		getWindow().addWindow(popupView);
	}
}
