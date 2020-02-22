package com.imoney.payementmotor.payactor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("api/V1")
@RestController
public class ControllerActor {

    private ServiceActor serviceActor;

    public ControllerActor(ServiceActor serviceActor) {
        this.serviceActor = serviceActor;
    }

    //creer acteur de paiement
    @PostMapping("/actor/create")
    public Actor creer(@Valid @RequestBody Actor actor)
    {
        return serviceActor.creer(actor);
    }
}
