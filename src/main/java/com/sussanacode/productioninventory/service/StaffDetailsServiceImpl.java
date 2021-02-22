package com.sussanacode.productioninventory.service;

import com.sussanacode.productioninventory.configuration.StaffRepository;
import com.sussanacode.productioninventory.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class StaffDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StaffRepository staffRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Staff staff = staffRepo.findByEmail(email);

        if(staff == null){
            throw new UsernameNotFoundException("Could not find a staff with this email " + email);
        }
        return new StaffDetails(staff);
    }
}
