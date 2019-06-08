package rs.novisad.crimetime.helper;


import java.util.List;

import rs.novisad.crimetime.entity.Cluster;
import rs.novisad.crimetime.global.var;

public class AddressProcessor {
	public static String  parseAdress(String text){
        String ret = null;
        List<Cluster> clusters = var.clusters;
        String[] splited = text.split(" ");
        

        for(Cluster oblast : clusters){
            if(text.toLowerCase().contains(oblast.getName())){
                ret = oblast.getName();
            }
        }

        for(String str : splited){
            for(Cluster cls : clusters){
                if(str.toLowerCase().startsWith(cls.getKeyword())){
                    ret = cls.getName();
                    break;
                }
            }
        }

        if(ret == null){
            if(text.toUpperCase().contains("ULIC")){
                String ulica = text.toUpperCase().split("ULIC")[1];


                String[] splitedUlica = ulica.split(" ");
                StringBuilder sbUlica = new StringBuilder();

                for(int i = 1; i < 5;i++){
                    sbUlica.append(splitedUlica[i]);
                    sbUlica.append(" ");
                }
                System.out.println(sbUlica.toString() + "--ULICA");
                for(Cluster c : clusters){
                    for(String adresa : c.getAddresses()){
                        if(sbUlica.toString().startsWith(adresa)){
                            ret = adresa;
                        }
                    }
                }

            }if(text.toUpperCase().contains("BULEV")){
                String bulevar = text.toUpperCase().split("BULEV")[1];

                String[] spBulevar = bulevar.split(" ");
                StringBuilder sbBulevar = new StringBuilder();
                sbBulevar.append("BULEVAR");
                for(int i = 1; i < 5;i++){
                    sbBulevar.append(" ");
                    sbBulevar.append(spBulevar[i]);
                }


                for(Cluster c : clusters){
                    for(String adresa : c.getAddresses()){
                        if(sbBulevar.toString().startsWith(adresa)){
                            ret = adresa;
                        }
                    }
                }


            }
        }

        return ret;

    }

}
