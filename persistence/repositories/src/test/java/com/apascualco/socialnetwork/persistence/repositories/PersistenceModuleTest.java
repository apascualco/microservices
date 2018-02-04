package com.apascualco.socialnetwork.persistence.repositories;

import com.apascualco.socialnetwork.persistence.data.model.entities.UserCredential;
import com.apascualco.socialnetwork.persistence.data.model.entities.UserRoles;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assume.assumeTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ContextConfiguration(classes = {JPATestConfig.class}, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("test")
public class PersistenceModuleTest {

    @Autowired
    protected UserCredentialRepo userCredentialRepo;

    @Autowired
    protected UserRoleRepo userRoleRepo;


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    @DatabaseSetups(
            @DatabaseSetup("/datasets/userCredentialRepositoryTest/userRole.xml")
    )
    @DatabaseTearDowns(
            @DatabaseTearDown(value = "/datasets/userCredentialRepositoryTest/userRole.xml", type = DatabaseOperation.DELETE)
    )
    public void should_insert_userCredential_test() {
        int results = userCredentialRepo.findAll().size();
        assumeTrue(userCredentialRepo.findAll().size() == 0);

        List<UserRoles> userRoles = new LinkedList<>();
        userRoleRepo.findById(0L).ifPresent(userRoles::add);
        assumeTrue(userRoles.size() > 0);

        UserCredential userCredential = new UserCredential();
        userCredential.setUser("ejemplo@mail.com");
        userCredential.setPassword("HexadecimalMD5");
        userCredential.setUserRoles(userRoles);
        userCredential.setAuditLastUpdate(Calendar.getInstance());
        userCredential.setAuditUser("TESTING");
        userCredentialRepo.save(userCredential);

        assumeTrue(userCredentialRepo.findAll().size() == 1);
    }

    @Test
    @DatabaseSetups({
            @DatabaseSetup("/datasets/userCredentialRepositoryTest/userRole.xml"),
            @DatabaseSetup("/datasets/userCredentialRepositoryTest/userCredential.xml")
    })
    @DatabaseTearDowns({
            @DatabaseTearDown(value = "/datasets/userCredentialRepositoryTest/userRole.xml",  type = DatabaseOperation.DELETE),
            @DatabaseTearDown(value = "/datasets/userCredentialRepositoryTest/userCredential.xml", type = DatabaseOperation.DELETE)
    })
    public void should_delete_userCredential_test() {
        assumeTrue(userCredentialRepo.findAll().size() == 1);
        userCredentialRepo.deleteById(0L);
        assumeTrue(userCredentialRepo.findAll().size() == 0);
    }

}
