package pawlowski.piotr.twitter.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
		return "user/user-panel";
	}
	
	@RequestMapping(value={"/delete/{id}"}, method = RequestMethod.GET)
	public String userPanel(@PathVariable int id, Model model){
		userService.deleteUser(id);
		return "redirect:../../admin/panel";
	}
	
}
