package ua.netcrackerteam.util.xls.source;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import ua.netcrackerteam.util.xls.entity.XlsUserInfo;
import org.apache.poi.ss.usermodel.CellStyle;
import java.util.List;


/**
 * @author unconsionable
 */
public class UserInfoXlsSource extends XlsSourceAbstract<XlsUserInfo> {

    private CellStyle style;
    private static final String[] SHEET_HEADERS = new String[]{"№ анкеты", "Фамилия",
        "Имя", "Финальный результат", "Интервьюер 1 (HR)",
        "Результат 1 (HR)", "Комментарий 1 (HR)", "Интервьюер 2 (Dev)",
        "Результат 2 (Dev)", "Комментарий 2 (Dev)", "Курс",
        "Ср. балл в ВУЗе", "Специальность", "ВУЗ", "e-mail",
        "e-mail 2", "телефон"};
    private static final Integer[] SHEET_COLUMN_WIDTH = new Integer[]{
        2350, 2550, 2100, // "№ анкеты", "Фамилия", "Имя" 
        3400, 4000, 3600, // "Финальный результат", "Интервьюер 1 (HR)", "Результат 1 (HR)"
        4300, 4000, 3600, // "Комментарий 1 (HR)", "Интервьюер 2 (Dev)", "Результат 2 (Dev)"
        4300, 2350, 2350, // "Комментарий 2 (Dev)", "Курс", "Ср. балл в ВУЗе"
        2350, 2350, 2350, // "Специальность", "ВУЗ", "e-mail"
        2350, 2350}; // "e-mail 2", "телефон"
    private static final short SHEET_HEADER_HEIGTH = 1000;

    public UserInfoXlsSource(List<XlsUserInfo> rows) {
        super(rows);
    }

    @Override
    protected void fillSheet(Sheet sh) {
        initHeaders(sh.createRow(0));
        initStyles(sh);

        for (int i = 0; i < rows.size(); i++) {        
            fillRow(sh, sh.createRow(i + 1), rows.get(i));           
        }
    }

    private void initHeaders(Row row) {
        for (int i = 0; i < SHEET_HEADERS.length; i++) {
            row.createCell(i).setCellValue(SHEET_HEADERS[i]);
        }
        row.setHeight(SHEET_HEADER_HEIGTH);
    }

    private void initStyles(Sheet sh) {
        for (int i = 0; i < SHEET_COLUMN_WIDTH.length; i++) {
        	//Klitna 26.08
            //sh.setColumnWidth(i, SHEET_COLUMN_WIDTH[i]);
            sh.autoSizeColumn(i);
        }

        style = wb.createCellStyle();
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);

        for (int i = 0; i < SHEET_COLUMN_WIDTH.length; i++) {
            sh.getRow(0).getCell(i).setCellStyle(style);
        }
    }
    
    private void fillRow(Sheet sheet, Row row, XlsUserInfo rowData) {
        row.createCell(0).setCellValue(rowData.getNumber2());
        row.createCell(1).setCellValue(rowData.getSurname());
        row.createCell(2).setCellValue(rowData.getName());
        row.createCell(3).setCellValue(rowData.getFinalResult());
        row.createCell(4).setCellValue(rowData.getHr1());
        row.createCell(5).setCellValue(rowData.getResult1());
        row.createCell(6).setCellValue(rowData.getCalculableComment1());
        row.createCell(7).setCellValue(rowData.getHr2());
        row.createCell(8).setCellValue(rowData.getResult2());
        row.createCell(9).setCellValue(rowData.getCalculableComment2());
        row.createCell(10).setCellValue(rowData.getCource());
        row.createCell(11).setCellValue(rowData.getAverageHighSchoolGrade());
        row.createCell(12).setCellValue(rowData.getSpeciality());
        row.createCell(13).setCellValue(rowData.getHighSchoolName());
        row.createCell(14).setCellValue(rowData.getEmail1());
        row.createCell(15).setCellValue(rowData.getEmail2());
        row.createCell(16).setCellValue(rowData.getTelNumber());
        
        ////Klitna 26.08
        for(int i = 0; i < 17; i++){
        	sheet.autoSizeColumn(i);
        }
       

        for (int i = 0; i < SHEET_HEADERS.length; i++) {
            row.getCell(i).setCellStyle(style);
            
        }
    }
}