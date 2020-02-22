package com.imoney.payementmotor.payement;

import com.imoney.payementmotor.motors.mtnci;
import com.imoney.payementmotor.motors.visamastercard;
import com.imoney.payementmotor.payactor.Actor;
import com.imoney.payementmotor.payactor.RepositoryActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceTransaction implements ISercviceTransaction {

    private static String URL_VISAmASTERCARD = "https://migs-mtf.mastercard.com.au/vpcpay";
    private static String URL_RTOUR= "https://localhost:9010/api/V1/retour";
    private static String SECURESECRET= "2B330777D8F46706A3A654D68CB4532E";


    @Autowired
    private RepositoryActor repositoryActor;

    public String PaymentVISAMASTER(EntryObject entryObject,String currency,String reference,String actorCode)
    {
        //récuperation infos acteur a utiliser
        Actor actor=repositoryActor.findByCode(actorCode);
        if(actor==null)
            return "Acteur non identifier";
        //constituer les parametres a passer
        String num="064582153";
        int amount=1500;
        String vpc_OrderInfo="0920";
        String title="TEST 1";
        Map<String,String> fields=new HashMap<String, String>();
        fields.put("vpc_Amount", String.valueOf(amount));
        fields.put("vpc_Version",actor.getVpc_Version());
        fields.put("vpc_OrderInfo",vpc_OrderInfo);
        fields.put("vpc_Command",actor.getVpc_Command());
        fields.put("vpc_AccessCode",actor.getVpc_AccessCode());
        fields.put("vpc_Merchant",actor.getVpc_Merchant());
        fields.put("vpc_MerchTxnRef",actor.getVpc_Merchant());
        fields.put("vpc_currency",currency);
        fields.put("Title",title);
       /* fields.put("imon_id",currency);
        fields.put("imon_action",reference);
        fields.put("imon_activ",reference);
        fields.put("imon_vas",reference);*/
        fields.put("vpc_ReturnURL","localhost:9000/test1/");

        Map<String,String> fieldsfordata=new HashMap<String, String>();

        fieldsfordata.put("Montant", String.valueOf(amount));
        fieldsfordata.put("Version",actor.getVpc_Version());
        fieldsfordata.put("OrderInfo",vpc_OrderInfo);
        fieldsfordata.put("Command",actor.getVpc_Command());
        fieldsfordata.put("AccessCode",actor.getVpc_AccessCode());
        fieldsfordata.put("Merchant",actor.getVpc_Merchant());
        fieldsfordata.put("Reference",num);
        fieldsfordata.put("currency",currency);
        fieldsfordata.put("Title",title);
        fieldsfordata.put("id",currency);
        fieldsfordata.put("action",reference);
        fieldsfordata.put("activ",reference);
        fieldsfordata.put("vas",reference);

        visamastercard visa=new visamastercard();
       String url= visa.PutVisaTrait(fields,fieldsfordata,this.URL_VISAmASTERCARD);
        return url;
    }

    public String PaymentMTN(String reference,int montant,String numero)
    {
        mtnci mtn=new mtnci();
        return mtn.process(reference,montant,numero);
    }

    public String MTNCI(String reference,int montant,String numero){
        mtnci mtn=new mtnci();
        return mtn.MTNCI(reference,montant,numero);
    }

}
