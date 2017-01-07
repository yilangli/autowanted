package com.servlet;

import java.io.File; 
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.service.PostService;
import com.service.UserService;
import com.util.ResponseUtils;


public class AddVehicle extends HttpServlet {

	private static final long serialVersionUID = 1L;  
    
    private String filePath;       
    private String tempPath;      
        
    public void init(ServletConfig config) throws ServletException    
    {    
        super.init(config);    
        filePath = config.getInitParameter("filepath");    
        tempPath = config.getInitParameter("temppath"); 
       
        ServletContext context = getServletContext();    
   
        filePath = context.getRealPath(filePath);    
        tempPath = context.getRealPath(tempPath); 
      
        File pathFile = new File(filePath);  
        File pathTemp = new File(tempPath);  
        if(!pathFile.exists()){  
            pathFile.mkdirs();  
        }  
        if(!pathTemp.exists()){  
            pathTemp.mkdirs();  
        }  
    }    

    

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String username = null;
		String type = null;
		try {
			username = session.getAttribute("username").toString();
		    type = session.getAttribute("type").toString();
		} catch (Exception e) {
			username = null;
			type = null;
		}
		
		if (username!=null&&type!=null) {
			
		PostService service=new PostService();
		String vin=request.getParameter("vin");
		boolean result=service.isExist(vin);
		response.setContentType("utf-8");
		response.setCharacterEncoding("utf-8");
		String returns=ResponseUtils.combineResponse(username, type, result?"true":"false");
		response.getWriter().println(returns);
		}else {
			String returns=ResponseUtils.combineResponse(username, type, "\"\"");
			response.getWriter().println(returns);
		}
	}



	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String username = null;
		String type = null;
		try {
			username = session.getAttribute("username").toString();
		    type = session.getAttribute("type").toString();
		} catch (Exception e) {
			username = null;
			type = null;
		}
		
		if (username!=null&&type.equals("user")) {
			
			/*req.setCharacterEncoding("UTF-8");
	        res.setContentType("text/plain;charset=UTF-8");    
	        PrintWriter pw = res.getWriter();  */  
	        try{    
	            DiskFileItemFactory diskFactory = new DiskFileItemFactory();    
	            diskFactory.setSizeThreshold(1000 * 1024 * 1024);    
	            diskFactory.setRepository(new File(tempPath));    
	            
	            ServletFileUpload upload = new ServletFileUpload(diskFactory);    
	            upload.setSizeMax(1000 * 1024 * 1024);    
	            List<FileItem> fileItems = upload.parseRequest(new ServletRequestContext(req));    
	            Iterator<FileItem> iter = fileItems.iterator();
	            Map<String , String > map=new HashMap<String, String>();
	            while(iter.hasNext())    
	            {    
	                FileItem item = (FileItem)iter.next();    
	                if(item.isFormField())    
	                {    
	                	String name = item.getFieldName();    
	     		        String value = item.getString();   
	     		        map.put(name, value);
	                }else{    
	                    String path=processUploadFile(item, req);   
	                    map.put("image", path);
	                }    
	            }
	           /*
	            pw.close();*/
	            new PostService().addPost(map,username);  
	            res.sendRedirect("pages/post.html");
	        }catch(Exception e){    
	            e.printStackTrace();    
	        }
			
			
		} else {

			res.sendRedirect("pages/login.html");
   }
	}
	  		        
		    private String processUploadFile(FileItem item, HttpServletRequest req)    
		        throws Exception    
		    {    
		        String filename = item.getName();           
		        int index = filename.lastIndexOf("\\");    
		        filename = filename.substring(index + 1, filename.length());    
		   
		        long fileSize = item.getSize();    
		   
		        if("".equals(filename) && fileSize == 0)    
		        {               
		            return "";    
		        }    
		        File uploadFile = new File(filePath + "/" + filename);    
		        if(!uploadFile.exists()){  
		            uploadFile.createNewFile();  
		        }  
		        item.write(uploadFile); 
		        String filePath = "/initUpload/"+filename;
		        return filePath;
		        
		    }   

}
