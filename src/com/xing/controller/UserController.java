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
		 * //使用SessionlocaleResolver实现国际化 // * public String
		 * loginForm(@PathVariable String formName, // String request_locale,
		 * Model model, HttpServletRequest request) if (request_locale != null)
		 * { // 设置中文环境 if (request_locale.equals("zh_CN")) { Locale locale = new
		 * Locale("zh", "CN"); request.getSession().setAttribute(
		 * SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale); } else
		 * if (request_locale.equals("en_US")) { // 设置英文环境 Locale locale = new
		 * Locale("en", "US"); request.getSession().setAttribute(
		 * SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale); } else
		 * { // 使用当前的语言环境 request.getSession().setAttribute(
		 * SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
		 * LocaleContextHolder.getLocale()); } }
		 */
		// 使用CookieLocaleResolver实现国际化

		if (request_locale != null) {
			// 设置中文环境
			if (request_locale.equals("zh_CN")) {
				Locale locale = new Locale("zh", "CN");
				(new CookieLocaleResolver()).setLocale(request, response,
						locale);
			} else if (request_locale.equals("en_US")) {
				// 设置英文环境
				Locale locale = new Locale("en", "US");
				(new CookieLocaleResolver()).setLocale(request, response,
						locale);
			} else {
				// 使用当前的语言环境
				(new CookieLocaleResolver()).setLocale(request, response,
						LocaleContextHolder.getLocale());
			}
		}

		User user = new User();
		model.addAttribute("user", user);
		// 动态跳转页面
		return formName;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute @Validated User user, Model model,
			HttpServletRequest request) {
		// 如果登录名是xing密码是123456则验证通过
		if (user.getLoginname() != null && user.getLoginname().equals("xing")
				&& user.getPassword() != null
				&& user.getPassword().equals("123456")) {
			// 从后台代码获取国际化信息username
			RequestContext requestContext = new RequestContext(request);
			String username = requestContext.getMessage("username");

			// 将获取的username信息设置到User对象,并添加到Model中
			user.setUsername(username);
			model.addAttribute("user", user);
			return "success";
		}

		return "error";
	}
}
