package eu.nets.portal.training.service;

import eu.nets.portal.training.dto.PopulationRecord;
import java.util.ArrayList;

public interface PopulationChangeRecordSvc {

    public String getWelcomeMessage();
    public ArrayList<PopulationRecord> getAllPopulationChangeList();
}
