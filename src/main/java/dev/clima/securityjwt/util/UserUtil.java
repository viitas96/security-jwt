package dev.clima.securityjwt.util;

import dev.clima.securityjwt.entity.User;
import dev.clima.securityjwt.repository.UserDAO;
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
