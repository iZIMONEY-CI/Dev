package com.imoney.payementmotor.payement;

import com.imoney.payementmotor.enums.ActeurCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/V1")
public class ControllerTransaction {
    private static String CURRENCY="XOF";
    private static String REFERENCE="01245";
    @Autowired
    private ServiceTransaction serviceTransaction;

    @PostMapping("pay/by/visamaster")
    public String VisaMasterCard(@Valid @RequestBody EntryObject entryObject){
        //acteur de l'operation
        System.out.println(entryObject.getPhonenumber());
         String actor= ActeurCodeEnum.VISAMASTERCARD.url();
         String reference=this.REFERENCE;
         String currency=this.CURRENCY;


        return  serviceTransaction.PaymentVISAMASTER(entryObject,currency,reference,actor);
    }

    @PostMapping("pay/by/mtnci")
    public String mtnci(){
        //acteur de l'operation

        String numero= "22574608008";
        String reference="0022503";
        int montant=200;
        return  serviceTransaction.MTNCI(reference,montant,numero);
    }
}
