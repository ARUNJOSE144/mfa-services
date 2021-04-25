package com.sixdee.magik.services.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.sixdee.magik.services.model.OfflineUploadSegmentTO;
import com.sixdee.magik.services.service.OfflineUploadSegmentService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.SystemProperties;

/**
 * @author Nakhil Kurian
 * @Date : January, 2021
 */

@RestController
public class OfflineUploadSegmentRestController {

	@Autowired
	private Environment env;

	static final Logger logger = Logger.getLogger(OfflineUploadSegmentRestController.class);

	@Autowired
	SystemProperties properties;

	@Autowired
	OfflineUploadSegmentService offlineUploadSegmentService;

	@SuppressWarnings("unused")
	@PostMapping(MagikServicePath.UPLOAD_OFFLINE_UPLOAD_SEGMENT)
	public @ResponseBody RestInfo<String> saveOfflineSegment(HttpServletRequest httpServletRequest,
			@RequestParam(value = "campaignOperationType") String campaignOperationType,
			@RequestParam(value = "fileName") String fileName, @RequestParam(value = "userId") String userId,
			@RequestParam(value = "files") MultipartFile files)
			throws ServletException, IOException, JSchException, SftpException {

		RestInfo<String> info = new RestInfo<String>();
		String status = "0";
		System.out.println("Inside Upload ::: ");
		System.out.println("fileName  ::    " + fileName);
		InputStream fis = null;
		String filename = null;
		BufferedReader bufferedReader = null;
		int port = 22;
		InputStream out = null;

		String host = env.getProperty("offline.segment.ip");
		String username = env.getProperty("offline.segment.username");
		String password = env.getProperty("offline.segment.password");
		System.out.println("Host Name ::  " + host);
		System.out.println("username  :: " + username);
		System.out.println("password :: " + password);

		try {
			status = offlineUploadSegmentService.saveOfflineSegment(campaignOperationType, userId);
			System.out.println("status  :::  " + status.toString());
			if (status == "0" && username != null && password != null && host != null) {

				JSch jsch = new JSch();
				Session session = jsch.getSession(username, host, port);
				session.setPassword(password);
				session.setConfig("StrictHostKeyChecking", "no");
				System.out.println("Password ::: " + password + "Host ::: " + host + "Port :: " + port + "UserName :: "
						+ username);
				System.out.println("Establishing Connection :: ");
				session.connect();
				System.out.println("Connection established :::  ");
				System.out.println("Creating SFTP Channel :: .");
				ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");

				sftpChannel.connect();

				if (files.isEmpty()) {
					info.setOperationCode(10);
					info.setMessage("Empty File");
					System.out.println("Empty File ::: ");
				} else {
					long timestamp = System.currentTimeMillis();

					String fullName = files.getOriginalFilename();
					int idx = fullName.lastIndexOf('.');
					String firstName = fullName.substring(0, idx);
					String lastName = fullName.substring(idx + 1);

					String filenameLast = firstName + "_" + timestamp + "." + lastName;
					System.out.println("filename1  " + filenameLast);

					if (campaignOperationType != null && campaignOperationType.equals("INSERT")) {
						System.out.println(" File Coping started  INSERT ::: ");
						byte[] bytes = files.getBytes();
						String insPath = env.getProperty("offline.segement.filepath.insert") + filenameLast;
						Files.write(Paths.get(insPath), bytes);
						info.setOperationCode(0);
						info.setMessage("Insert Success");
						System.out.println(" File Copied Done INSERT  ::: ");
					} else {
						System.out.println(" File Coping started  DELETE ::: ");
						byte[] bytes = files.getBytes();
						String insPath = env.getProperty("offline.segement.filepath.delete") + filenameLast;
						Files.write(Paths.get(insPath), bytes);
						info.setOperationCode(0);
						info.setMessage("Delete Success");
						System.out.println(" File Copied Done DELETE  ::: ");
					}
				}
			}

//					if (campaignOperationType != null && campaignOperationType.equals("INSERT")) {
//						System.out.println(" File Coping started  INSERT ::: ");
//						byte[] bytes = files.getBytes();
//						System.out.println("bytes  "  + bytes.toString());
//						System.out.println("file Path ::: " + env.getProperty("offline.segement.filepath.insert"));
//						Path path = Paths
//								.get(env.getProperty("offline.segement.filepath.insert") + files.getOriginalFilename());
//
//						System.out.println(" Path ::: " + path);
//						Files.write(path, bytes);
//						info.setOperationCode(0);
//						info.setMessage("Success");
//						System.out.println(" File Copied Done INSERT  ::: ");
//					} else {
//						System.out.println(" File Coping started  DELETE ::: ");
//						byte[] bytes = files.getBytes();
//						System.out.println("file Path ::: " + env.getProperty("offline.segement.filepath.delete"));
//						Path path = Paths
//								.get(env.getProperty("offline.segement.filepath.delete") + files.getOriginalFilename());
//						System.out.println(" Path ::: " + path);
//						Files.write(path, bytes);
//						info.setOperationCode(0);
//						info.setMessage("Success");
//						System.out.println(" File Copied Done  DELETE ::: ");
//					}

		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			info.setOperationCode(1);
			info.setMessage("Failure");
		}
		return info;

	}

	@PostMapping(MagikServicePath.INSERT_OR_DELETE_SINGLE)
	public @ResponseBody RestInfo<OfflineUploadSegmentTO> insertOrDeleteSingle(HttpServletRequest httpServletRequest,
			@RequestParam(value = "campaignOperationType") String campaignOperationType,
			@RequestParam(value = "mobileNumber") String mobileNumber, @RequestParam(value = "userId") String userId,
			@RequestParam(value = "microSegment") String microSegment) {
		RestInfo<OfflineUploadSegmentTO> info = new RestInfo<OfflineUploadSegmentTO>();
		OfflineUploadSegmentTO status;
		System.out.println("campaignOperationType  ::    " + campaignOperationType);
		System.out.println("mobileNumber  ::    " + mobileNumber);
		System.out.println("microSegment  ::    " + microSegment);
		System.out.println("userId  ::    " + userId);

		try {
			status = offlineUploadSegmentService.insertOrDeleteSingle(campaignOperationType, userId, mobileNumber,
					microSegment);
			System.out.println("getStatus  rest :  " + status.getStatus());
			System.out.println("getResposeCode  rest :  " + status.getResposeCode());
			System.out.println("getCount  rest :  " + status.getCount());
			if (status.getStatus() == "0") {
				if (campaignOperationType != null && campaignOperationType.equals("INSERT")) {
					System.out.println("INSIDE INSERT  :::  ");
					info.setOperationCode(0);
					info.setMessage(status.getCount());
					System.out.println("INSERTED :: " + status.getCount());
				} else {
					System.out.println("INSIDE DELETE  :::  ");
					info.setOperationCode(0);
					info.setMessage(status.getCount());
					System.out.println(" Deleted  ::: " + status.getCount());
				}
			} else {
				System.out.println("INSIDE else  :::  ");
				info.setOperationCode(1);
			}

		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			info.setOperationCode(1);
			info.setMessage("Failure");
		}
		return info;
	}
}
