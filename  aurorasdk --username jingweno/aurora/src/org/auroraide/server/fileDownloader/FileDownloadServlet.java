package org.auroraide.server.fileDownloader;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

import org.auroraide.server.database.File;

public class FileDownloadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet (HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException{
       
        String name=req.getParameter("name");
        String project=req.getParameter("project");
        String pkg=req.getParameter("pkg");
		
		//pout a filename
        //String filename="A.class";
        //bytes = any byte[] you want to send
        //byte[] bytes="".getBytes();
       
        //read the query string
        //String q=req.getQueryString();
        byte[] bytes=null;
        File file=null;
        try{
        //read class file from the database
        file=new File();
        List<?> files=file.loadAll(new String[] {"name","project","pkg"}, new Object[] {name,project,pkg});
        file=(File)files.get(0);
        	long length=file.compiled.length();
        	bytes=file.compiled.getBytes(1, (int)length);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
       
        int                 length   = 0;
        ServletOutputStream op       = res.getOutputStream();
        ServletContext      context  = getServletConfig().getServletContext();
        String              mimetype = context.getMimeType( file.name );

        //
        //  Set the response and go!
        //
        //
        if(bytes==null)
        	return;
        res.setContentType( (mimetype != null) ? mimetype : "application/octet-stream" );
        res.setContentLength( bytes.length );
        res.setHeader( "Content-Disposition", "attachment; filename=\"" + file.name + ".class\"" );

        //
        //  Stream to the requester.
        //
        byte[] bbuf = new byte[bytes.length];
   
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));

        while ((in != null) && ((length = in.read(bbuf)) != -1))
        {
            op.write(bbuf,0,length);
        }

        in.close();
        op.flush();
        op.close();
       
    }


}
