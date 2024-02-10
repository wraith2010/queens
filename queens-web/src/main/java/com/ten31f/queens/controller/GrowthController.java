package com.ten31f.queens.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GrowthController {

	@Value("${spring.application.name}")
	String appName;

	@GetMapping("/growth")
	public String growth(Model model) {

		List<Set<Integer[]>> sets = new ArrayList<>();

		sets.add(Actions.permute(4).stream().filter(Actions::validate).collect(Collectors.toSet()));
		sets.add(Actions.permute(5).stream().filter(Actions::validate).collect(Collectors.toSet()));
		sets.add(Actions.permute(6).stream().filter(Actions::validate).collect(Collectors.toSet()));
		sets.add(Actions.permute(7).stream().filter(Actions::validate).collect(Collectors.toSet()));
		sets.add(Actions.permute(8).stream().filter(Actions::validate).collect(Collectors.toSet()));
		sets.add(Actions.permute(9).stream().filter(Actions::validate).collect(Collectors.toSet()));
		sets.add(Actions.permute(10).stream().filter(Actions::validate).collect(Collectors.toSet()));

		model.addAttribute("appName", appName);
		model.addAttribute("sets", sets);

		return "growth";
	}

}
