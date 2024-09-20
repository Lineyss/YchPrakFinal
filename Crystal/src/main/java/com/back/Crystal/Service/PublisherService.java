package com.back.Crystal.Service;

import com.back.Crystal.Abstracts.AService;
import com.back.Crystal.Abstracts.Interface.Repository.IPublisherRepository;
import com.back.Crystal.DTO.PublisherCreateDTO;
import com.back.Crystal.Model.Entity.Publisher;
import org.springframework.stereotype.Service;

@Service
public class PublisherService extends AService<Publisher, PublisherCreateDTO> {

    public PublisherService(IPublisherRepository Repository) {
        super(Repository);
    }

    @Override
    protected Publisher convertToModel(PublisherCreateDTO publisherCreateDTO) {
        return new Publisher(publisherCreateDTO.getTitle());
    }

    @Override
    protected boolean existsWithSameUniqueFields(Publisher publisherCreateDTO) {
        Publisher publisher = Repository.findAll()
                .stream()
                .filter(element -> element.getTitle().equals(publisherCreateDTO.getTitle()) && !element.getID().equals(publisherCreateDTO.getID()))
                .findFirst()
                .orElse(null);

        return publisher != null;
    }


    @Override
    protected void updateModel(Publisher publisher, Publisher modelForUpdate) {
        publisher.setTitle(modelForUpdate.getTitle());
    }

    @Override
    protected boolean checkValidData(Publisher model) {
        if(model.getTitle().isBlank() || model.getTitle() == null)
        {
            return false;
        }

        return true;
    }
}
