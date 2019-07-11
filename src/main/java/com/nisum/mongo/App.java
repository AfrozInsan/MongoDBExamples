package com.nisum.mongo;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.nisum.configuration.MongoDBConfiguration;
import com.nisum.model.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfiguration.class);
       MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
       
       User user = new User("afroz","afroz123");
       
      // mongoOperation.save(user);
       
       //user object will get the created Id
       System.out.println(user);
       
    // query to search user
   	Query searchUserQuery = new Query(Criteria.where("username").is("afroz"));

   	// find the saved user again.
/*   	User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
   	System.out.println("2. find - savedUser : " + savedUser);
*/
   	// update password
   	mongoOperation.updateFirst(searchUserQuery, 
                            Update.update("password", "new password"),User.class);

   	// find the updated user object
   	User updatedUser = mongoOperation.findOne(searchUserQuery, User.class);

   	System.out.println("3. updatedUser : " + updatedUser);

   	// delete
   	mongoOperation.remove(searchUserQuery, User.class);

   	// List, it should be empty now.
   	List<User> listUser = mongoOperation.findAll(User.class);
   	System.out.println("4. Number of user = " + listUser.size()+ listUser);

    }
}
