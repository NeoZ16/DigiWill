package de.digiwill.repository;

import de.digiwill.model.*;
import de.digiwill.service.UserHandleManager;
import de.digiwill.util.SecurityHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserHandleRepositoryTest {

    @Autowired
    private UserHandleRepository repository;
    private UserHandleManager userHandleManager;

    @Before
    public void setUp() {
        userHandleManager = new UserHandleManager(repository);
        userHandleManager.createUsers(createUserHandles(5, Arrays.asList(
                new EmailAction(Arrays.asList("nobodyT@digiwill.de"), "Hey there!", false, "blalbalbla")
        )));
    }

    public static List<UserHandle> createUserHandles(int amount, List<BaseAction> actions) {
        List<UserHandle> users = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            PersonalData personalData = new PersonalData("no", "body" + i, new Date(2018, 1, 1), Address.getInitial());
            UserBooleans userBooleans = new UserBooleans(true, true, true, true);
            UserHandle userHandle = UserHandleFactory.createCompleteUserHandle("nobody" + i + "@digiwill.de", SecurityHelper.encodePassword("nobody" + i + "@digiwill.de"), new AuthoritySet(AuthorityUtils.createAuthorityList("ROLE_USER")),
                    userBooleans, UserTimestamps.getInitial(), UserDeltaTimes.getInitial(), false,
                    personalData, new ActionSet(actions, false));
            users.add(userHandle);
        }
        return users;
    }

    @Test
    public void findUserHandleByEmailAddressTest() {
        Assert.assertEquals("nobody1@digiwill.de", userHandleManager.loadUserByEmailAddress("nobody1@digiwill.de").getEmailAddress());
    }


}
