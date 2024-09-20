package com.back.Crystal.Abstracts;

import com.back.Crystal.Model.Help.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;
import java.util.List;

public abstract class AService<Model, CreateDTO> {

    protected JpaRepository<Model, Long> Repository;

    public AService(JpaRepository<Model, Long> Repository)
    {
        this.Repository = Repository;
    }

    public Result<ArrayList<Model>> GetAll()
    {
        try
        {
            List<Model> list_models = Repository.findAll();
            ArrayList<Model> models = new ArrayList<>(list_models);
            return Result.Success(models);
        }
        catch (Exception e)
        {
            return Result.Fail(e.getMessage());
        }
    }

    public Result<Model> GetByID(Long ID)
    {
        try
        {
            Model model = Repository.findById(ID)
                    .orElse(null);

            return Result.Success(model);
        }
        catch (Exception e)
        {
            return Result.Fail(e.getMessage());
        }
    }

    public Result<Model> Delete(Long ID)
    {
        try
        {
            Result<Model> modelDTO = GetByID(ID);

            if(!modelDTO.IsError())
            {
                Repository.deleteById(ID);
                return Result.Success(modelDTO.getModel());
            }

            return Result.Fail("Объекта с таким ID не существует");
        }
        catch (Exception e)
        {
            return Result.Fail(e.getMessage());
        }
    }

    public Result<Model> Create(CreateDTO create_model)
    {
        try
        {
            Model model = convertToModel(create_model);

            boolean exist = existsWithSameUniqueFields(model);
            boolean validData = checkValidData(model);

            if(!exist && validData)
            {
                Repository.save(model);
                return Result.Success(model);
            }

            return Result.Fail("Не удалось создать объект");
        }
        catch (Exception e)
        {
            return Result.Fail(e.getMessage());
        }
    }

    public Result<Model> Update(Long ID, CreateDTO create_model)
    {
        try
        {
            Model model = Repository.findById(ID).orElse(null);

            if(model == null)
            {
                return Result.Fail("Объекта с таким ID не существует");
            }

            Model create_modelDTO = convertToModel(create_model);
            updateModel(model, create_modelDTO);

             if(!existsWithSameUniqueFields(model) && checkValidData(model))
            {
                Repository.save(model);
                return Result.Success(model);
            }


            return Result.Fail("Не удалось обновить объект");
        }
        catch (Exception e)
        {
            return Result.Fail(e.getMessage());
        }
    }

    protected abstract Model convertToModel(CreateDTO createDTO);
    protected abstract boolean existsWithSameUniqueFields(Model createDTO);
    protected abstract void updateModel(Model model, Model modelForUpdate);
    protected abstract boolean checkValidData(Model model);
}
