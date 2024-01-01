//package repository;
//
//import com.api.MvcApi.MvcApiApplicationTests;
//import com.api.MvcApi.domain.User;
//import com.api.MvcApi.domain.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class UserRepositoryTests extends MvcApiApplicationTests {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void create() {
//        User user = new User();
//
//        user.setId("test");
//        user.setUsername("jpaTest");
//        user.setPassword("123");
//
//        User newUser = userRepository.save(user);
//    }
//}