package contollers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import beans.UserBean;
import ass2.FormManager;
import ass2.JDBCConnector;
import ass2.ParameterManager;

public class AddAuctionController extends MasterFormBasedController {


	public AddAuctionController(ParameterManager pm, HttpServletRequest request) throws ServletException {
		// TODO Auto-generated constructor stub

		super(null);

		try {
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {

					String fieldName = item.getFieldName();
					String fieldValue = item.getFieldName();

					formManager.addForm(fieldName, fieldValue);

					System.out.println("AddAuctionController - adding form " + fieldName);


				} else {

					String fieldName = item.getFieldName();
					String fileName = item.getName();
					try {
						InputStream filecontent = item.getInputStream();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}



				}
			}
		} catch (FileUploadException e) {
			throw new ServletException("Can't do stuff", e);
		}
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

		formManager.addForm("title", paramManager.getIndividualParam("title"));
		formManager.addForm("category", paramManager.getIndividualParam("category"));
		formManager.addForm("picture", paramManager.getIndividualParam("picture"));
		formManager.addForm("description", paramManager.getIndividualParam("description"));
		formManager.addForm("postageDetails", paramManager.getIndividualParam("postageDetails"));
		formManager.addForm("reservePrice", paramManager.getIndividualParam("reservePrice"),FormManager.RESTRICT_NUMERIC_ONLY);
		formManager.addForm("biddingStart", paramManager.getIndividualParam("biddingStart"), FormManager.RESTRICT_NUMERIC_ONLY);
		formManager.addForm("biddingIncrements", paramManager.getIndividualParam("biddingIncrements"), FormManager.RESTRICT_NUMERIC_ONLY);
		// it's optional so i think don't add it
		//formManager.addForm("endOfAuction", paramManager.getIndividualParam("endOfAuction"));
		 */
	}

	public void addAuction() {

	}

}
