package com.ecommerceJava.ecommerce.services;


import com.ecommerceJava.ecommerce.dto.ProductDTO;
import com.ecommerceJava.ecommerce.entities.Product;
import com.ecommerceJava.ecommerce.repositories.ProductRepository;
import com.ecommerceJava.ecommerce.services.exeptions.DatabaseException;
import com.ecommerceJava.ecommerce.services.exeptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(long id) {
        Product product = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("recurso nao encontrado"));
        return new ProductDTO(product); // Lancando a propria execcao do error

    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findALL(Pageable pageable) {
        Page<Product> result = repository.findAll(pageable);
        //return result.stream().map(x -> new ProductDTO(x)).toList();
        return result.map(x -> new ProductDTO(x));


    }


    @Transactional
    public ProductDTO insert (ProductDTO dto) {

        Product entity = new Product();
        copyDtoToEntity(dto,entity); // usando o metodo copyDtoToEntity pro codigo nao ficar repetitivo
//        entity.setName(dto.getName());
//        entity.setDescription(dto.getDescription());
//        entity.setPrice(dto.getPrice());
//        entity.setImgUrl(dto.getImgUrl());
        entity = repository.save(entity);
        return new ProductDTO(entity);



    }


    @Transactional()
    public ProductDTO update (Long id,ProductDTO dto) {
        try {
            Product entity = repository.getReferenceById(id); // pega a referencia do id que eu passar, sem ir no BD
            copyDtoToEntity(dto,entity);
            return new ProductDTO(entity);

        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("recurso nao encontrado");
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS) // so vai executar se o metodo estiver no contexto de outra transacao.
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }

        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

// delete antigo.
//    @Transactional
//    public void  delete (long id) {
//        repository.deleteById(id);
//    }


    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }


}
