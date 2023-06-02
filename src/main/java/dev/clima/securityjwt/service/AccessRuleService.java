package dev.clima.securityjwt.service;

import dev.clima.securityjwt.dto.AccessRuleDTO;
import dev.clima.securityjwt.entity.AccessRule;
import dev.clima.securityjwt.entity.Path;
import dev.clima.securityjwt.repository.AccessRuleDAO;
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
