package com.imoney.payementmotor.payactor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceActor {

    @Autowired
    private RepositoryActor repositoryActor;

    public ServiceActor(RepositoryActor repositoryActor) {
        this.repositoryActor = repositoryActor;
    }

    //service creation acteur de payement
    public Actor creer(Actor actor)
    {
        return  repositoryActor.save(actor);
    }

    //get visa actor
    public Actor findByCode(String code)
    {
        return repositoryActor.findByCode(code);
    }
}
