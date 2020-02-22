package com.imoney.payementmotor.payement;

public interface ISercviceTransaction {
    public String PaymentVISAMASTER(EntryObject entryObject,String currency,String reference,String actorCode);
    public String PaymentMTN(String reference,int montant,String numero);
    public String MTNCI(String reference,int montant,String numero);
}
