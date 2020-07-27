package ru.sap.shop_common.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.sap.database.model.Picture;
import ru.sap.database.model.Product;

import ru.sap.database.repo.ProductRepo;
import ru.sap.shop_common.dto.ProductDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;
    private ModelMapper modelMapper;
//    @Value("${imgDir}")
//    private String directory;
    
    public ProductServiceImpl(ProductRepo productRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void save(ProductDTO productDTO) {
//        String rootPath = new File("").getAbsolutePath();
//        String directoryPath = rootPath + directory + productDTO.getName();
//        String fullPath = directory + "/" + productDTO.getFile()[0].getOriginalFilename();
//        String relativePath = "/img/" + productDTO.getName() + "/" + productDTO.getFile()[0].getOriginalFilename();
//        Path picture = Paths.get(fullPath);
//        try {
//            Files.createDirectory(Paths.get(directoryPath));
//            Files.createFile(picture);
//            Files.write(picture, productDTO.getFile()[0].getBytes());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Product product = new Product();
//        modelMapper.addMappings(new PropertyMap<ProductDTO, Product>() {
//            @Override
//            protected void configure() {
//                map().setPicture(relativePath);
//            }
//        });

        Product product = new Product();
        modelMapper.addMappings(new PropertyMap<ProductDTO, Product>() {
            @Override
            protected void configure() {
                List<Picture> pictures;
                if (productDTO.getId() == null) {
                    pictures = new ArrayList<>();
                } else {
                    pictures = productRepo.findById(productDTO.getId()).get().getPictures();
                }
                Picture picture;
                for (MultipartFile file : productDTO.getFile()) {
                    try {
                        picture = new Picture(file.getContentType(), file.getBytes());
                        pictures.add(picture);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                map().setPictures(pictures);
            }
        });
        modelMapper.map(productDTO, product);
        productRepo.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO getProductById(Long id) {
        return new ProductDTO(productRepo.findById(id).get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        return productRepo.findAll().stream()
                                    .map(ProductDTO::new)
                                    .collect(Collectors.toList());
    }

    @Override
    public void deleteProductById(Long id) {
        productRepo.delete(productRepo.findById(id).get());
    }
}
