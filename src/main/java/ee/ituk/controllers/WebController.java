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

            if (!responseModel.getCheckedDay1()) {
                responseModel.setPackageDay1("none");
            }

            if (!responseModel.getCheckedDay2()) {
                responseModel.setPackageDay2("none");
            }

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

    @CrossOrigin
    @GetMapping("/count")
    @ResponseBody
    public String count() {
        ObjectNode root = factory.objectNode();
        ObjectNode data = factory.objectNode();
        try {

            for (Integer p = 1; p < 4; p++) {
                data.put("d1p" + p, responseRepo.findByPackageDay1(p.toString()).size());
            }
            for (Integer p = 1; p < 4; p++) {
                data.put("d2p" + p, responseRepo.findByPackageDay2(p.toString()).size());
            }

            root.put("status", "OK");
            root.putPOJO("data", data);
            return root.toString();
        } catch (Exception e) {
            e.printStackTrace();
            root.put("status", "NOT OK");
            root.put("error", e.getClass().getSimpleName());
            return root.toString();
        }
    }
}
