import java.util.Formatter;
import java.util.FormatterClosedException;
import java.io.*;

//Con esta clase vamos a escribir archivos


public class Archivo {
	
	private static Formatter output;
	
	public void escribirArchivo(String nombre,String contenido) {
	   
		try {  			
			
			File f = new File(nombre);
			if(!f.exists()){
			  f.createNewFile();
			}
			  		
            OutputStream outputStream = new FileOutputStream(nombre);  
            Writer outputStreamWriter = new OutputStreamWriter(outputStream);  
            outputStreamWriter.write(contenido);    
            outputStreamWriter.close();  
			
        } catch (Exception e) {  
           
            System.out.println(e.getMessage());
        }  
	  }

}
