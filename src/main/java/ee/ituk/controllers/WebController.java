package ee.ituk.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ee.ituk.database.ResponseModel;
import ee.ituk.database.ResponseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {
    private boolean count;
    private JsonNodeFactory factory;
    private ObjectMapper mapper;

    @Autowired
    ResponseRepo responseRepo;

    public WebController(){
        count = false;
        factory = new JsonNodeFactory(false);
        mapper = new ObjectMapper();
    }

    @CrossOrigin
    @PostMapping("/submit")
    @ResponseBody
    public String submit(@RequestBody String responseString) {
        ObjectNode root = factory.objectNode();
        try {
            ResponseModel responseModel = mapper.readValue(responseString, ResponseModel.class);
            responseRepo.save(responseModel);

            root.put("status", "OK");
            root.put("info", "Response saved");
            return root.toString();
        } catch (Exception e) {
            e.printStackTrace();
            root.put("status", "NOT OK");
            root.put("error", e.getClass().getSimpleName());
            return root.toString();
        }
    }
}
