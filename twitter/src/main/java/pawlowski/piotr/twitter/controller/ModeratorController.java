package pawlowski.piotr.twitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pawlowski.piotr.twitter.entity.Tweet;
import pawlowski.piotr.twitter.repository.TweetRepository;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {

	private TweetRepository tweetRepository;
	
	@Autowired
	public ModeratorController(TweetRepository tweetRepository){
		this.tweetRepository = tweetRepository;
	}
	
	@RequestMapping(value="/panel", method = RequestMethod.GET)
	public String showModeratorPanel(Model model){
		List<Tweet> tweetList = tweetRepository.findFirst10ByOrderByIdAsc();
		model.addAttribute("tweetList", tweetList);
		return "/moderator/moderator-panel";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteTweet(@PathVariable Long id){
		Tweet tweet = tweetRepository.findOne(id);
		tweetRepository.delete(tweet);
		return "redirect:../../moderator/panel";
		
	}
	
	
	
}
