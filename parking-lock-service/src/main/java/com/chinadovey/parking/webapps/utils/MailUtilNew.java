package com.chinadovey.parking.webapps.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

public class MailUtilNew {
	@SuppressWarnings("restriction")
	public static void sendMail(String title, String content, String mail) {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.exmail.qq.com"); // 可以换上你的smtp主机名。
			props.put("mail.smtp.auth", "true"); // 身份验证，目前免费邮件服务器都需要这一项
			Session session = Session.getDefaultInstance(props, null);
			Transport transport = session.getTransport("smtp");
			Message message = new MimeMessage(session);
			message.setSubject( MimeUtility.encodeText(title,"gb2312","B")); //B为base64方式
			/*
			 * MimeMessage mimeMsg = new MimeMessage(session);
			 * sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
			 *  message.setSubject("=?gbk?B?" + enc.encode(title.getBytes())+ "?=");
			 */
			String mailUser = ConfUtils.getMailUsername();
			String mailPwd = ConfUtils.getMailPassword();
			transport.connect(mailUser, mailPwd);

			message.setFrom(new InternetAddress(mailUser));
			message.setSentDate(new Date());

			message.addRecipients(Message.RecipientType.TO,
					new InternetAddress[] { new InternetAddress(mail) });

			Multipart multipart = new MimeMultipart();

			MimeBodyPart contentPart = new MimeBodyPart();
			contentPart.setText(content);

			multipart.addBodyPart(contentPart);
			message.setContent(multipart);

			message.saveChanges();

			session.setDebug(true);

			transport.sendMessage(message, message.getAllRecipients());

			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*public static void main(String[] args) {
		sendMail("测试", "测试", "fenggj@huaching.com");
	}*/
}
