package ua.netcrackerteam.util.zip.source;

import java.io.*;
import java.util.List;
import java.util.zip.ZipOutputStream;

import ua.netcrackerteam.util.zip.entity.ZipContentFile;
import ua.netcrackerteam.util.zip.exception.ZipException;

/**
 * @author unconsionable
 */
public class ZipSource implements ZipSourceI {

    private List<ZipContentFile> files;

    public ZipSource(List<ZipContentFile> files) {
        this.files = files;
    }

    public ByteArrayOutputStream createZipOutput() throws ZipException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(outputStream));
      
       
        try {
            for (ZipContentFile zipContentFile : files) {
                zipContentFile.writeToZip(zipOutputStream);
            }
        } finally {
            closeOutputStream(zipOutputStream);
        }

        return outputStream;
    }

    private void closeOutputStream(ZipOutputStream zipOutputStream) throws ZipException {
        if (zipOutputStream != null) {
            try {
                zipOutputStream.close();
            } catch (IOException ex) {
                throw new ZipException("can't close zip", ex);
            }
        }
    }

    @Override
    public InputStream getStream() {
        ByteArrayOutputStream output = null;
        try {
            output = createZipOutput();
            return new ByteArrayInputStream(output.toByteArray());
        } catch (ZipException ex) {
            ex.printStackTrace();
            return new ByteArrayInputStream(new byte[]{});
        }
    }
}