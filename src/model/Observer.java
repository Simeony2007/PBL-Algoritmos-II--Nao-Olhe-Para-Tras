package src.model;

import src.model.service.logic.Orfeu;

public interface Observer {

    public void modelUpdated(Orfeu orfeu);
}
