package pawlowski.piotr.twitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pawlowski.piotr.twitter.entity.User;
import pawlowski.piotr.twitter.service.UserService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	private UserService userService;
	
	@Autowired
	public AdminController(UserService userService){
		this.userService = userService;
	}
	
	
	@RequestMapping(value="/panel", method = RequestMethod.GET)
	public String showAdminPanel(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<User> usersList = userService.findAllUsers();
		model.addAttribute("usersList", usersList);
		model.addAttribute("user", user);
		return "admin/admin-panel";
	}

}
