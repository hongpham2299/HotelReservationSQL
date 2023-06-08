package hotelReservations.services;

import hotelReservations.daos.FrontDeskAgentsDAO;
import hotelReservations.daos.GuestsDAO;
import hotelReservations.models.FrontDeskAgents;
import hotelReservations.models.Guests;

public class FrontDeskAgentService {

    private FrontDeskAgentsDAO frontDeskAgentsDAO;

    public void getAll(){
        frontDeskAgentsDAO = FrontDeskAgentsDAO.getInstance();
        frontDeskAgentsDAO.getAll();
    }

    public void getByID(FrontDeskAgents frontDeskAgents){
        frontDeskAgentsDAO = FrontDeskAgentsDAO.getInstance();
        frontDeskAgentsDAO.getByID(frontDeskAgents);
    }

    public void create(FrontDeskAgents frontDeskAgents){
        frontDeskAgentsDAO = FrontDeskAgentsDAO.getInstance();
        frontDeskAgentsDAO.create(frontDeskAgents);
    }

    public void update(FrontDeskAgents frontDeskAgents){
        frontDeskAgentsDAO = FrontDeskAgentsDAO.getInstance();
        frontDeskAgentsDAO.update(frontDeskAgents);
    }
}
