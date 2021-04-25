package com.sixdee.magik.services.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.sixdee.magik.services.model.AttachedFileTransientTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */
 
public class FileUploadUtil {
	
	  
	 public static void saveFilesToServerFolder(String mainFolder_PATH,MultipartFile orginalFile,String unique_fileName,Boolean checkBeforeUpdating)
	 {
		 try 
		 {
				 File file = new File(mainFolder_PATH); 
					
					if(!checkBeforeUpdating)
					{
						//At the time of create
						if (!file.exists()) 
							file.mkdirs();
					}
					else
					{	
						//At the time of Update
						if (!file.exists()) 
							file.mkdirs();
						   else 
						   {
							  //FileUtils.forceDelete(new File(server_PATH));
								String[]entries = file.list();
								for(String s: entries){
								    File currentFile = new File(file.getPath(),s);
								    currentFile.delete();
								}
						   }
					}
					
					//Files.copy(fileObj.getInputStream(), Paths.get(folderPath+fileObj.getName()), StandardCopyOption.REPLACE_EXISTING);
					String filename=orginalFile.getOriginalFilename().trim();
					String ext=filename.substring(filename.lastIndexOf("."));
					
			    	File  newfile = new File(file.getAbsolutePath()+ "\\"+unique_fileName+ext);
			    	
			    	if (!newfile.exists())
							newfile.createNewFile();
				
			    	orginalFile.transferTo(newfile);
		 }
		 catch (IOException e) {e.printStackTrace();}
	 

	 }

	public static List<AttachedFileTransientTO>  retrieveFilesFromServer(String FILE_PATH) throws Exception
	{
		File[] destinationFIleList=null;
		List<AttachedFileTransientTO> files = new ArrayList<>();   
        try
		{
			String FileUploadLocation = FILE_PATH;
			String folderPath = FileUploadLocation;
			File file = new File(folderPath); 
			if (!file.exists()) 
				file.mkdirs();
			
			destinationFIleList=file.listFiles();
			for(File physicalFile :destinationFIleList)
			{
				
					String filepath=physicalFile.getPath().trim();
					String filename=filepath.substring(filepath.indexOf("-")+1); //backend Filename: autoId-filename +  extension
			    	String ext=filename.substring(filename.lastIndexOf("."));
				   
					File sourceFile = new File(physicalFile.getAbsolutePath());
					String encodeFile=encodeFileToBase64Binary(sourceFile);
					
					   
					
//					MultipartFile multipartFile = new MockMultipartFile(sourceFile.getPath(), new FileInputStream(new File(sourceFile.getPath())));
					
					AttachedFileTransientTO attachedFileTransientTO = new AttachedFileTransientTO();
					if(sourceFile.getPath()!=null && (
							        sourceFile.getPath().trim().toLowerCase().contains(".jpg")  || 
									sourceFile.getPath().trim().toLowerCase().contains(".jpeg") ||
									sourceFile.getPath().trim().toLowerCase().contains(".webp"))   )
									{
								                 attachedFileTransientTO.setFileType("IMAGE");
								                 attachedFileTransientTO.setMimeType("image/jpeg");
								                 
									}
					else if(sourceFile.getPath()!=null && ( sourceFile.getPath().trim().toLowerCase().contains(".png")))
					{
						 attachedFileTransientTO.setFileType("IMAGE_PNG");
		                 attachedFileTransientTO.setMimeType("image/png");
						
					}
					
					else if(sourceFile.getPath()!=null && (
					        sourceFile.getPath().trim().toLowerCase().contains(".mp4")  || 
							sourceFile.getPath().trim().toLowerCase().contains(".mpeg") ||
							sourceFile.getPath().trim().toLowerCase().contains(".avi")  || 
							sourceFile.getPath().trim().toLowerCase().contains(".webm") || 
							sourceFile.getPath().trim().toLowerCase().contains(".mov") )
							)
							{
						        attachedFileTransientTO.setFileType("VIDEO");
						        attachedFileTransientTO.setMimeType("video/mp4");
						                 
							}
					else if(sourceFile.getPath()!=null && (
					        sourceFile.getPath().trim().toLowerCase().contains(".mp3") )
							)
							{
						        attachedFileTransientTO.setFileType("AUDIO");
						        attachedFileTransientTO.setMimeType("audio/mp3");
						                 
							}
					else
					{
						attachedFileTransientTO.setFileType("TEXT");
						 attachedFileTransientTO.setMimeType("@file/octet-stream");
					}
					
					
					
						attachedFileTransientTO.setEncodedFile_STRING(encodeFile);
						attachedFileTransientTO.setFileName(filename);
						files.add(attachedFileTransientTO);
					
					
			}
				
		}
		catch(Exception exception){exception.printStackTrace();}
		return  files;
	}
	
	 public static  String encodeFileToBase64Binary(File file) throws Exception{
         FileInputStream fileInputStreamReader = new FileInputStream(file);
         byte[] bytes = new byte[(int)file.length()];
         fileInputStreamReader.read(bytes);
         return new String(Base64.encodeBase64(bytes), "UTF-8");
     }
	 
		public static String getUtf8Conversion(String word) 
		{
			String decoded = "";
			for (Integer index = 0; index < word.length(); index++) {
				String charAt = "" + word.charAt(index);
				if (charAt.equals("&") && index < word.length() && ("" + word.charAt(index + 1)).equals("#")) {
					try {
						Integer length = word.indexOf(";", index);
						String sub = word.substring(index + 2, length);
						decoded += Character.toString((char) Integer.parseInt(sub));
						index = length;
					} catch (Exception ex) {
						decoded += charAt;
					}
				} else {
					decoded += charAt;
				}
			}
			return decoded;
		}
	
	 
}
