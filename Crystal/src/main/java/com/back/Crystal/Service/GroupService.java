package com.back.Crystal.Service;

import com.back.Crystal.Abstracts.AService;
import com.back.Crystal.Abstracts.Interface.Repository.IGroupRepository;
import com.back.Crystal.DTO.GroupCreateDTO;
import com.back.Crystal.Model.Entity.Group;
import org.springframework.stereotype.Service;

@Service
public class GroupService extends AService<Group, GroupCreateDTO> {

    public GroupService(IGroupRepository Repository) {
        super(Repository);
    }

    @Override
    protected Group convertToModel(GroupCreateDTO groupCreateDTO) {
        return new Group(groupCreateDTO.getTitle());
    }

    @Override
    protected boolean existsWithSameUniqueFields(Group groupCreateDTO) {
        Group group = Repository.findAll()
                .stream()
                .filter(element -> element.getTitle().equals(groupCreateDTO.getTitle()) && !element.getID().equals(groupCreateDTO.getID()))
                .findFirst()
                .orElse(null);

        if(group == null)
        {
            return false;
        }

        return true;
    }

    @Override
    protected void updateModel(Group group, Group groupCreateDTO) {
        group.setTitle(groupCreateDTO.getTitle());
    }

    @Override
    protected boolean checkValidData(Group model) {
        if(model.getTitle().isBlank() || model.getTitle() == null)
        {
            return false;
        }

        return true;
    }
}
