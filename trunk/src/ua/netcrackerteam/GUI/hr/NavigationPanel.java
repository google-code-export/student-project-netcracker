package ua.netcrackerteam.GUI.hr;

import java.util.Arrays;
import java.util.List;

import ua.netcrackerteam.controller.HRPage;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.themes.Runo;

@SuppressWarnings("serial")
public class NavigationPanel extends Panel {
	
	private Accordion sidebarContent = new Accordion();
	private GridLayout applicationFormsLayout = new GridLayout(1, 1);
	private GridLayout searchLayout = new GridLayout(1, 1);
	private Tree formCategories = new Tree();
	private Button deleteButton = new Button("Удалить");
	private Button editButton = new Button("Редактировать");
	private Button verifyButton = new Button("Подтвердить");
	private Button searchButton = new Button("Поиск");
	private TextField searchField = new TextField("Найти...");
	private NativeSelect searchCriteria = new NativeSelect("Критерии поиска:");
	private FormsTable table;
	
	
	final String width = "150";
	final String STYLE = Runo.BUTTON_LINK;
	
	private final List<String> CATEGORIES = Arrays.asList(new String[] {
			"Фамилия", "Имя", "Номер анкеты", "ВУЗ", "Курс", "Факультет",
			"Кафедра" });
	
	public NavigationPanel(FormsTable table) {
		super("Навигация");
		this.table = table;
		initialize();
	}
	
	public enum State {
		VALIDATED, NOT_CHECKED
	}
	
	private void initialize() {

		deleteButton.setWidth(width);
		deleteButton.setIcon(new ThemeResource("icons/32/document-delete.png"));
		deleteButton.setStyleName(STYLE);
		editButton.setWidth(width);
		editButton.setIcon(new ThemeResource("icons/32/document-edit.png"));
		editButton.setStyleName(STYLE);
		verifyButton.setWidth(width);
		verifyButton.setIcon(new ThemeResource("icons/32/Checkbox-Full-icon.png"));
		verifyButton.setStyleName(STYLE);
		verifyButton.setEnabled(false);
		
		setHeight("100%");
		applicationFormsLayout.setSpacing(true);
		searchLayout.setSpacing(true);
		sidebarContent.setSizeFull();
		
		searchCriteria.setNullSelectionAllowed(false);
		searchCriteria.setValue(CATEGORIES.get(0));
		searchCriteria.setImmediate(true);
		
		searchField.setImmediate(true);
		searchField.setRequired(true);
		
		for (String currCategory : CATEGORIES) {
			searchCriteria.addItem(currCategory);
		}
		
		refreshCount();
		
		formCategories.setNullSelectionAllowed(false);
	
		formCategories.addListener(new ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent event) {
				table.setCurrentState((State)event.getItemId());
				enableButtons((State)event.getItemId());
				refreshCount();
				formCategories.select((State)event.getItemId());
			}
		});
		
		applicationFormsLayout.addComponent(formCategories);
		applicationFormsLayout.addComponent(editButton);
		applicationFormsLayout.addComponent(verifyButton);
		applicationFormsLayout.addComponent(deleteButton);
		
		searchLayout.addComponent(searchCriteria);
		searchLayout.addComponent(searchField);
		searchLayout.addComponent(searchButton);
		
		sidebarContent.addTab(applicationFormsLayout, "Анкеты");
		sidebarContent.addTab(searchLayout, "Поиск");
		
		addComponent(sidebarContent);
	}
	
	public void setEditButtonListener(ClickListener listener) {
		editButton.addListener(listener);
	}
	
	public void setDeleteButtonListener(ClickListener clickListener) {
		deleteButton.addListener(clickListener);
	}
	
	public void setApproveButtonListener(ClickListener listener) {
		verifyButton.addListener(listener);
	}
	
	public void setSearchButtonListener(ClickListener listener) {
		searchButton.addListener(listener);
	}
	
	public void disableEditButton(boolean disabled) {
		editButton.setEnabled(disabled);
	}
	
	public void disableDeleteButton(boolean disabled) {
		deleteButton.setEnabled(disabled);
	}
	
	public void disableApproveButton(boolean disabled) {
		verifyButton.setEnabled(disabled);
	}
	
	public String getSearchCriteria() {
		return (String) searchCriteria.getValue();
	}
	
	public String getSearchInput() {
		return (String) searchField.getValue();
	}
	
	public void enableButtons(State state) {
		if (state == State.VALIDATED) {
			editButton.setEnabled(true);
			deleteButton.setEnabled(true);
			verifyButton.setEnabled(false);
		}
		
		if (state == State.NOT_CHECKED) {
			editButton.setEnabled(true);
			deleteButton.setEnabled(true);
			verifyButton.setEnabled(true);
		}
	}
	
	private void refreshCount() {
		Long formCount = HRPage.getCountOfAllForms();
		Long notVerificatedCount = HRPage.getCountOfNonVerificatedForms();
		
		String allFormsTitle = "Все (" + String.valueOf(formCount) + ")";
		String notVerifiedFormsTitle = "Не подтвержденные ("+ String.valueOf(notVerificatedCount) + ")";
		
		formCategories.addItem(State.VALIDATED);
		formCategories.addItem(State.NOT_CHECKED);
		formCategories.setChildrenAllowed(allFormsTitle, false);	
		formCategories.setChildrenAllowed(notVerifiedFormsTitle, false);
		
		formCategories.setItemCaption(State.VALIDATED, allFormsTitle);
		formCategories.setItemCaption(State.NOT_CHECKED, notVerifiedFormsTitle);
		
	}
}
