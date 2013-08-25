package ua.netcrackerteam.util.zip.entity;

import ua.netcrackerteam.util.zip.exception.ZipException;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author unconsionable
 */
public class ZipContentImage implements ZipContentFile {

    protected String fileName;
    protected byte[] image;

    public ZipContentImage(String fileName, byte[] image) {
        this.fileName = fileName;
        this.image = image;
    }

    public ZipContentImage() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void writeToZip(ZipOutputStream zipOutputStream) throws ZipException {
        try {
            zipOutputStream.putNextEntry(new ZipEntry(fileName));
            zipOutputStream.write(image);
            zipOutputStream.closeEntry();
        } catch (IOException ex) {
            throw new ZipException("Can't write image file [" + fileName + "] to zip", ex);
        }
    }
}