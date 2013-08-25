package ua.netcrackerteam.util.zip.entity;


import ua.netcrackerteam.util.zip.exception.ZipException;

import java.util.zip.ZipOutputStream;

/**
 * @author unconsionable
 */
public interface ZipContentFile {

    void writeToZip(ZipOutputStream zipOutputStream) throws ZipException;
}