package ru.sap.shop_common.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sap.database.model.Picture;
import ru.sap.shop_common.service.PictureService;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class PictureController {
    private PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("picture")
    public void getPicture(@RequestParam (name = "id") Long id,
                                        HttpServletResponse response) throws IOException {
        Optional<Picture> picture = pictureService.getPictureById(id);


        if (picture.isPresent()) {
            response.setContentType(picture.get().getContentType());
            response.getOutputStream().write(picture.get().getData());
        }
    }
}
