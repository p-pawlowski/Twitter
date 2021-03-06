package pawlowski.piotr.twitter.service;

import java.util.List;

import pawlowski.piotr.twitter.entity.Tweet;
import pawlowski.piotr.twitter.entity.User;

public interface UserService {
	public User findUserByEmail(String email);
	public User findUser(int id);
	public List<User> findAllUsers();
	public void saveUser(User user);
	public List<Tweet> findTweetListByUser(User user);
	public void deleteUser(int id);

}
