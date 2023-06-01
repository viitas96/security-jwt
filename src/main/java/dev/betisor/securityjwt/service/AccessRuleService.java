package dev.betisor.securityjwt.service;

import dev.betisor.securityjwt.dto.AccessRuleDTO;
import dev.betisor.securityjwt.entity.AccessRule;
import dev.betisor.securityjwt.entity.Path;
import dev.betisor.securityjwt.repository.AccessRuleDAO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccessRuleService {

    private final PathService pathService;

    private final AccessRuleDAO accessRuleDAO;

    private final ModelMapper modelMapper;

    public AccessRuleDTO save(AccessRuleDTO accessRuleDTO) {
        AccessRule accessRule = modelMapper.map(accessRuleDTO, AccessRule.class);
        return modelMapper.map(accessRuleDAO.save(accessRule), AccessRuleDTO.class);
    }

    public AccessRuleDTO getById(long id) {
        return modelMapper.map(accessRuleDAO.findById(id).orElseThrow(null), AccessRuleDTO.class);
    }

    public void addRuleToPath(long ruleId, long pathId) {
        Path path = pathService.getPath(pathId);
        AccessRule accessRule = accessRuleDAO.findById(ruleId).orElseThrow(null);
        path.getAccessRules().add(accessRule);
        accessRule.getPaths().add(path);
        accessRuleDAO.save(accessRule);
    }

}
