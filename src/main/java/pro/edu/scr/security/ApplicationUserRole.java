package pro.edu.scr.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static pro.edu.scr.security.ApplicationUserPermission.*;

public enum  ApplicationUserRole {

    DOCTOR_ROLE(Sets.newHashSet()),
    USER_ROLE(Sets.newHashSet(PERSON_READ)),
    ADMIN_ROLE(Sets.newHashSet(PERSON_READ, PERSON_WRITE, DOCTOR_READ, DOCTOR_WRITE));


    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }


}
