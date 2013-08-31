package ua.netcrackerteam.GUI;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class Footer extends Label {
	
	public Footer() {
		super();
		initialize();
	}
	
	private void initialize() {
        String s = "";
        DataInputStream in;
        try {
            WebApplicationContext context = (WebApplicationContext) MainPage.getInstance().getContext();
            File file = new File (context.getHttpSession().getServletContext().getRealPath("/WEB-INF/resources/footer-text.txt") );
            in = new DataInputStream(new FileInputStream(file));
            byte[] array = new byte[in.available()];
            in.read(array);
            s = new String(array);
            in.close();
        } catch (IOException ex) {
            System.out.println("File footer-text.txt is not found");
        }
        setContentMode(Label.CONTENT_XHTML);
        setValue(s);
        setStyleName("footer");
	}
}
