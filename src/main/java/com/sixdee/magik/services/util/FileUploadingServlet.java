package com.sixdee.magik.services.util;
/**
 * 
 * @author amal a s
 * @version 1.0
 * 
 * <p>
 * <b><u>Development History</u></b><br>
 * <table border="1" width="100%">
 * <tr>
 * <td width="15%"><b>Date</b></td>
 * <td width="20%"><b>Author</b></td>
 * </tr>
 * <tr>
 * <td>March 04,2020 12:46:12 PM</td>
 * <td>amal a s</td>
 * </tr>
 * </table>
 * </p>
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sixdee.magik.services.threads.SocialMediaSchedulerThread;

@WebServlet(name = "FileUploadingServlet", urlPatterns = "/UploadFile")
public class FileUploadingServlet extends HttpServlet {

	private static final long serialVersionUID = -2392240456866818181L;
	private static final Logger logger = LoggerFactory.getLogger(FileUploadingServlet.class);

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		processRequest(request, response);
	}

	@SuppressWarnings("rawtypes")
	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		logger.info("Request Came for Uploading file to path : "+SocialMediaSchedulerThread.fileUploadPath);
		String responseMessage = "UP0000";
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			try {
				List items = upload.parseRequest(request);
				Iterator iterator = items.iterator();
				while (iterator.hasNext()) {
					FileItem item = (FileItem) iterator.next();
					if (!item.isFormField()) {
						String fileName = item.getName();
						File path = new File(SocialMediaSchedulerThread.fileUploadPath);
						if (!path.exists()) {
							path.mkdirs();
						}

						File uploadedFile = new File(
								path + "/" + String.valueOf(System.currentTimeMillis()) + "_" + fileName);
						item.write(uploadedFile);
						logger.info("Uploading success. Absolute Path : " + uploadedFile.getAbsolutePath());
						responseMessage = uploadedFile.getName();
					}
				}
			} catch (FileUploadException e) {
				responseMessage = "UP0001";
				e.printStackTrace();
			} catch (Exception e) {
				responseMessage = "UP0001";
				e.printStackTrace();
			}
		}

		pw.write(responseMessage);
		pw.flush();

	}

}
