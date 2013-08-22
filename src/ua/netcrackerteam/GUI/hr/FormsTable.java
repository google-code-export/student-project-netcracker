package ua.netcrackerteam.GUI.hr;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ua.netcrackerteam.GUI.BlankDifferensesWindow;
import ua.netcrackerteam.GUI.MainPage;
import ua.netcrackerteam.GUI.hr.NavigationPanel.State;
import ua.netcrackerteam.applicationForm.ApplicationForm;
import ua.netcrackerteam.controller.StudentPage;
import ua.netcrackerteam.controller.bean.StudentData;
import ua.netcrackerteam.controller.bean.StudentDataShort;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class FormsTable extends Panel {
	
	private Table table = new Table();
	private HorizontalLayout buttonPanel = new HorizontalLayout();
	private Button photoSaveButton = new Button("Фото");
	private Button showDiffButton = new Button("Различия");
	
	private final Object[] NATURAL_COL_ORDER = new Object[] { "idForm",
													"studentLastName", "studentFirstName", "studentMiddleName",
													"studentInstitute", "studentInstituteCourse", "studentFaculty",
													"studentCathedra" };
	
	private final String[] COL_HEADERS_RUSSIAN = new String[] { "Номер анкеты",
													"Фамилия", "Имя", "Отчество", 
													"ВУЗ", "Курс", "Факультет",
													"Кафедра" };
	
	private BeanItemContainer<StudentDataShort> container =  new BeanItemContainer<StudentDataShort>(StudentDataShort.class);
	
	private State currentState;
	private DataLoader dataLoader;
	private Link pdfLink = new Link();
	
	private StudentDataShort selectedValue;
	
	public FormsTable(DataLoader dataLoader) {
		super();
		this.dataLoader = dataLoader;
		initialize();
	}
	
	public static interface DataLoader {
		List<StudentDataShort> getData(State state);
	}
	
	private void initialize() {
		table.setWidth("100%");
		table.setHeight("375");
		table.setSelectable(true);
		table.setImmediate(true);
		table.setContainerDataSource(container);
		table.setColumnReorderingAllowed(true);
		table.setColumnCollapsingAllowed(true);
		table.setVisibleColumns(NATURAL_COL_ORDER);
		table.setColumnHeaders(COL_HEADERS_RUSSIAN);
		
		showDiffButton.setWidth("150");
		photoSaveButton.setWidth("150");
		
		buttonPanel.addComponent(photoSaveButton);
		buttonPanel.addComponent(showDiffButton);
		buttonPanel.addComponent(pdfLink);
		
		photoSaveButton.setEnabled(false);
		showDiffButton.setEnabled(false);
		
		addComponent(table);
		addComponent(buttonPanel);
		
		table.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event.getProperty().getValue() != null) {
					selectedValue = (StudentDataShort) event.getProperty().getValue();
					//TODO Move to HRBlankLayout
					//mainPanel.setCaption("Информация: "+selectedValue.getStudentFirstName()+" "+selectedValue.getStudentLastName());
					Link oldPDFLink = pdfLink;
					pdfLink = getPDFLink();
					replaceComponent(oldPDFLink, pdfLink);
				}
			}
		});
		
		photoSaveButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (selectedValue != null) {
					StreamSource ss = new StreamSource() {
						StudentData studentData = StudentPage.getStudentDataByIdForm(selectedValue.getIdForm());
						byte[] bytes = studentData.getPhoto();
						InputStream is = new ByteArrayInputStream(bytes);

						@Override
						public InputStream getStream() {
							return is;
						}
					};
					StreamResource sr = new StreamResource(ss, "userPhoto.jpg", MainPage.getInstance());
					getWindow().open(sr, "_blank");
				} else {
					getWindow().showNotification("Ошибка!", 
							"Перед сохранением выберите анкету из списка и введите оценку!", 
							Window.Notification.TYPE_TRAY_NOTIFICATION);
				}
			}
		});
		
		showDiffButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (selectedValue != null) {
					Integer formID = selectedValue.getIdForm();
					BlankDifferensesWindow diffWindow = new BlankDifferensesWindow(
							MainPage.getInstance(), formID);
					getWindow().addWindow(diffWindow);
				} else {
					getWindow().showNotification("Ошибка!", 
							"Перед просмотром выберите анкету из списка и введите оценку!", 
							Window.Notification.TYPE_TRAY_NOTIFICATION);
				}
			}
		});
	}
	
	public StudentDataShort getCurrentValue() {
		return selectedValue;
	}
		
	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		
		this.currentState = currentState;
		table.setContainerDataSource(new BeanItemContainer<StudentDataShort>(StudentDataShort.class, dataLoader.getData(currentState)));
		
		if (currentState == State.NOT_CHECKED) {
			photoSaveButton.setEnabled(true);
			showDiffButton.setEnabled(false);
			return;
		} else {
			photoSaveButton.setEnabled(true);
			showDiffButton.setEnabled(false);
		}
	}

	public DataLoader getDataLoader() {
		return dataLoader;
	}

	public void setDataLoader(DataLoader dataLoader) {
		this.dataLoader = dataLoader;
	}
	
	private Link getPDFLink() {
		StreamResource resource = new StreamResource(new PdfStreamSource(),
				"form.pdf", MainPage.getInstance());
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
	
	private class PdfStreamSource implements StreamResource.StreamSource {

		@Override
		public InputStream getStream() {
			ApplicationForm form = new ApplicationForm(selectedValue.getIdForm());
			return new ByteArrayInputStream(form.generateFormPDF());
		}
	}
	
	public void refresh() {
		setCurrentState(currentState);
	}
	
	public void fillTable(List<StudentDataShort> data) {
		table.setContainerDataSource(new BeanItemContainer<StudentDataShort>(StudentDataShort.class, data));
	}
}
