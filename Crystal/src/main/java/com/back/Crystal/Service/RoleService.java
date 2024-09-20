package com.back.Crystal.Service;

import com.back.Crystal.Abstracts.AService;
import com.back.Crystal.Abstracts.Interface.Repository.IRoleRepository;
import com.back.Crystal.DTO.RoleCreateDTO;
import com.back.Crystal.Model.Entity.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AService<Role, RoleCreateDTO> {

    public RoleService(IRoleRepository Repository) {
        super(Repository);
    }

    @Override
    protected Role convertToModel(RoleCreateDTO roleCreateDTO) {
        return new Role(roleCreateDTO.getTitle());
    }

    @Override
    protected boolean existsWithSameUniqueFields(Role roleCreateDTO) {
        Role role = Repository.findAll()
                .stream()
                .filter(element-> element.getTitle().equals(roleCreateDTO.getTitle()) && !element.getID().equals(roleCreateDTO.getID()))
                .findFirst()
                .orElse(null);

        if(role == null)
        {
            return false;
        }

        return true;
    }

    @Override
    protected void updateModel(Role role, Role modelForUpdate) {
        role.setTitle(modelForUpdate.getTitle());
    }

    @Override
    protected boolean checkValidData(Role model) {
        if(model.getTitle() == null || model.getTitle().isBlank())
        {
            return false;
        }

        return true;
    }
}
