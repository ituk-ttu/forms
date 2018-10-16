package ee.ituk.forms.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ee.ituk.forms.server.database.ResponseModel;
import ee.ituk.forms.server.database.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class WebController {
    private boolean count;
    private JsonNodeFactory factory;
    private ObjectMapper mapper;

    @Autowired
    ResponseRepository responseRepository;

    public WebController(){
        count = false;
        factory = new JsonNodeFactory(false);
        mapper = new ObjectMapper();
    }

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

            responseRepository.save(responseModel);

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

    @GetMapping("/count")
    @ResponseBody
    public String count() {
        ObjectNode root = factory.objectNode();
        ObjectNode data = factory.objectNode();
        try {

            for (Integer p = 1; p < 4; p++) {
                data.put("d1p" + p, responseRepository.findByPackageDay1(p.toString()).size());
            }
            for (Integer p = 1; p < 4; p++) {
                data.put("d2p" + p, responseRepository.findByPackageDay2(p.toString()).size());
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
