package com.back.Crystal.Service;

import com.back.Crystal.Abstracts.AService;
import com.back.Crystal.Abstracts.Interface.Repository.ISoftwareRepository;
import com.back.Crystal.DTO.PublisherCreateDTO;
import com.back.Crystal.DTO.SoftwareCreateDTO;
import com.back.Crystal.Model.Entity.Publisher;
import com.back.Crystal.Model.Entity.Software;
import com.back.Crystal.Model.Help.Result;
import org.springframework.stereotype.Service;

@Service
public class SoftwareService extends AService<Software, SoftwareCreateDTO> {

    private final AService<Publisher, PublisherCreateDTO> PublisherService;

    public SoftwareService(ISoftwareRepository Repository,
                           AService<Publisher, PublisherCreateDTO> PublisherService) {
        super(Repository);

        this.PublisherService = PublisherService;
    }

    @Override
    protected Software convertToModel(SoftwareCreateDTO softwareCreateDTO) {
        Result<Publisher> publisher = PublisherService.GetByID(softwareCreateDTO.getIDPublisher());
        return new Software(softwareCreateDTO.getTitle(), softwareCreateDTO.getDescription(), publisher.getModel());
    }

    @Override
    protected boolean existsWithSameUniqueFields(Software softwareCreateDTO) {
        Software software = Repository.findAll()
                .stream()
                .filter(element -> element.getTitle().equals(softwareCreateDTO.getTitle()) && !softwareCreateDTO.getID().equals(softwareCreateDTO.getID()))
                .findFirst()
                .orElse(null);

        return software != null;
    }

    @Override
    protected void updateModel(Software software, Software softwareCreateDTO) {
        software.setTitle(softwareCreateDTO.getTitle());
        software.setPublisher(softwareCreateDTO.getPublisher());
        software.setDescription(softwareCreateDTO.getDescription());
    }

    @Override
    protected boolean checkValidData(Software model) {
        if(model.getTitle().isBlank() || model.getTitle() == null)
        {
            return false;
        }

        return true;
    }
}
