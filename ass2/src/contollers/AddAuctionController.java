package contollers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
	
	Timestamp ts;
	
	private int minutesUntilAuctionEnd = 0;
	public AddAuctionController(ParameterManager params) {
		super(params);
		createForm();


		//get timestamp
		//TODO tell user to format like this




	}

	protected void createForm() {

		formManager.addForm("title", paramManager.getIndividualParam("title"), FormManager.RESTIRCT_WORD_MAX, 10);
		formManager.addForm("category", paramManager.getIndividualParam("category"));
		//formManager.addForm("picture", paramManager.getIndividualParam("picture"));
		formManager.addForm("description", paramManager.getIndividualParam("description"), FormManager.RESTIRCT_WORD_MAX, 100);
		formManager.addForm("postageDetails", paramManager.getIndividualParam("postageDetails"));
		formManager.addForm("reservePrice", paramManager.getIndividualParam("reservePrice"),FormManager.RESTRICT_FLOAT_ONLY_GREATER_ZERO);
		formManager.addForm("biddingStart", paramManager.getIndividualParam("biddingStart"), FormManager.RESTRICT_FLOAT_ONLY);
		formManager.addForm("auctionEnd", paramManager.getIndividualParam("auctionEnd"), FormManager.RESTRICT_NUMERIC_ONLY);
		formManager.addForm("biddingIncrements", paramManager.getIndividualParam("biddingIncrements"), FormManager.RESTRICT_FLOAT_ONLY_GREATER_ZERO);
		// it's optional so i think don't add it
		//formManager.addForm("endOfAuction", paramManager.getIndividualParam("endOfAuction"));
	}

	public int addAuction(Part file, String diskLocation, String location, String author) {
		String err = "";
		if (Float.parseFloat(paramManager.getIndividualParam("reservePrice")) <= 0)
			err += "reserve price must be greater than 0<br>";
		if (Float.parseFloat(paramManager.getIndividualParam("biddingIncrements")) <= 0)
			err += "bidding increments must be greater than 0<br>";
		if (Float.parseFloat(paramManager.getIndividualParam("biddingStart")) < 0)
			err += "bidding start must not be negative<br>";
		if (Integer.parseInt(paramManager.getIndividualParam("auctionEnd")) < 3 || Integer.parseInt(paramManager.getIndividualParam("auctionEnd")) > 60)
			err += "you have modified the form and specified an invalid auction end<br>";
		
		String filename = getFilename(file);
		
		String ext = "";
		int indexOf = filename.lastIndexOf('.');
		if (indexOf > 0)
		    ext = filename.substring(indexOf + 1);
		    
		//file extensions
		if (!ext.equals("jpg") && !ext.equals("jpeg") && !ext.equals("png") && !ext.equals("gif") && !ext.equals("bmp"))
			err += "your file is not a picture<br>";
			
		if (!err.equals("")) {
			message = err;
			return 0;
		}
		
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
		}

		return JDBCConnector.addAuction(paramManager.getIndividualParam("title"), author, paramManager.getIndividualParam("category"), location + filename, paramManager.getIndividualParam("description"), paramManager.getIndividualParam("postageDetails"), Float.parseFloat(paramManager.getIndividualParam("reservePrice")), Float.parseFloat(paramManager.getIndividualParam("biddingStart")), Float.parseFloat(paramManager.getIndividualParam("biddingIncrements")), getEndOfAuction(), false);
		
	}

	private Timestamp getEndOfAuction() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, Integer.parseInt(paramManager.getIndividualParam("auctionEnd")));
		ts = new Timestamp(cal.getTime().getTime());
		return ts;
	}
	
	//credit from stackoverflow - http://stackoverflow.com/questions/2422468/how-to-upload-files-to-server-using-jsp-servlet
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
