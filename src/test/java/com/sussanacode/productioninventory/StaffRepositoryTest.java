package com.sussanacode.productioninventory;


import com.sussanacode.productioninventory.configuration.StaffRepository;
import com.sussanacode.productioninventory.model.Role;
import com.sussanacode.productioninventory.model.Staff;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class StaffRepositoryTest {

    @Autowired
    private StaffRepository staffRepo;

    //this will persist and get data from the database
    @Autowired
    private TestEntityManager entityManager;



    @Test
    public void testCreateRoles(){
        Role roleAdmin = new Role("Admin");
        Role roleSupervisor = new Role("Supervisor");
        Role roleStaff = new Role("Staff");

        entityManager.persist(roleAdmin);
        entityManager.persist(roleSupervisor);
        entityManager.persist(roleStaff);
    }

    @Test
    public void testCreateStaffRole(){
        Role roleAdmin = entityManager.find(Role.class, 1);
        Staff staff = new Staff("Sarah Banks", "sarahbanks@org.com", "banks2021");
        staff.addRole(roleAdmin);

        staffRepo.save(staff);

    }

    @Test
    public void testCreateStaffWithMultipleRole(){
        Role roleSupervisor = entityManager.find(Role.class, 2);
        Role roleStaff = entityManager.find(Role.class, 3);

        Staff staff = new Staff("Kevin Moose", "kevinmoose@org.com", "moose2021");

        staff.addRole(roleSupervisor);
        staff.addRole(roleStaff);

        staffRepo.save(staff);

    }

    @Test
    public void testAssignRoleToExistingStaff(){

        Staff staff = staffRepo.findById(1).get();
        Role roleStaff = entityManager.find(Role.class, 2);

        staff.addRole(roleStaff);

    }


    @Test
    public void testRemoveRoleToExistingStaff(){

        Staff staff = staffRepo.findById(1).get();
        Role roleStaff = new Role(2);

        staff.removeRole(roleStaff);

    }


    //insert new staff with a role using cascade type persist in the many-to-many mapping class
    @Test
    public void testCreateNewStaffWithaRole(){

        Role roleSalesPerson = new Role("SalesPerson");
        Staff staff = new Staff("John Oden", "johnoden@org.com", "odenj2021");
        staff.addRole(roleSalesPerson);

        staffRepo.save(staff);
    }


    //fetching many-to-many with fetch type eager to get the relation entity as well

    @Test
    public void testFetchStaff(){

        Staff staff = staffRepo.findById(2).get();
        entityManager.detach(staff);
        System.out.println(staff.getEmail());
        System.out.println(staff.getRoles());

    }

    @Test
    public void testDeleteStaff(){ staffRepo.deleteById(4); }



}
