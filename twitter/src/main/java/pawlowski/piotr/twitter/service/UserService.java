package pawlowski.piotr.twitter.service;

import pawlowski.piotr.twitter.entity.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);

}
