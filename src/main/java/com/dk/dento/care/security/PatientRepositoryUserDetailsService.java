package com.dk.dento.care.security;

import java.util.Collection;

import com.dk.dento.care.data.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.dk.dento.care.data.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientRepositoryUserDetailsService implements UserDetailsService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public PatientRepositoryUserDetailsService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByEmail(username);
        if(doctor == null) {
            throw new UsernameNotFoundException("Could not find doctor " + username);
        }
        return new CustomDoctorDetails(doctor);
    }

    private final static class CustomDoctorDetails extends Doctor implements UserDetails {

        private CustomDoctorDetails(Doctor doctor) {
            super(doctor);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        private static final long serialVersionUID = 5639683223516504866L;
    }
}
