package com.back.Crystal.Service;

import com.back.Crystal.Abstracts.AService;
import com.back.Crystal.Abstracts.Interface.Repository.IUserRepository;
import com.back.Crystal.DTO.GroupCreateDTO;
import com.back.Crystal.DTO.RoleCreateDTO;
import com.back.Crystal.DTO.UserCreateDTO;
import com.back.Crystal.Model.Entity.Group;
import com.back.Crystal.Model.Entity.Role;
import com.back.Crystal.Model.Entity.User;
import com.back.Crystal.Model.Help.Result;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class UserService extends AService<User, UserCreateDTO> {

    private final AService<Role, RoleCreateDTO> RoleRepository;
    private final AService<Group, GroupCreateDTO> GroupRepository;

    public UserService(IUserRepository Repository,
                       AService<Role, RoleCreateDTO> RoleRepository,
                       AService<Group, GroupCreateDTO> GroupRepository) {

        super(Repository);
        this.RoleRepository = RoleRepository;
        this.GroupRepository = GroupRepository;
    }

    protected Set<Group> GetGroups(ArrayList<Long> groupsID)
    {
        Set<Group> groups = new HashSet<>();

        for(Long id : groupsID)
        {
            Result<Group> group = GroupRepository.GetByID(id);
            if(!group.IsError())
            {
                groups.add(group.getModel());
            }
        }

        return groups;
    }

    @Override
    @Nullable
    protected User convertToModel(UserCreateDTO userCreateDTO) {
        Result<Role> role = RoleRepository.GetByID(userCreateDTO.getIDRole());

        return new User(userCreateDTO.getLogin(), userCreateDTO.getPassword(), role.getModel());
    }

    @Override
    protected boolean existsWithSameUniqueFields(User userCreateDTO) {
        User user = Repository.findAll()
                .stream()
                .filter(element -> element.getLogin().equals(userCreateDTO.getLogin()) && !Objects.equals(element.getID(), userCreateDTO.getID()))
                .findFirst()
                .orElse(null);

        if(user != null)
        {
            return true;
        }

        return false;
    }

    @Override
    protected void updateModel(User user, User modelForUpdate) {
        user.setLogin(modelForUpdate.getLogin());
        user.setPassword(modelForUpdate.getPassword());
        user.setRole(modelForUpdate.getRole());
    }

    @Override
    protected boolean checkValidData(User model) {
        if(model.getLogin() != null && !model.getLogin().isBlank())
        {
            return true;
        }

        return false;
    }
}
