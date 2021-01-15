package eu.nets.portal.training.rest;

import eu.nets.portal.training.dto.PopulationRecord;
import eu.nets.portal.training.service.PopulationChangeRecordSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/rest")
public class PopulationChangeController {

    @Autowired
    private PopulationChangeRecordSvc populationChangeRecordSvc;

    @RequestMapping(value = "/getWelcomeText")
    public String getWelcomeText(){
        return populationChangeRecordSvc.getWelcomeMessage();
    }

    @RequestMapping(value = "/getAllPopulationChanges")
    public ArrayList<PopulationRecord> getAllPopulationChangeRecords() {
        ArrayList<PopulationRecord> allPopulationChangeList = populationChangeRecordSvc.getAllPopulationChangeList();
        return  allPopulationChangeList;
    }
}
