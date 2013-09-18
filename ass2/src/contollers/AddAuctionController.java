package contollers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.sun.xml.internal.bind.CycleRecoverable.Context;

import beans.UserBean;
import ass2.FormManager;
import ass2.JDBCConnector;
import ass2.ParameterManager;

public class AddAuctionController extends MasterFormBasedController {

	private final static int MAX_SIZE = 50000;
	private final static int BUFFER_SIZE = 8192;
	
	public AddAuctionController(ParameterManager params) {
		super(params);
	}

	protected void createForm() {
		/*
		<li>Title: <input type="text" name="title"></li>
		<li>Category: <input type="password" name="category"></li>
		<li>Picture: <input type="file" name="picture"></li>
		<li>Description: <input type="text" name="description" width=600 height=300></li>
		<li>Postage Details: <input type="text" name="postageDetails"></li>
		<li>Reserve Price: <input type="text" name="reservePrice"></li> 	
		<li>Bidding Start Price: <input type="text" name="biddingStart"></li>
		<li>Bidding Increments: <input type="text" name="biddingIncrements"></li>
		<li>End of Auction: <input type="text" name="endOfAuction"></li>
		<li><input type="submit" value="submit"></li>
		*/

		formManager.addForm("title", paramManager.getIndividualParam("title"));
		formManager.addForm("category", paramManager.getIndividualParam("category"));
		//formManager.addForm("picture", paramManager.getIndividualParam("picture"));
		formManager.addForm("description", paramManager.getIndividualParam("description"));
		formManager.addForm("postageDetails", paramManager.getIndividualParam("postageDetails"));
		formManager.addForm("reservePrice", paramManager.getIndividualParam("reservePrice"),FormManager.RESTRICT_NUMERIC_ONLY);
		formManager.addForm("biddingStart", paramManager.getIndividualParam("biddingStart"), FormManager.RESTRICT_NUMERIC_ONLY);
		formManager.addForm("biddingIncrements", paramManager.getIndividualParam("biddingIncrements"), FormManager.RESTRICT_NUMERIC_ONLY);
		// it's optional so i think don't add it
		//formManager.addForm("endOfAuction", paramManager.getIndividualParam("endOfAuction"));
	}

	public void addAuction(Part file, String diskLocation, String location, String author, Timestamp endOfAuction) {
		String filename = getFilename(file);
		try {
			InputStream is = file.getInputStream();
			OutputStream os = new FileOutputStream(diskLocation + location + filename);
			
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytes;
			while ((bytes = is.read(buffer)) != -1) {
				os.write(buffer, 0, bytes);
			}
			
			is.close();
			os.close();
		} catch (Exception e) {
			System.out.println("Could not upload file.");
			//TODO cleanup file if written
			return ;
		}
		
		JDBCConnector.addAuction(paramManager.getIndividualParam("title"), author, paramManager.getIndividualParam("category"), location + filename, paramManager.getIndividualParam("description"), paramManager.getIndividualParam("postageDetails"), Float.parseFloat(paramManager.getIndividualParam("reservePrice")), Float.parseFloat(paramManager.getIndividualParam("biddingStart")), Float.parseFloat(paramManager.getIndividualParam("biddingIncrements")), endOfAuction, false);
	}
	
	//credit from stackoverflow - http://stackoverflow.com/questions/2422468/how-to-upload-files-to-server-using-jsp-servlet
	//CHECK FOR ATTACKS
	private static String getFilename(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
	        }
	    }
	    return null;
	}

}
