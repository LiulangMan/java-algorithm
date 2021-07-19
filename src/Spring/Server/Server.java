package Spring.Server;

import Spring.Factory.BeanFactory;
import Spring.User.User;
import org.springframework.stereotype.Service;

@Service
public class Server {
    private User user = (User) BeanFactory.ioc.get("userUser");
    public Server() {
    }

    public User queryUserById(int id) {
        return user.queryUserById(1);
    }
}
