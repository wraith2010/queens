package com.ten31f.queens.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ten31f.queens.boardtools.Permutator;
import com.ten31f.queens.boardtools.Validator;

@Controller
public class GrowthController {

	@Value("${spring.application.name}")
	String appName;

	@GetMapping("/growth")
	public String growth(Model model) {

		List<Set<Integer[]>> sets = new ArrayList<>();

		sets.add(Permutator.permute(4).stream().filter(Validator::validate).collect(Collectors.toSet()));
		sets.add(Permutator.permute(5).stream().filter(Validator::validate).collect(Collectors.toSet()));
		sets.add(Permutator.permute(6).stream().filter(Validator::validate).collect(Collectors.toSet()));
		sets.add(Permutator.permute(7).stream().filter(Validator::validate).collect(Collectors.toSet()));
		sets.add(Permutator.permute(8).stream().filter(Validator::validate).collect(Collectors.toSet()));
		sets.add(Permutator.permute(9).stream().filter(Validator::validate).collect(Collectors.toSet()));
		sets.add(Permutator.permute(10).stream().filter(Validator::validate).collect(Collectors.toSet()));

		model.addAttribute("appName", appName);
		model.addAttribute("sets", sets);

		return "growth";
	}

}
