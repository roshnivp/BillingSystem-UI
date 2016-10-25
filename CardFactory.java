/**
 * Created by Roshni Velluva Puthanidam on 22/10/16.
 */
public class CardFactory {
    CreditCard createCard(String type){
        if(type.equalsIgnoreCase("VISA")){
            return  new Visa();
        }
        else if(type.equalsIgnoreCase("MASTER")){
            return  new Master();
        }
        else if(type.equalsIgnoreCase("AMEX")){
            return  new Amex();
        }
        else {
            return  new Discover();
        }
    }
}
