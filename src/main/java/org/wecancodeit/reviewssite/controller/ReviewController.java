package org.wecancodeit.reviewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.wecancodeit.reviewssite.model.Comment;
import org.wecancodeit.reviewssite.repository.CommentRepository;
import org.wecancodeit.reviewssite.repository.DoggoRepository;

@Controller
public class ReviewController {

	@Autowired
	private DoggoRepository reviewRepo;
	
	@Autowired
	private CommentRepository commentRepo;

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
		model.addAttribute("doggo", reviewRepo.findById(id).get());
		return "review";
	}
	
	@GetMapping("tags/{tagName}")
	public String getTag(@PathVariable(value = "tagName") String tagName) {
		return "tag";
	}
	
	@PostMapping("doggos/{id}/add-comment")
	public String addComment(@PathVariable(value = "id") Long id, String userName, String comment, Model model) {
		commentRepo.save(new Comment(userName, comment, reviewRepo.findById(id).get()));
		return "redirect:/doggos/" + id;
	}
	
}
