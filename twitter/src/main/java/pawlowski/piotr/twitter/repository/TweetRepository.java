package pawlowski.piotr.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawlowski.piotr.twitter.entity.Tweet;
import pawlowski.piotr.twitter.entity.User;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long>{
	List<Tweet> findByUser(User user);
}
