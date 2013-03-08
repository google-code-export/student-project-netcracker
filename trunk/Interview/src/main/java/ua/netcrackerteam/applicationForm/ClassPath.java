/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm;

	import java.io.File;
	
	public class ClassPath{
            
	    private static ClassPath instance = null;
	    private String webInfPath, webXmlPath, configXmlPath;
	 
	    /**
	     * The constructor will get the webInfPath and store it until the app close
	     */
	    private ClassPath(){
	        File myClass = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
	 
	        //The package name give the number of parentFile to apply
	        //We add 3 : the first one is the .class name, the "." counted are one less, and the package is
	        //inside a classes folder. So 3.
	        int packageSubFolder = getClass().getPackage().getName().replaceAll("[^.]", "").length() + 3;
	 
	        //Place the file to the good parent file to point into the web inf
	        for(int i=0; i<packageSubFolder; i++){
	            myClass = myClass.getParentFile();
	        }
	 
	        this.webInfPath = myClass.getAbsolutePath().replaceAll("%20", " ") + File.separator;
	        this.webXmlPath = this.getWebInfPath() + "web.xml";
	        this.configXmlPath = this.getWebInfPath() + "config.xml";
	    }
	 
	    /**
	     * Singleton structure
	     * @return himself
	     */
	    public static ClassPath getInstance(){
	        if(instance == null){
	            instance = new ClassPath();
	        }
	        return instance;
	    }
	 
	 
	    /**
	     * Get back the WEB-INF path
	     * @return The WEB-INF path
	     */
	    public String getWebInfPath(){
	        return this.webInfPath;
	    }
	 
	    /**
	     * Get back the WEB-INF/web.xml path
	     * @return The WEB-INF/web.xml path
	     */
	    public String getWebXmlPath(){
	        return this.webXmlPath;
	    }
	 
	    /**
	     * Get back the WEB-INF/config.xml path
	     * @return The WEB-INF/config.xml path
	     */
	    public String getConfigXmlPath(){
	        return this.configXmlPath;
	    }
	}
