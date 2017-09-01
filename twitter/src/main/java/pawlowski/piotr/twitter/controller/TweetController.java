package pawlowski.piotr.twitter.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pawlowski.piotr.twitter.entity.Tweet;
import pawlowski.piotr.twitter.repository.TweetRepository;

@RequestMapping("/tweet")
@Controller
public class TweetController {

	TweetRepository tweetRepository;
	
	@Autowired
	public TweetController (TweetRepository tweetRepository){
		this.tweetRepository = tweetRepository;
	}
	
	@RequestMapping("/MainPage")
	public String showMainPage(){
		return "MainPage";
	}
	
	@GetMapping("/add")
	public String addNewTweet(Model model){
		model.addAttribute("tweet", new Tweet());
		return "AddTweet";
		
	}
	
	@PostMapping("/add")
	public String saveNewTweet(@ModelAttribute @Valid Tweet tweet, BindingResult result){
		if	(result.hasErrors())	{
			return	"AddTweet";
		}
		tweetRepository.save(tweet);
		return "redirect:list";
		
	}
	
	@RequestMapping("/list")
	public String showTweetList(Model model){
		List<Tweet> tweetList = tweetRepository.findAll();
		model.addAttribute("tweetList", tweetList);
		return "TweetList";
		
	}
	
}
