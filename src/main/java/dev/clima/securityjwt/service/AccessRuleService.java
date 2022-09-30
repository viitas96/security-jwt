package dev.clima.securityjwt.service;

import dev.clima.securityjwt.entity.AccessRule;
import dev.clima.securityjwt.entity.Path;
import dev.clima.securityjwt.repository.AccessRuleDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccessRuleService {

    private final PathService pathService;

    private final AccessRuleDAO accessRuleDAO;

    public void save(AccessRule accessRule) {
        accessRuleDAO.save(accessRule);
    }

    public AccessRule getById(long id) {
        return accessRuleDAO.findById(id).orElseThrow(null);
    }

    public void addRuleToPath(long ruleId, long pathId) {
        Path path = pathService.getPath(pathId);
        AccessRule accessRule = getById(ruleId);
        path.getAccessRules().add(accessRule);
        accessRule.getPaths().add(path);
        accessRuleDAO.save(accessRule);
    }

}
