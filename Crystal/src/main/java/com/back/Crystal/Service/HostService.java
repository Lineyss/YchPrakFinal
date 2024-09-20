package com.back.Crystal.Service;

import com.back.Crystal.Abstracts.AService;
import com.back.Crystal.Abstracts.Interface.Repository.IGroupRepository;
import com.back.Crystal.Abstracts.Interface.Repository.IHostRepository;
import com.back.Crystal.DTO.GroupCreateDTO;
import com.back.Crystal.DTO.HostCreateDTO;
import com.back.Crystal.Model.Entity.Group;
import com.back.Crystal.Model.Entity.Host;
import com.back.Crystal.Model.Help.Result;
import org.springframework.stereotype.Service;

@Service
public class HostService extends AService<Host, HostCreateDTO> {

    private final AService<Group, GroupCreateDTO> GroupService;

    public HostService(IHostRepository Repository,
                       AService<Group, GroupCreateDTO> GroupService) {
        super(Repository);

        this.GroupService = GroupService;
    }

    @Override
    protected Host convertToModel(HostCreateDTO hostCreateDTO) {
        Result<Group> group = GroupService.GetByID(hostCreateDTO.getIDGroup());
        return new Host(hostCreateDTO.getHostname(), group.getModel());
    }

    @Override
    protected boolean existsWithSameUniqueFields(Host hostCreateDTO) {
        Host host = Repository.findAll()
                .stream()
                .filter(element -> element.getHostname().equals(hostCreateDTO.getHostname()))
                .findFirst()
                .orElse(null);

        return host != null;
    }

    @Override
    protected void updateModel(Host host, Host hostCreateDTO) {
        host.setHostname(hostCreateDTO.getHostname());
        host.setGroup(hostCreateDTO.getGroup());
    }

    @Override
    protected boolean checkValidData(Host model) {
        if(model.getHostname().isBlank() || model.getHostname() == null)
        {
            return false;
        }

        return true;
    }
}
