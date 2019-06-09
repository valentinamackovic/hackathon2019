package rs.novisad.crimetime.helper;


import java.util.List;

import rs.novisad.crimetime.entity.Cluster;
import rs.novisad.crimetime.global.var;

public class AddressProcessor {
	public static String  parseAdress(String text){
        String ret = "";
        List<Cluster> clusters = var.clusters;
        String[] splited = text.split(" ");
        
//
//        for(Cluster oblast : clusters){
//            if(text.toLowerCase().contains(oblast.getName())){
//                ret = oblast.getName();
//            }
//        }

        for(String str : splited){
            for(Cluster cls : var.clusters){
                if(str.toLowerCase().startsWith(cls.getKeyword())){                	
                    return cls.getName();
                }
            }
        }

        if(ret.equals("")){
            if(text.toUpperCase().contains("ULIC")){
                String ulica = text.toUpperCase().split("ULIC")[1];
                

                String[] splitedUlica = ulica.split(" ");
                StringBuilder sbUlica = new StringBuilder();
                
                if(splitedUlica.length > 0) {
                	for(int i = 1; i < splitedUlica.length ;i++){
                		
                			sbUlica.append(splitedUlica[i]);
                            sbUlica.append(" ");
                		
                        
                    }
         
                    for(Cluster c : clusters){
                        for(String adresa : c.getAddresses()){
                            if(sbUlica.toString().toLowerCase().startsWith(adresa)){
                            	return c.getName();
                            }
                        }
                    }
                }
                
                

            }if(text.toUpperCase().contains("BULEV")){
                String bulevar = text.toUpperCase().split("BULEV")[1];

                String[] spBulevar = bulevar.split(" ");
                
                if(spBulevar.length > 0) {
                	 StringBuilder sbBulevar = new StringBuilder();
                     sbBulevar.append("BULEVAR");
                     for(int i = 1; i < spBulevar.length ;i++){
                    	 if(spBulevar[i] != null) {
                    		 sbBulevar.append(" ");
                             sbBulevar.append(spBulevar[i]); 
                    	 }
                         
                     }


                     for(Cluster c : clusters){
                         for(String adresa : c.getAddresses()){
                             if(sbBulevar.toString().toLowerCase().startsWith(adresa)){
                                 return c.getName();
                             }
                         }
                     }
                }
               


            }
        }

        return ret;

    }

}
