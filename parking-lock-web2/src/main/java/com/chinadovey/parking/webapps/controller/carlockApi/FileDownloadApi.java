package com.chinadovey.parking.webapps.controller.carlockApi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.ServletContextFactoryBean;
import org.springframework.web.context.support.ServletContextScope;

import com.chinadovey.parking.acl.Login;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
@Controller
@RequestMapping("/file")
public class FileDownloadApi{
	private static final String  defaultPath = "excels";
	private static final String  defaultFileType = "xls";
	private static final String  defaultEncode ="utf-8";
	
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/downloadp")
	@ResponseBody
	public void download(HttpServletRequest request,HttpServletResponse response,
			String path,String fileName,String fileType) throws IOException {
		dl(request, response, path, fileName, fileType);
	}
	public void dl(HttpServletRequest request,HttpServletResponse response,
			String path,String fileName,String fileType) throws IOException{
		request.setCharacterEncoding(defaultEncode);
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			String ctx = request.getSession().getServletContext().getRealPath(path)+File.separator;
			String fileRealPath = ctx+fileName+"."+fileType;
			long fileLength = new File(fileRealPath).length();
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition",
					"attachment;fileName=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + "." + fileType);
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(fileRealPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while ((bytesRead = bis.read(buff, 0, buff.length)) > 0) {
				bos.write(buff, 0, bytesRead);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				bis.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
	}
	@RequestMapping("/download")
	@ResponseBody
	public void download(HttpServletRequest request,HttpServletResponse response,String fileName) throws IOException {
		download(request, response,defaultPath, fileName,defaultFileType);
	}

}



















