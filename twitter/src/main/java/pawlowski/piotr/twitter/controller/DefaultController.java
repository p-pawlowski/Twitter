package pawlowski.piotr.twitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pawlowski.piotr.twitter.entity.Tweet;
import pawlowski.piotr.twitter.entity.User;
import pawlowski.piotr.twitter.repository.TweetRepository;
import pawlowski.piotr.twitter.service.UserService;

@Controller
public class DefaultController {
	
	private TweetRepository tweetRepository;
	private UserService userService;
	
	@Autowired
	public DefaultController(TweetRepository tweetRepository, UserService userService) {
		this.tweetRepository = tweetRepository;
		this.userService = userService;
	}
	
	@RequestMapping(value="/access-denied")
	public String accessDenied(){
		return "access-denied";
	}
	
	@RequestMapping(value="/")
	public String mainPage(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
//		if (user == null){
//			user = new User();
//		}
		model.addAttribute("user", user);
		List<Tweet> tweetList = (List<Tweet>) tweetRepository.findFirst10ByOrderByIdAsc();
		model.addAttribute("tweetList", tweetList);
		return "main-page";
	}
}
