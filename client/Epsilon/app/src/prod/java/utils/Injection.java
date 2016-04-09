package utils;

import com.epsilon.models.category.CategoryRepository;
import com.epsilon.models.category.CategoryRepositoryApiImpl;
import com.epsilon.models.course.CourseRepository;
import com.epsilon.models.course.CourseRepositoryApiImpl;
import com.epsilon.models.user.UserRepository;
import com.epsilon.models.user.UserRepositoryApiImpl;

/**
 * Created by Dandoh on 4/9/16.
 */
public class Injection {

    public static UserRepository provideUserRepository() {
        return UserRepositoryApiImpl.getInstance();
    }

    public static CategoryRepository provideCategoryRepository() {
        return CategoryRepositoryApiImpl.getInstance();
    }

    public static CourseRepositoryApiImpl provideCourseRepository() {
        return CourseRepositoryApiImpl.getInstance();
    }
}
