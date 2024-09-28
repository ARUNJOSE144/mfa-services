package com.inno.mfa.services.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.inno.mfa.services.model.OIDataTo;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Service
public class OIDAO {

	static final Logger logger = Logger.getLogger(OIDAO.class);

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-SSS");
	Format f = new SimpleDateFormat("EEEE");
	Format f_date = new SimpleDateFormat("dd");

	private static final SimpleDateFormat FILE_DATE_FORMAT = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");

	@Value("${OI_FILES_PATH}")
	private String oiFilePath;

	public void getOIData(OIDataTo oiDataTo) {
		String filePath =oiFilePath;
		File directory = new File(filePath);

		// Get the configured file prefix
		String filePrefix = oiDataTo.getSymbol();

		// Find the latest file
		Optional<File> latestFile = findLatestFile(directory, filePrefix);

		if (latestFile != null) {
			try {
				System.out.println("latestFile.get().getName() : " + latestFile.get().getName());
				String content = readFileToString(filePath + "/" + latestFile.get().getName());
				System.out.println("Content : " + content);
				oiDataTo.setData(content);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static Optional<File> findLatestFile(File directory, String filePrefix) {
		// List all files in the directory that match the prefix
		File[] files = directory.listFiles((dir, name) -> name.startsWith(filePrefix));

		if (files == null || files.length == 0) {
			return Optional.empty(); // No files found
		}

		// Sort the files by the timestamp part of the file name
		return Arrays.stream(files).max(Comparator.comparing(OIDAO::extractTimestamp));
	}

	private static Date extractTimestamp(File file) {
		try {
			// Extract the timestamp part of the file name (remove the prefix and the
			// underscore)
			String fileName = file.getName();
			String timestampPart = fileName.substring(fileName.lastIndexOf("_") - 19, fileName.length());

			// Parse the timestamp into a Date object
			return FILE_DATE_FORMAT.parse(timestampPart);
		} catch (Exception e) {
			e.printStackTrace();
			return new Date(0); // Return epoch time in case of failure
		}
	}

	public static String readFileToString(String filePath) throws IOException {
		// Read the file content as a single String
		return new String(Files.readAllBytes(Paths.get(filePath)));
	}

}
