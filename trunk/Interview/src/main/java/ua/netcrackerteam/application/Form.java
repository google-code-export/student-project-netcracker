/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.application;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author tanya
 */
public class Form implements Observer{
    
    /**
     * Unique identifier for form
     */
    public int idForm;
    /**
     * interview
     */
    public Observable interview;
    
    /**
     * Called when the interview was changed by hr
     */
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Checking the filling of fields
     * @return true/false
     */
    public boolean checkFields(){
        return true;
    }
    /**    
     * Check size photo (max size 300 kb)
     * @return true/false
     */
    public boolean checkSizePhoto(){
        return true;
    }
    
    /**
     * Create from (pdf-format)
     */
    public void generateFormPDF() throws DocumentException, IOException{
        
        /*BaseFont font = BaseFont.createFont("times.ttf","cp1251",BaseFont.EMBEDDED);        
        PdfReader reader = new PdfReader("Template.pdf");

        PdfStamper stamper = new PdfStamper(reader,new FileOutputStream("C:\\Form.pdf"));
        
        AcroFields form = stamper.getAcroFields();
        form.addSubstitutionFont(font);
        
        Map map = form.getFields();
        Iterator iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            form.setField((String)iterator.next(), "Поле");
        }
        stamper.close(); */
    }
 
    
}
