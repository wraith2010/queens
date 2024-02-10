package com.ten31f.queens.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ten31f.queens.boardtools.Simplifiier;
import com.ten31f.queens.values.KNOWNSOLUTIONS;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller
public class BruteForceController {

	@Value("${spring.application.name}")
	String appName;

	@GetMapping("/brute/{n}")
	public String brutePage(@PathVariable("n") int n, Model model) {

		Actions.permute(n);

		Set<List<Integer>> games = Actions.permute(n).stream().filter(Actions::validate)
				.map(Simplifiier::findLowestOrderBoard).map(Arrays::asList).collect(Collectors.toSet());

		model.addAttribute("appName", appName);
		model.addAttribute("fundamental", KNOWNSOLUTIONS.FUNDAMENTAL);
		model.addAttribute("all", KNOWNSOLUTIONS.ALL);
		model.addAttribute("games", games);
		model.addAttribute("n", n);

		return "brute";
	}
}
