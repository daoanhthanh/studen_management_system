package hanu.edu.ems.domains.Authority;

import hanu.edu.ems.base.CRUDService;
import hanu.edu.ems.domains.Authority.dto.CreateAuthorityDTO;
import hanu.edu.ems.domains.Authority.entity.Authority;

public interface AuthorityService extends CRUDService<Authority, Long, CreateAuthorityDTO, CreateAuthorityDTO> {
    boolean hasStudentAccess(Long currentUserID, Long studentID);
}
