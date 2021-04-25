package com.sixdee.magik.services.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * @author : Ramesh Babu Cheerla
 * @category Upload File SFTP Process
 */
public class FileUploadSFTPProcess {

	private String sftpHost;
	private int sftpPort;
	private String sftpUserName;
	private String sftpPswd;
	private File uploadFile;
	private String sftpWorkingDir;
	private String uplaodFileName;
	private String remoteFileName;

	public FileUploadSFTPProcess(Map<String, String> serverDetailsMap, MultipartFile file) {

		this.sftpHost = serverDetailsMap.get("host");
		this.sftpPort = Integer.parseInt(serverDetailsMap.get("port"));
		this.sftpUserName = serverDetailsMap.get("username");
		this.sftpPswd = serverDetailsMap.get("password");
		this.sftpWorkingDir = serverDetailsMap.get("location");
		this.uplaodFileName = serverDetailsMap.get("fileName");
		this.remoteFileName = serverDetailsMap.get("remoteFileName");

		try {
			this.uploadFile = convert(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.uploadFile();
	}

	/*
	 * Upload file from one local to application server
	 */
	private void uploadFile() {

		System.out.println("======================== File upload FTP process start =================================");
		System.out.println("Host -------------------------> : " + sftpHost);
		System.out.println("Port -------------------------> : " + sftpPort);
		System.out.println("User Name --------------------> : " + sftpUserName);
		System.out.println("Password ---------------------> : " + sftpPswd);
		System.out.println("Upload File Name -------------> : " + uplaodFileName);
		System.out.println("Remote File Name -------------> : " + remoteFileName);
		System.out.println("Remote File Location ---------> : " + sftpWorkingDir);

		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;

		try {
			JSch jsch = new JSch();
			session = jsch.getSession(sftpUserName, sftpHost, sftpPort);
			session.setPassword(sftpPswd);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			System.out.println("Server connecting ..............");
			session.connect();
			System.out.println("Channel creating ..............");
			channel = session.openChannel("sftp");
			System.out.println("Channel connecting ..............");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			System.out.println("File copie directory creating ..............");
			channelSftp.cd(sftpWorkingDir);
			System.out.println("File copie process to target location ..............");
			channelSftp.put(new FileInputStream(uploadFile), uploadFile.getName());
			System.out.println("File uploaded successfully ..............");
			System.out
					.println("======================== File upload FTP process done =================================");
		} catch (Exception ex) {
			System.out.println("Exception on SFTP file upload process.....");
			ex.printStackTrace();
		}
	}

	/*
	 * Multipart File to File
	 */
	public File convert(MultipartFile file) throws IllegalStateException, IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
}
