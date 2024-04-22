package com.hokhanh.admin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(Model model, Authentication authentication) {
		if(authentication != null) {
			return "redirect:/index";
		}
		model.addAttribute("title", "Trang đăng nhập");
		return "login";
	}
}
