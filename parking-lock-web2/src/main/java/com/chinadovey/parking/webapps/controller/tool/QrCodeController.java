package com.chinadovey.parking.webapps.controller.tool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadovey.parking.acl.Login;
import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.mvc.AbstractBaseController;
import com.chinadovey.parking.core.mvc.AjaxResult;
import com.chinadovey.parking.core.mvc.AjaxResult.Result;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/tool/qrcode")
public class QrCodeController extends AbstractBaseController {

/*	@Autowired
	private QrCodeBiz qrCodeBiz;

	@Autowired
	private WechatConfigureBiz wechatConfigureBiz;

	@Autowired
	private WechatCodeBiz wechatCodeBiz;

	@Autowired
	private BusinessDiscountBiz businessDiscountBiz;*/

	/**
	 * 根据code和url保存二维码
	 * 
	 * @param code
	 * @param url
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("save")
	@ResponseBody
	public AjaxResult save(String code, String url) {
		try {
//			code = qrCodeBiz.save(url, code);
			if ("".equals(code)) {
				return new AjaxResult(Result.FAIL, "null");
			}
			return new AjaxResult(Result.SUCCESS, code);
		} catch (Exception e) {
			return new AjaxResult(Result.FAIL);
		}
	}

	/**
	 * 保存用餐链接
	 * 
	 * @param code
	 * @param id
	 * @param wechatId
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("saveEat")
	@ResponseBody
	public AjaxResult saveEat(String code, Integer id, Integer wechatId) {
		return null;
		/*try {
			WechatConfigure wechatConfigure = wechatConfigureBiz.find(wechatId);
			BusinessDiscountSubBase businessDiscount = businessDiscountBiz.findSub(id);
			if (businessDiscount == null) {
				return new AjaxResult(Result.FAIL, "noBusinessDiscount");
			}
			String webPath = wechatCodeBiz.getValueByKey(WechatConfigureConstant.WEB_PATH, wechatId);
			String wechatUrl = URLEncoder.encode(webPath, "UTF-8");
			Business business = businessDiscount.getBusiness();
			if (business == null || business.getId() == null) {
				return new AjaxResult(Result.FAIL, "noBusiness");
			}
			if (business.getParkId() == null) {
				return new AjaxResult(Result.FAIL, "noParkId");
			}
			Discount discount = businessDiscount.getDiscount();
			if (discount == null || discount.getId() == null) {
				return new AjaxResult(Result.FAIL, "noDiscount");
			}
			Integer parkId = business.getParkId();
			String businessNo = business.getNumber();
			String discountNo = discount.getNumber();
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wechatConfigure.getAppId()
					+ "&redirect_uri=" + wechatUrl + "%2fpreferential" + "%2f" + "eatPaymentView" + "%3fparkId%3d"
					+ parkId + "%26id%3d" + wechatId + "%26businessNo%3d" + businessNo + "%26discountNo%3d" + discountNo
					+ "&response_type=code&scope=snsapi_base";
			if (code == null || "".equals(code)) {
				code = qrCodeBiz.save(url);
			} else {
				code = qrCodeBiz.save(url, code);
			}
			if ("".equals(code)) {
				return new AjaxResult(Result.FAIL, "null");
			}
			return new AjaxResult(Result.SUCCESS, code);
		} catch (Exception e) {
			logger.error("保存二维码配置失败", e);
			return new AjaxResult(Result.FAIL);
		}*/
	}

	/**
	 * 得到url
	 * 
	 * @param code
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("getUrl")
	@ResponseBody
	public String getUrl(String code) {
		try {
			/*String url = qrCodeBiz.getUrl(code);
			url = QrCodeBiz.QR_CODE_URL + URLEncoder.encode(url, "UTF-8");
			return url;*/
		} catch (Exception e) {
			return "";
		}
		return code;
	}

	/**
	 * 得到微信url
	 * 
	 * @param code
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("getWechatUrlById")
	@ResponseBody
	public String getWechatUrlById(String code, Integer id) {
		try {
			/*String qrurl = "/qrCode/jump?code=";
			String webPath = wechatCodeBiz.getValueByKey(WechatConfigureConstant.WEB_PATH, id);
			String url = webPath + qrurl + code;
			url = QrCodeBiz.QR_CODE_URL + URLEncoder.encode(url, "UTF-8");
			return url;*/
		} catch (Exception e) {
			return "";
		}
		return code;
	}

	/**
	 * 得到微信url
	 * 
	 * @param code
	 * @return
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("getWechatUrlByWechatUrl")
	@ResponseBody
	public String getWechatUrlByWechatUrl(String code, String wechatUrl) {
		try {
			/*String url = wechatUrl + code;
			url = QrCodeBiz.QR_CODE_URL + URLEncoder.encode(url, "UTF-8");
			return url;*/
		} catch (Exception e) {
			return "";
		}
		return wechatUrl;
	}

	/**
	 * 根据url
	 * @param code
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SecurityAccessCheckable(resource = Login.class)
	@RequestMapping("/downloadByUrl")
	public String downloadByUrl(String code, Integer id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		/*try {
			String qrurl = "/qrCode/jump?code=";
			String webPath = wechatCodeBiz.getValueByKey(WechatConfigureConstant.WEB_PATH, id);
			String url = webPath + qrurl + code;
			url = QrCodeBiz.QR_CODE_URL + URLEncoder.encode(url, "UTF-8");
			URL urla = new URL(url);
			URLConnection uc = urla.openConnection();
			uc.setConnectTimeout(3 * 1000);
			int contentLength = uc.getContentLength(); // 保存内容长度

			// 设置头文件
			response.setContentType("application/x-msdownload");

			// 设置文件名和文件长度
			response.setHeader("Content-Disposition",
					"attachment;fileName=" + new String("二维码".getBytes("utf-8"), "ISO8859-1") + ".png");
			response.setHeader("Content-Length", String.valueOf(contentLength));
			// 通过流写入文件
			bis = new BufferedInputStream(uc.getInputStream());
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
			}*/
		return null;
		}

}
