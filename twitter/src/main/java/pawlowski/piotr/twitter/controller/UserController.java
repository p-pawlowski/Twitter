package pawlowski.piotr.twitter.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pawlowski.piotr.twitter.entity.Tweet;
import pawlowski.piotr.twitter.entity.User;
import pawlowski.piotr.twitter.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService){
		this.userService = userService;
	}
	
	@RequestMapping(value={"/panel"}, method = RequestMethod.GET)
	public String userPanel(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<Tweet> tweetList = userService.findTweetListByUser(user);
		model.addAttribute("tweetList", tweetList);
		model.addAttribute("user", user);
		return "user/panel";
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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		tweet.setUser(user);
		tweetRepository.save(tweet);
		return "redirect:user/panel";
		
	}
	
	@RequestMapping("/list")
	public String showTweetList(Model model){
		List<Tweet> tweetList = tweetRepository.findAll();
		model.addAttribute("tweetList", tweetList);
		return "TweetList";
		
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteTweet(@PathVariable Long id){
		Tweet tweet = tweetRepository.findOne(id);
		tweetRepository.delete(tweet);
		return "redirect:../list";
		
	}

}
