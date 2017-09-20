package pawlowski.piotr.twitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@RequestMapping(value="/panel", method = RequestMethod.GET)
	public String showAdminPanel(Model model){
		return "admin/admin-panel";
	}

}
