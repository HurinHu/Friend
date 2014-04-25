package servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * Servlet implementation class Import
 */
@SuppressWarnings("serial")
public class Import extends HttpServlet {
	private static final long serialVersionUID = -7744625344830285257L;
	private ServletContext sc;  
    private String savePath; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Import() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/**
		 * Delete table
		 */
		
		Database db = new Database();
		try{
			db.connect();
			db.deleteimport();
			db.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void init(ServletConfig config) {  
	        // 在web.xml中设置的一个初始化参数  
	        savePath = config.getInitParameter("savePath");  
	        sc = config.getServletContext();  
	}  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/**
		 * Save file and read into database, and delete file
		 */
		
		request.setCharacterEncoding("UTF-8");  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        ServletFileUpload upload = new ServletFileUpload(factory);  
        try {  
            List items = upload.parseRequest(request);  
            Iterator itr = items.iterator();  
            while (itr.hasNext()) {  
                FileItem item = (FileItem) itr.next();  
                if (item.isFormField()) {  
                    
                } else {  
                    if (item.getName() != null && !item.getName().equals("")) {  
                        //System.out.println("size:" + item.getSize());  
                        //System.out.println("type:" + item.getContentType());  
                        //System.out.println("name:" + item.getName());  
                        File tempFile = new File(item.getName());   
                        File f1 = new File(sc.getRealPath("/") + savePath);
                        if (!f1.exists()) {
                        	f1.mkdirs();
                        }
                        File file = new File(sc.getRealPath("/") + savePath,tempFile.getName()); 
                        item.write(file);  
                        read(sc.getRealPath("/") + savePath + "/" + tempFile.getName());  
                        file.delete();
                    } else {  
                    	System.out.println("no file");    
                    }  
                }  
            }  
        } catch (FileUploadException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println("error");  
        }  
	}
	
	public void read(String file){
		Database db = new Database();
		try{
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);
			db.connect();
			db.deleteimport();
			for(int i=1;i<rs.getRows(); i++){
				Cell c0 = rs.getCell(0, i);
				Cell c1 = rs.getCell(1, i);
				Cell c2 = rs.getCell(2, i);
				Cell c3 = rs.getCell(3, i);
				Cell c4 = rs.getCell(4, i);
				Cell c5 = rs.getCell(5, i);
				Cell c6 = rs.getCell(6, i);
				Cell c7 = rs.getCell(7, i);
				Cell c8 = rs.getCell(8, i);
				Cell c9 = rs.getCell(9, i);
				Cell c10 = rs.getCell(10, i);
				String num = c0.getContents();
				String id = c1.getContents();
				String name = c2.getContents();
				String tel = c3.getContents();
				String project = c4.getContents();
				String supervisor = c5.getContents();
				String observer = c6.getContents();
				String examiner = c7.getContents();
				String date = c8.getContents();
				String time = c9.getContents();
				String room = c10.getContents();
				db.insertimport(num,id,name,tel,project,supervisor,observer,examiner,date,time,room);
			}
			db.close();
			rwb.close();
		}catch(Exception e){
			e.printStackTrace();
		}
}

}
