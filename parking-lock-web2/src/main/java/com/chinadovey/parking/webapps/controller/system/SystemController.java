package com.chinadovey.parking.webapps.controller.system;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.chinadovey.parking.Constants;
import com.chinadovey.parking.acl.Login;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AjaxResult;
import com.chinadovey.parking.core.mvc.AjaxResult.Result;
import com.chinadovey.parking.webapps.utils.FileUtil;
import com.chinadovey.parking.webapps.utils.ImageUtils;
import com.chinadovey.parking.webapps.utils.ParkingUtil;
import com.chinadovey.parking.webapps.utils.StringUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/system")
public class SystemController {

	/*@Autowired
	private WechatConfigureBiz wechatConfigureBiz;*/

	/**
	 * 下载文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param path
	 *            文件夹
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/download")
	public String download(String path, String fileName, String type, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 设置文件下载目录
		String ctxPath = request.getSession().getServletContext().getRealPath(path) + File.separator;

		// 取得文件路径
		String downLoadPath = ctxPath + fileName + "." + type;
		System.err.println(downLoadPath);
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			long fileLength = new File(downLoadPath).length();

			// 设置头文件
			response.setContentType("application/x-msdownload");

			// 设置文件名和文件长度
			response.setHeader("Content-Disposition",
					"attachment;fileName=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + "." + type);
			response.setHeader("Content-Length", String.valueOf(fileLength));
			// 通过流写入文件
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
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
		return null;
	}

	/**
	 * 上传图片文件
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/upload")
	@ResponseBody
	public JSONObject upload(String data, String type, String path, HttpServletRequest request, Model model) {
		JSONObject json = new JSONObject();
		try {

			// 页面控件的文件流
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multiRequest.getFile("file");
			MultipartFile multipartFileCheck = multipartFile;

			// 获取文件的后缀 ,并设置文件名
			String fileNo = StringUtil.randomOrderNo();
			String fileName = fileNo + "." + type;

			// 设置文件上传路径
			path = ParkingUtil.getPicPath()+ path;
			File fileOriginal = new File(path, fileName);
			// 判断文件夹是否存在
			if (!fileOriginal.exists()) {
				fileOriginal.mkdirs();
			}

			// 验证文件是否存在
			String fileNameExist = FileUtil.checkAndGetFileExist(path, multipartFileCheck);
			if ("".equals(fileNameExist)) {
				// 上传原文件到文件夹
				multipartFile.transferTo(fileOriginal);
				fileNameExist = fileName;
			} else {
				fileOriginal.delete();
				String fileNameExistSub = fileNameExist;
				fileNo = fileNameExistSub.substring(0,
						fileNameExistSub.endsWith(".") ? fileNameExistSub.lastIndexOf(".") : 0);
			}
			uploadCutPicture(data, path, fileNo);
			json.put("fileName", fileNameExist);
			json.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", false);
			json.put("message", "上传失败");
		} finally {
		}
		return json;
	}

	public void uploadCutPicture(String data, String path, String fileNo) {
		if (data != null && !data.equals("")) {
			String fileNameCut = "cut" + fileNo + ".png";
			ImageUtils.GenerateImage(data, path + File.separator + fileNameCut);
		}
	}

	/**
	 * 根据serverId保存文件
	 * 
	 * @param serverId
	 * @param wechatId
	 * @return 保存成功返回文件名
	 */
	@RequestMapping("/saveFileByServerId")
	@ResponseBody
	public AjaxResult saveFileByServerId(String serverId, Integer wechatId, HttpServletRequest request,
			HttpServletResponse response) {
		return new AjaxResult(Result.FAIL);
		
	}

	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/{fileName}/picture")
	public String picture(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String path = ParkingUtil.getPicPath() + "picture";
		return ImageUtils.outPicture(fileName, response, path);
	}
}
