package pawlowski.piotr.twitter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pawlowski.piotr.twitter.entity.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Long> {
	User findByEmail(String email);
	User findById(int id);
}