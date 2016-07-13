package org.utn.marvellator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.utn.marvellator.model.Role;
import org.utn.marvellator.model.User;
import org.utn.marvellator.repository.UserRepository;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
 
    boolean alreadySetup = false;
 
    @Autowired
		private UserRepository userRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
				Date date=new Date();
				Timer timer = new Timer();

				timer.schedule(new TimerTask(){
					public void run(){
						System.out.println("Im Running..."+new Date());
					}
				},date, 24*60*60*1000);
				User admin = userRepository.findFirstByUserName("ADMIN");
				if (admin != null)
					return;
        User user = new User();
				user.setName("ADMIN");
				user.setUserName("ADMIN");
				user.setPassword(new BCryptPasswordEncoder().encode("111111"));
        user.setEmail("ADMIN@admin");
        user.setRole(Role.ADMIN);
        userRepository.save(user);

        alreadySetup = true;
    }
}