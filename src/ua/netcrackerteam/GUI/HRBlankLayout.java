package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.StreamResource.StreamSource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Runo;
import ua.netcrackerteam.applicationForm.ApplicationForm;
import ua.netcrackerteam.controller.HRPage;
import ua.netcrackerteam.controller.StudentPage;
import ua.netcrackerteam.controller.bean.StudentData;
import ua.netcrackerteam.controller.bean.StudentDataShort;
import ua.netcrackerteam.controller.bean.StudentsMarks;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class HRBlankLayout extends HorizontalSplitPanel {
	
	private Panel sideBar = new Panel("Навигация");
	private Panel mainPanel = new Panel("Информация");
	private Accordion sidebarContent = new Accordion();
	private GridLayout applicationFormsLayout = new GridLayout(1, 1);
	private GridLayout searchLayout = new GridLayout(1, 1);;
	private Table formTable = createFormsTable(null);
	private Table resultsTable = createResultsTable(null);
	private TextField searchField = new TextField("Найти...");
	private NativeSelect searchCriteria = new NativeSelect("Критерии поиска:");
	private Link pdfLink = new Link();;

	private Tree formCategories = new Tree();

	private HorizontalLayout buttonPanel = new HorizontalLayout();
	private Label markLabel = new Label("Введите оценку");
	private TextArea markTextArea = new TextArea();
	private Button searchButton = new Button("Поиск");
	private Button markSaveButton = new Button("Сохранить");
	private Button photoSaveButton = new Button("Фото");
	private Button showDiffButton = new Button("Различия");
	private Button deleteButton = new Button("Удалить");
	private Button editButton = new Button("Редактировать");
	private Button verifyButton = new Button("Подтвердить");

	private static final List<String> categories = Arrays.asList(new String[] {
			"Фамилия", "Имя", "Номер анкеты", "ВУЗ", "Курс", "Факультет",
			"Кафедра" });
	
	private final String username;
	private MainPage mainPage;

	public HRBlankLayout(String userName, MainPage mainPage) {
		this.username = userName;
		this.mainPage = mainPage;
		initialize();
	}
	
	private void initialize() {
		setStyleName(Runo.SPLITPANEL_REDUCED);
		setSplitPosition(200, Sizeable.UNITS_PIXELS);
		
		initializeComponents();
		
		buttonPanel.addComponent(markSaveButton);
		buttonPanel.addComponent(photoSaveButton);
		buttonPanel.addComponent(showDiffButton);

		
		applicationFormsLayout.addComponent(formCategories);
		applicationFormsLayout.addComponent(editButton);
		applicationFormsLayout.addComponent(verifyButton);
		applicationFormsLayout.addComponent(deleteButton);
		
		searchLayout.addComponent(searchCriteria);
		searchLayout.addComponent(searchField);
		searchLayout.addComponent(searchButton);
		
		sidebarContent.addTab(applicationFormsLayout, "Анкеты");
		sidebarContent.addTab(searchLayout, "Поиск");
		
		sideBar.addComponent(sidebarContent);
		
		mainPanel.addComponent(formTable);
		mainPanel.addComponent(resultsTable);
		mainPanel.addComponent(markLabel);
		mainPanel.addComponent(markTextArea);
		mainPanel.addComponent(buttonPanel);
		mainPanel.addComponent(pdfLink);
		
		addComponent(sideBar);
		addComponent(mainPanel);
	}
	
	private void initializeComponents() {
		
		mainPanel.setHeight("100%");
		sideBar.setHeight("100%");
		applicationFormsLayout.setSpacing(true);
		searchLayout.setSpacing(true);
		sidebarContent.setSizeFull();
		
		searchCriteria.setNullSelectionAllowed(false);
		searchCriteria.setValue(categories.get(0));
		searchCriteria.setImmediate(true);
		
		searchField.setImmediate(true);
		searchField.setRequired(true);
		
		for (String currCategory : categories) {
			searchCriteria.addItem(currCategory);
		}
		
		Long formCount = HRPage.getCountOfAllForms();
		Long notAppliedCount = HRPage.getCountOfBlankWithoutInterview();
		Long notVerificatedCount = HRPage.getCountOfNonVerificatedForms();
		
		String allFormsTitle = "Все записанные(" + String.valueOf(formCount) + ")";
		String notAppliedFormsTitle = "Не записанные(" + String.valueOf(notAppliedCount) + ")";
		String notVerifiedFormsTitle = "Не проверенные("+ String.valueOf(notVerificatedCount) + ")";
		
		formCategories.addItem("1");
		formCategories.addItem("2");
		formCategories.addItem("3");
		
		formCategories.setItemCaption("1", allFormsTitle);
		formCategories.setItemCaption("2", notAppliedFormsTitle);
		formCategories.setItemCaption("3", notVerifiedFormsTitle);
		
		formCategories.setNullSelectionAllowed(false);
		
		formCategories.setChildrenAllowed(allFormsTitle, false);
		formCategories.setChildrenAllowed(notAppliedFormsTitle, false);		
		formCategories.setChildrenAllowed(notVerifiedFormsTitle, false);
		
		formCategories.addListener(new ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent event) {
				fillTable(event.getItemId());
			}
		});
		
		markTextArea.setRows(3);
		markTextArea.setColumns(40);
		markTextArea.setRequired(true);
		markTextArea.setEnabled(false);

		initButtons();
	}
	
	private void initButtons() {
		final String width = "150";
		final String STYLE = Runo.BUTTON_LINK;
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
		markSaveButton.setWidth(width);
		markSaveButton.setEnabled(false);
		photoSaveButton.setWidth(width);
		photoSaveButton.setEnabled(false);
		showDiffButton.setWidth(width);
		showDiffButton.setEnabled(false);
		
		deleteButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (formTable.getValue() != null) {
					StudentDataShort selectedValue = (StudentDataShort) formTable
							.getValue();
					showPopup(selectedValue);
				} else {
					showNotification("Ошибка!", "Перед удалением выберите анкету из списка!");
				}
			}
		});

		editButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (formTable.getValue() != null) {
					final Window window = new Window("Window");
					StudentDataShort selectedValue = (StudentDataShort) formTable
							.getValue();
					StudentBlank studentBlank = new StudentBlank(HRPage
							.getUserNameByFormId(selectedValue.getIdForm()),
							mainPage, username);
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

		verifyButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (formTable.getValue() != null) {
					StudentDataShort selectedValue = (StudentDataShort) formTable
							.getValue();
					HRPage.verificateForm(selectedValue.getIdForm());
					showNotification("Анкета подтверждена!", "");
					fillFormsTable(HRPage.getNonVerificatedForms());
				} else {
					showNotification("Ошибка!", "Перед подтверждением выберите анкету из списка!");
				}
			}
		});
		
		searchButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (!searchField.getValue().equals("")) {
					String filter = (String) searchCriteria.getValue();
					String value = (String) searchField.getValue();
					List<StudentDataShort> data = HRPage.searchStudents(filter, value);
					fillFormsTable(data);
				}
			}
		});
		
		markSaveButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (!markTextArea.getValue().equals("")
						&& (formTable.getValue() != null)) {
					StudentDataShort selectedValue = (StudentDataShort) formTable
							.getValue();
					HRPage.setStudentMark(selectedValue.getIdForm(), username,
							markTextArea.getValue().toString());
					showNotification("Оценка выставлена!", "");
					markTextArea.setEnabled(false);
					markSaveButton.setEnabled(false);
					List<StudentsMarks> currMarks = HRPage.getStudentMark(selectedValue.getIdForm());
					fillResultsTable(currMarks);
				} else {
					showNotification("Ошибка!", "Перед сохранением выберите анкету из списка и введите оценку!");
				}
			}
		});

		photoSaveButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (formTable.getValue() != null) {
					StreamSource ss = new StreamSource() {
						StudentDataShort selectedValue = (StudentDataShort) formTable
								.getValue();
						StudentData studentData = StudentPage
								.getStudentDataByIdForm(selectedValue
										.getIdForm());
						byte[] bytes = studentData.getPhoto();
						InputStream is = new ByteArrayInputStream(bytes);

						@Override
						public InputStream getStream() {
							return is;
						}
					};
					StreamResource sr = new StreamResource(ss, "userPhoto.jpg", mainPage);
					getWindow().open(sr, "_blank");
				} else {
					showNotification("Ошибка!",
									"Перед сохранением выберите анкету из списка и введите оценку!");
				}
			}
		});
		
		showDiffButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (formTable.getValue() != null) {
					StudentDataShort selectedValue = (StudentDataShort) formTable
							.getValue();
					Integer formID = selectedValue.getIdForm();
					BlankDifferensesWindow diffWindow = new BlankDifferensesWindow(
							mainPage, formID);
					getWindow().addWindow(diffWindow);
				} else {
					showNotification("Ошибка!", "Перед просмотром выберите анкету из списка и введите оценку!");
				}
			}
		});
	}
	
	private void fillTable(Object id) {
		List<StudentDataShort> data = null;
		if (id == null || id.equals("1") ) {
			data = HRPage.getAllForms();
			verifyButton.setEnabled(false);
			markTextArea.setEnabled(false);
			markSaveButton.setEnabled(false);
			editButton.setEnabled(true);
		} else if (id.equals("2")) {
			data = HRPage.getNonVerificatedForms();
			verifyButton.setEnabled(true);
			markTextArea.setEnabled(false);
			markSaveButton.setEnabled(false);
			editButton.setEnabled(false);
			markTextArea.setEnabled(false);
		} else if (id.equals("3")) {
			data = HRPage.getBlankWithoutInterview();
			verifyButton.setEnabled(false);
			markTextArea.setEnabled(false);
			markSaveButton.setEnabled(false);
			editButton.setEnabled(false);
			markTextArea.setEnabled(false);
		}
		fillFormsTable(data);
	}
	
	private void fillFormsTable(List<StudentDataShort> data) {
		formTable.setContainerDataSource(new BeanItemContainer(StudentDataShort.class, data));
	}
	
	private void fillResultsTable(List<StudentsMarks> data) {
		resultsTable.setContainerDataSource(new BeanItemContainer(StudentsMarks.class, data));
	}
	
	private void showNotification(String header, String message) {
		getWindow().showNotification(header, message, Window.Notification.TYPE_TRAY_NOTIFICATION);
	}

	private Link getPDFLink() {
		StreamResource resource = new StreamResource(new PdfStreamSource(),
				"form.pdf", mainPage);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String filename = "form-" + df.format(new Date()) + ".pdf";
		resource.setFilename(filename);
		resource.setCacheTime(0);
		Link pdf = new Link("Просмотреть/Распечатать анкету", resource);
		pdf.setTargetName("_blank");
		pdf.setTargetWidth(600);
		//pdf.setTargetHeight(height - 10);
		pdf.setTargetBorder(Link.TARGET_BORDER_NONE);
		pdf.setDescription("Анкета студента (откроется в новом окне)");
		ThemeResource icon = new ThemeResource("icons/32/document-pdf.png");
		pdf.setIcon(icon);
		return pdf;
	}

	private Table createFormsTable(List<StudentDataShort> data) {

		Table formsTable = new Table();
		BeanItemContainer<StudentDataShort> container = 
				new BeanItemContainer<StudentDataShort>(StudentDataShort.class, data);

		Object[] NATURAL_COL_ORDER = new Object[] { "idForm",
				"studentLastName", "studentFirstName", "studentMiddleName",
				"studentInstitute", "studentInstituteCourse", "studentFaculty",
				"studentCathedra" };

		String[] COL_HEADERS_RUSSIAN = new String[] { "Номер анкеты",
				"Фамилия", "Имя", "Отчество", "ВУЗ", "Курс", "Факультет",
				"Кафедра" };

		formsTable.setWidth("100%");
		formsTable.setHeight("300");
		formsTable.setSelectable(true);
		formsTable.setImmediate(true);
		formsTable.setContainerDataSource(container);
		formsTable.setColumnReorderingAllowed(true);
		formsTable.setColumnCollapsingAllowed(true);
		formsTable.setVisibleColumns(NATURAL_COL_ORDER);
		formsTable.setColumnHeaders(COL_HEADERS_RUSSIAN);
		formsTable.addListener(new Table.ValueChangeListener() {

			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				Object value = event.getProperty().getValue();
				if (value != null) {
					StudentDataShort selectedValue = (StudentDataShort) value;
					mainPanel.setCaption("Информация: "+selectedValue.getStudentFirstName()+" "+selectedValue.getStudentLastName());
					List<StudentsMarks> currMarks = HRPage.getStudentMark(selectedValue.getIdForm());
					fillResultsTable(currMarks);

					Link oldPDFLink = pdfLink;
					pdfLink = getPDFLink();
					mainPanel.replaceComponent(oldPDFLink, pdfLink);
					try {
						if (formCategories.getValue().equals("3")) {
							markTextArea.setEnabled(false);
							markSaveButton.setEnabled(false);
							photoSaveButton.setEnabled(true);
							showDiffButton.setEnabled(false);
						} else if (formCategories.getValue().equals("2")) {
							markTextArea.setEnabled(false);
							markSaveButton.setEnabled(false);
							photoSaveButton.setEnabled(true);
							showDiffButton.setEnabled(true);
						} else {
							markTextArea.setEnabled(true);
							markSaveButton.setEnabled(true);
							photoSaveButton.setEnabled(true);
							showDiffButton.setEnabled(false);
						}
						markTextArea.setValue("");
					} catch (NullPointerException e) {

					}
				} else {
					markTextArea.setEnabled(false);
					markSaveButton.setEnabled(false);
					photoSaveButton.setEnabled(false);
					showDiffButton.setEnabled(false);
				}
			}
		});
		
		return formsTable;
	}
	
	private void showPopup(final StudentDataShort data) {
		GridLayout layout = new GridLayout(2,1);
		Button ok = new Button("OK");
		Button cancel = new Button("Отмена");
		layout.addComponent(ok);
		layout.addComponent(cancel);
		final Window popupView = new Window("Подтвердите удаление", layout);
		popupView.center();
		popupView.setResizable(false);
		
		ok.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				HRPage.deleteStudentBlank(data.getIdForm());
				showNotification("Анкета удалена!", "");
				fillTable(formCategories.getValue());
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

	private Table createResultsTable(List<StudentsMarks> data) {

		Table resultsTable = new Table();
		BeanItemContainer<StudentsMarks> container = new BeanItemContainer<StudentsMarks>(StudentsMarks.class, data);
		
		Object[] NATURAL_COL_ORDER = new Object[] { "idUser", "userName",
				"studentMark" };
		String[] COL_HEADERS_RUSSIAN = new String[] {
				"Идентификатор пользователя", "Имя пользователя", "Оценка" };
		
		resultsTable.setContainerDataSource(container);
		
		resultsTable.setWidth("100%");
		resultsTable.setHeight("100");
		resultsTable.setSelectable(false);
		resultsTable.setImmediate(true);
		resultsTable.setColumnReorderingAllowed(true);
		resultsTable.setColumnCollapsingAllowed(true);
		resultsTable.setVisibleColumns(NATURAL_COL_ORDER);
		resultsTable.setColumnHeaders(COL_HEADERS_RUSSIAN);
		
		return resultsTable;
	}

	private class PdfStreamSource implements StreamResource.StreamSource {

		@Override
		public InputStream getStream() {
			StudentDataShort selectedValue = (StudentDataShort) formTable
					.getValue();
			ApplicationForm form = new ApplicationForm(
					selectedValue.getIdForm());
			return new ByteArrayInputStream(form.generateFormPDF());
		}
	}
}
