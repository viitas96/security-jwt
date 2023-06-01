package dev.betisor.securityjwt.util;

import dev.betisor.securityjwt.entity.User;
import dev.betisor.securityjwt.repository.UserDAO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserUtil {

    private UserDAO userDAO;

    public boolean getCompanyIdFromUser(long companyId) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDAO.findByEmail(username).orElseThrow(null);

        return companyId == user.getCompany().getId();
    }


}
