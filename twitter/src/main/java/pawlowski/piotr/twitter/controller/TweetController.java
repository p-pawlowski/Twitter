package pawlowski.piotr.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pawlowski.piotr.twitter.entity.Tweet;
import pawlowski.piotr.twitter.repository.TweetRepository;

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
	
	@RequestMapping("/addTweet")
	public String addNewTweet(){
		Tweet tweet = new Tweet();
		tweet.setText("nowy Tweet");
		tweetRepository.save(tweet);
		return "redirect:MainPage";
		
	}
	
}
