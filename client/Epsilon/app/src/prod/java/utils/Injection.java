package utils;

import com.epsilon.models.user.UserRepository;
import com.epsilon.models.user.UserRepositoryApiImpl;

/**
 * Created by Dandoh on 4/9/16.
 */
public class Injection {

    public static UserRepository provideUserRepository() {
        return UserRepositoryApiImpl.getInstance();
    }
}
