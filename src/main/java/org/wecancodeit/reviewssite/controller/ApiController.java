package org.wecancodeit.reviewssite.controller;

import java.util.Collection;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wecancodeit.reviewssite.model.Doggo;
import org.wecancodeit.reviewssite.model.Tag;
import org.wecancodeit.reviewssite.repository.DoggoRepository;
import org.wecancodeit.reviewssite.repository.TagRepository;

@RestController
public class ApiController {

	@Autowired
	private TagRepository tagRepo;
	
	@Autowired
	private DoggoRepository reviewRepo;
	
	@GetMapping("/api/tags/{tagName}")
	public Tag getTag(@PathVariable(value = "tagName") String tagName) {
		return tagRepo.findByTagNameIgnoreCase(tagName);
	}
	
	@GetMapping("/api/doggos/{id}")
	public Doggo getDoggo(@PathVariable(value = "id") Long id) {
		return reviewRepo.findById(id).get();
	}
	
	@GetMapping("/api/doggos")
	public Iterable<Doggo> getDoggos( Long id) {
		return reviewRepo.findAll();
	}
	
	@PostMapping("/api/doggos/{id}/tags/add")
	public Collection<Tag> addTag(@PathVariable(value = "id") Long id, @RequestBody String body) throws JSONException {
		JSONObject json = new JSONObject(body);
		String newTag = json.getString("tagName");

		if(tagRepo.findByTagNameIgnoreCase(newTag) == null) {
			tagRepo.save(new Tag(newTag));
		}

		Doggo doggoToUpdate = reviewRepo.findById(id).get();
		doggoToUpdate.addTag(tagRepo.findByTagNameIgnoreCase(newTag));
		Doggo updatedDoggo = reviewRepo.save(doggoToUpdate);

		return updatedDoggo.getTags();
	}
}
