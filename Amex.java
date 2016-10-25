/**
 * Created by Roshni Velluva Puthanidam on 21/10/16.
 */

public class Amex extends CreditCard {

    @Override
    public int cardVerify(String f1,String f2,String f3,String f4,String f5,int l1, int l2, int l3, int l4, int l5) {
        String firstDigit = f1;
        String decondDigit = f2;
        String thirdDigit = f3;
        String fourthDigit = f4;
        String fifthDigit = f5;
        int length = l1 + l2 + l3 + l4 + l5;
        if (firstDigit.matches("[0-9].*$") && decondDigit.matches("[0-9].*$") && thirdDigit.matches("[0-9].*$") && fourthDigit.matches("[0-9].*$")) {
            if (firstDigit.charAt(0) == '3') {
                if (firstDigit.charAt(1) == '4' || firstDigit.charAt(1) == '7') {
                    if (length == 15) {
                        return 1;

                    } else {

                        return 0;
                    }
                } else {

                    return 0;
                }
            } else {
                return 0;
            }
        }
        else{
            return 0;
        }
    }

}
