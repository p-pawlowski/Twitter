package pawlowski.piotr.twitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pawlowski.piotr.twitter.entity.Tweet;
import pawlowski.piotr.twitter.repository.TweetRepository;

@Controller
public class DefaultController {
	
	TweetRepository tweetRepository;
	
	@Autowired
	public DefaultController(TweetRepository tweetRepository) {
		this.tweetRepository = tweetRepository;
	}
	
	@RequestMapping(value="/access-denied")
	public String accessDenied(Model model){
		return "access-denied";
	}
	
	@RequestMapping(value="/")
	public String mainPage(Model model){

		List<Tweet> tweetList = (List<Tweet>) tweetRepository.findFirst10ByOrderByIdAsc();
		model.addAttribute("tweetList", tweetList);
		return "main-page";
	}
}
