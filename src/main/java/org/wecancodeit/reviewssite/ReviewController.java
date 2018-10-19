package org.wecancodeit.reviewssite;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReviewController {

	@Resource
	private DoggoRepository reviewRepo;

	@GetMapping("/")
	public String getHome() {
		return "index";
	}

	@GetMapping("/doggos")
	public String getDoggos(Model model) {
		model.addAttribute("doggos", reviewRepo.findAll());
		return "reviews";
	}

	@GetMapping("doggos/{id}")
	public String getDoggo(@PathVariable(value = "id") Long id, Model model) {
		model.addAttribute("doggo", reviewRepo.findById(id));
		return "review";
	}
}
