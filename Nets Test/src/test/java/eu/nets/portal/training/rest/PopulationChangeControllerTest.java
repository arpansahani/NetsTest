package eu.nets.portal.training.rest;

import eu.nets.portal.training.dto.PopulationRecord;
import eu.nets.portal.training.service.PopulationChangeRecordSvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PopulationChangeControllerTest {
    private PopulationChangeController populationChangeController;
    private PopulationChangeRecordSvc populationChangeRecordSvc;


    public PopulationChangeControllerTest() {
        populationChangeRecordSvc = Mockito.mock(PopulationChangeRecordSvc.class);
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testWelcomeMessageFromService() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/getWelcomeText"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().string("Welcome to demo test application implemented by Arpan. " +
                        "This is a welcome message from service"));
    }

    @Test
    public void whenPopulationChangeRecordExists_thenHttp200_andPopulationChangeRecordReturned() throws Exception {
        PopulationRecord testPopulationChangeRecord1 =
                new PopulationRecord("3001 Halden","2018K3","Births",0);
        ArrayList populationChangeList = new ArrayList();
        populationChangeList.add(testPopulationChangeRecord1);
        Mockito.doReturn(populationChangeList)
                .when(populationChangeRecordSvc)
                .getAllPopulationChangeList();
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/getAllPopulationChanges"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
    }
}
