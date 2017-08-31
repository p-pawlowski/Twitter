package pawlowski.piotr.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawlowski.piotr.twitter.entity.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long>{
}
