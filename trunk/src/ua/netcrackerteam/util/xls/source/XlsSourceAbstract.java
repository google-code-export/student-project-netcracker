package ua.netcrackerteam.util.xls.source;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ua.netcrackerteam.util.xls.entity.XlsEntity;
import ua.netcrackerteam.util.xls.exception.XlsException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @author unconsionable
 */
public abstract class XlsSourceAbstract<T extends XlsEntity> implements XlsSourceI<T> {

    protected Workbook wb;
    protected List<T> rows;

    public XlsSourceAbstract(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public InputStream getStream() {
        try {
            return new ByteArrayInputStream(getByteArrayOutputStream().toByteArray());
        } catch (XlsException ex) {
            //TODO handle error
            return null;
        }
    }
    
    private ByteArrayOutputStream getByteArrayOutputStream() throws XlsException {
        ByteArrayOutputStream output = null;
        try {
            output = new ByteArrayOutputStream();

            initWorkbook();

            wb.write(output);
        } catch (IOException ex) {
            throw new XlsException("Can't write xls to OutputStream", ex);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                    throw new XlsException("Can't close OutputStream", ex);
                }
            }
        }

        return output;
    }

    protected void initWorkbook() {
        wb = new HSSFWorkbook();
        Sheet sh = wb.createSheet();
        fillSheet(sh);
    }

    protected abstract void fillSheet(Sheet sh);
}