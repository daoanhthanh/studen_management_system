package hanu.edu.ems.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CRUDController<ModelType, IdType, CreateDTOType, UpdateDTOType> {
    ModelType create(CreateDTOType data);

    List<ModelType> findAll();

    Page<ModelType> findAll(Pageable pageable);

    ModelType getByID(IdType id);

    ModelType updateByID(IdType id, UpdateDTOType dto);

    void deleteByID(IdType id);
}
