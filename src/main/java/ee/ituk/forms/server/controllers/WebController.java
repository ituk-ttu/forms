package ee.ituk.forms.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ee.ituk.forms.server.database.Response;
import ee.ituk.forms.server.database.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
public class WebController {

    @Resource
    private JsonNodeFactory factory;
    @Resource
    private ObjectMapper mapper;
    @Resource
    private ResponseRepository responseRepository;

    @PostMapping("/submit")
    @ResponseBody
    public String submit(@RequestBody String responseString) {
        ObjectNode root = factory.objectNode();
        try {
            Response response = mapper.readValue(responseString, Response.class);

            if (!response.getCheckedDay1()) {
                response.setPackageDay1("none");
            }

            if (!response.getCheckedDay2()) {
                response.setPackageDay2("none");
            }

            responseRepository.save(response);

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
