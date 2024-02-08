package com.ten31f.queens.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ten31f.queens.action.simple.Generator;

@Controller
public class VisualizerController {

	@Value("${spring.application.name}")
	String appName;

	@GetMapping("/growth")
	public String growth(Model model) {

		List<Set<int[]>> sets = new ArrayList<>();

		
		Generator.generate(4);
		
		
		sets.add(Actions.permute(4).stream().filter(Actions::validate).collect(Collectors.toSet()));

		for (int x = 5; x < 8; x++) {
			sets.add(Actions.expand(sets.get(sets.size() - 1)));
		}

		model.addAttribute("appName", appName);
		model.addAttribute("sets", sets);

		return "visualizer";
	}

}
