package lt.karolis.demo.TaskManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feat")
public class FeatureController {

    @Autowired
    FeatureService featureService;

    @GetMapping
    public String getFeatures() {
        String str = featureService.getFeatures();

        return str;
    }
}
