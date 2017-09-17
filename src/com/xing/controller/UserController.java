package com.xing.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.support.RequestContext;

import com.xing.domain.User;

/**
 * @author xpengfei
 * @creat 2:55:44 PM Sep 17, 2017
 */
@Controller
public class UserController {
	@RequestMapping(value = "/{formName}")
	public String loginForm(@PathVariable String formName,
			String request_locale, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("request_locale=" + request_locale);
		/*
		 * //ʹ��SessionlocaleResolverʵ�ֹ��ʻ� // * public String
		 * loginForm(@PathVariable String formName, // String request_locale,
		 * Model model, HttpServletRequest request) if (request_locale != null)
		 * { // �������Ļ��� if (request_locale.equals("zh_CN")) { Locale locale = new
		 * Locale("zh", "CN"); request.getSession().setAttribute(
		 * SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale); } else
		 * if (request_locale.equals("en_US")) { // ����Ӣ�Ļ��� Locale locale = new
		 * Locale("en", "US"); request.getSession().setAttribute(
		 * SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale); } else
		 * { // ʹ�õ�ǰ�����Ի��� request.getSession().setAttribute(
		 * SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
		 * LocaleContextHolder.getLocale()); } }
		 */
		// ʹ��CookieLocaleResolverʵ�ֹ��ʻ�

		if (request_locale != null) {
			// �������Ļ���
			if (request_locale.equals("zh_CN")) {
				Locale locale = new Locale("zh", "CN");
				(new CookieLocaleResolver()).setLocale(request, response,
						locale);
			} else if (request_locale.equals("en_US")) {
				// ����Ӣ�Ļ���
				Locale locale = new Locale("en", "US");
				(new CookieLocaleResolver()).setLocale(request, response,
						locale);
			} else {
				// ʹ�õ�ǰ�����Ի���
				(new CookieLocaleResolver()).setLocale(request, response,
						LocaleContextHolder.getLocale());
			}
		}

		User user = new User();
		model.addAttribute("user", user);
		// ��̬��תҳ��
		return formName;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute @Validated User user, Model model,
			HttpServletRequest request) {
		// �����¼����xing������123456����֤ͨ��
		if (user.getLoginname() != null && user.getLoginname().equals("xing")
				&& user.getPassword() != null
				&& user.getPassword().equals("123456")) {
			// �Ӻ�̨�����ȡ���ʻ���Ϣusername
			RequestContext requestContext = new RequestContext(request);
			String username = requestContext.getMessage("username");

			// ����ȡ��username��Ϣ���õ�User����,����ӵ�Model��
			user.setUsername(username);
			model.addAttribute("user", user);
			return "success";
		}

		return "error";
	}
}
