package com.chinadovey.parking.webapps.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件工具类 Copyright ©2014 中瑞华清（北京）智能科技有限公司 http://zhrhq.com
 * 
 * @author 王生栋 2015-6-18 下午2:39:51
 */
public final class FileUtil {

	/**
	 * MultipartFile 文件上传
	 * 
	 * @param filePath
	 * @param fileName
	 * @param file
	 */
	public static void uploadFile(String filePath, String fileName, MultipartFile file) {
		OutputStream os = null;
		InputStream is = null;
		File myFilePath = new File(filePath);
		try {
			if (file.isEmpty()) {
				return;
			}
			is = file.getInputStream();
			if (!myFilePath.exists()) {
				myFilePath.mkdirs();
			}
			os = new FileOutputStream(filePath + File.separator + fileName);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) > 0) {
				os.write(buffer, 0, len);
			}
			os.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (!myFilePath.exists()) {
					myFilePath.createNewFile();
				}
				if (os != null) {
					os.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 删除文件
	 * 
	 * @return
	 */
	public static String deleteFileByPath(String path) {
		if (path == null || "".equals(path)) {
			return null;
		}
		File file = new File(path);
		if (file.exists()) {
			if (file.isFile()) {
				if (file.delete()) {
					return "";
				} else {
					return "";
				}

			}
		}
		return null;
	}

	/**
	 * 通过uuid的方式生成新的文件名。
	 * 
	 * @param fileName
	 * @return
	 */
	public static String changeFileName(String fileName) {
		if (StringUtils.isNotBlank(fileName)) {
			int i = fileName.lastIndexOf(".");
			String ext = fileName.substring(i + 1);
			fileName = fileName.substring(0, i) + UUID.randomUUID().toString() + "." + ext;
			return fileName;
		}
		return null;
	}

	/**
	 * 根据md5检验是否存在相同的文件，返回文件名
	 * @param path 路径
	 * @param multipartFile 上传的文件流 
	 * @return 文件名
	 * @throws IOException 读取异常
	 * @throws FileNotFoundException 文件未找到
	 */
	public static String checkAndGetFileExist(String path, MultipartFile multipartFile)
			throws IOException, FileNotFoundException {
//		String md5File = DigestUtils.md5Hex(multipartFile.getInputStream());
//		System.err.println("md5校验码=" + md5File);
//		File files = new File(path);
//		if (files.isDirectory()) {
//			String[] filelist = files.list();
//			for (int i = 0; i < filelist.length; i++) {
//				File readfile = new File(path + File.separator + filelist[i]);
//				if (!readfile.isDirectory()) {
//					System.err.println("name=" + readfile.getName());
//					String md5ReadFile = DigestUtils.md5Hex(new FileInputStream(readfile));
//					System.err.println("md5校验码=" + md5ReadFile);
//					if (md5ReadFile.equals(md5File)) {
//						String fileName = readfile.getName();
//						return fileName;
//					}
//				}
//			}
//		}
		return "";
	}

	public static boolean createFile(String filePath) {
		boolean result = false;
		try {
			String folderPath = filePath.substring(filePath.lastIndexOf(File.separator));
			File folder = new File(folderPath);
			// 判断文件夹是否存在
			if (!folder.exists()) {
				folder.mkdirs();
			}

			File file = new File(filePath);
			if (file.exists()) {
				result = true;
			} else {
				result = file.createNewFile();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 判断文件是否存在
	 */
	public static boolean fileIsExists(String filePath) {

		boolean result = false;
		File file = new File(filePath);
		if (file.exists()) {
			result = true;
		} else {
			result = false;
		}

		return result;

	}
}
