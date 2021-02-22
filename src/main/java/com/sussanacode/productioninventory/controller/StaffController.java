package com.sussanacode.productioninventory.controller;

import com.sussanacode.productioninventory.configuration.RoleRepository;
import com.sussanacode.productioninventory.configuration.StaffRepository;
import com.sussanacode.productioninventory.model.Role;
import com.sussanacode.productioninventory.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class StaffController {

    @Autowired
    private StaffRepository staffRepo;

    @Autowired
    private RoleRepository roleRepo;



    @GetMapping("/staffs")
    public String showStaffs(Model model) {

        List<Staff> staffList = staffRepo.findAll();
        model.addAttribute("staffList", staffList);

        return "staffs";

    }

    @GetMapping("/staffs/new")
    public String enterNewStaff(Model model) {
        List<Role> roleList = roleRepo.findAll();
        model.addAttribute("roleList", roleList);
        model.addAttribute("staff", new Staff());

        return "staffEntry";
    }

    @PostMapping("/staff/save")
    public String saveNewStaff( Staff staff) {

        //BCrypt the staff Password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = encoder.encode(staff.getPassword());
        staff.setPassword(rawPassword);

        staffRepo.save(staff);

        System.out.println(staff.getFullname() + " " + staff.getPassword());

        return "redirect:/staffs";
    }

    //edit staff by id
    @GetMapping("/staffs/edit/{id}")
    public String editNewStaff(@PathVariable("id") Integer id, Model model) {
        Staff staff = staffRepo.findById(id).get();
        List<Role> roleList = roleRepo.findAll();

        model.addAttribute("roleList", roleList);
        model.addAttribute("staff", staff);

        return "staffEntry";
    }


    @GetMapping("/staffs/delete/{id}")
    public String deleteStaff(@PathVariable("id") Integer id) {
        staffRepo.deleteById(id);

        return "redirect:/staffs";
    }

//    @GetMapping("/accessdenied")
//    public String error403() {
//        return "accessdenied";
//    }

}
