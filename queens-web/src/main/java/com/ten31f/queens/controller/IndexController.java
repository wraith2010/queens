package com.ten31f.queens.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ten31f.queens.values.KNOWNSOLUTIONS;

import lombok.Getter;
import lombok.Setter;

@Controller
@Getter
@Setter
public class IndexController {

	@Value("${spring.application.name}")
	String appName;

	@GetMapping("/")
	public String homePage(Model model) {

		model.addAttribute("appName", appName);
		model.addAttribute("knowSolutions",KNOWNSOLUTIONS.FUNDAMENTAL);

		return "index";
	}

}
