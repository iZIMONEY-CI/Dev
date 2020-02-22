package com.imoney.payementmotor.motors;

import java.util.*;
import java.net.URLEncoder;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.math.BigInteger;

public class visamastercard {

    // ****************
    // This is secret for encoding the SHA256 hash
    // This secret will vary from merchant to merchant
    static final String SECURE_SECRET = "2B330777D8F46706A3A654D68CB4532E";

    // This is an array for creating hex chars
    static final char[] HEX_TABLE = new char[] {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

//  ----------------------------------------------------------------------------

    String hashKeys = new String();
    String hashValues = new String();


    /**
     * This method is for sorting the fields and creating a SHA256 secure hash.
     *
     * @param fields is a map of all the incoming hey-value pairs from the VPC
     * @param buf is the hash being returned for comparison to the incoming hash
     */
    void appendQueryFields(StringBuffer buf, Map fields) {

        // create a list
        List fieldNames = new ArrayList(fields.keySet());
        Iterator itr = fieldNames.iterator();

        // move through the list and create a series of URL key/value pairs
        while (itr.hasNext()) {
            String fieldName = (String)itr.next();
            String fieldValue = (String)fields.get(fieldName);

            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                // append the URL parameters
                buf.append(URLEncoder.encode(fieldName));
                buf.append('=');
                buf.append(URLEncoder.encode(fieldValue));
            }

            // add a '&' to the end if we have more fields coming.
            if (itr.hasNext()) {
                buf.append('&');
            }
        }

    }


    String SHAhashAllFields(Map fields) {

        hashKeys = "";
        hashValues = "";

        // create a list and sort it
        List fieldNames = new ArrayList(fields.keySet());
        Collections.sort(fieldNames);

        // create a buffer for the SHA256 input
        StringBuffer buf = new StringBuffer();

        // iterate through the list and add the remaining field values
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) fields.get(fieldName);
            hashKeys += fieldName + ", ";
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                buf.append(fieldName + "=" + fieldValue);
                if (itr.hasNext()) {
                    buf.append('&');
                }
            }
        }
        byte[] mac = null;
        try {
            byte []  b = fromHexString(SECURE_SECRET, 0, SECURE_SECRET.length());
            SecretKey key = new SecretKeySpec(b, "HmacSHA256");
            Mac m = Mac.getInstance("HmacSHA256");
            m.init(key);

            m.update(buf.toString().getBytes("ISO-8859-1"));
            mac = m.doFinal();
        } catch(Exception e) {

        }
        String hashValue = hex(mac);
        return hashValue;

    } // end hashAllFields()


    public static byte[] fromHexString(String s, int offset, int length)
    {
        if ((length%2) != 0)
            return null;
        byte[] byteArray = new byte[length/2];
        int j = 0;
        int end = offset+length;
        for (int i = offset; i < end; i += 2)
        {
            int high_nibble = Character.digit(s.charAt(i), 16);
            int low_nibble = Character.digit(s.charAt(i+1), 16);
            if (high_nibble == -1 || low_nibble == -1)
            {
                // illegal format
                return null;
            }
            byteArray[j++] = (byte)(((high_nibble << 4) & 0xf0) | (low_nibble & 0x0f));
        }
        return byteArray;
    }
//  ----------------------------------------------------------------------------

    /**
     * Returns Hex output of byte array
     */
    static String hex(byte[] input) {
        // create a StringBuffer 2x the size of the hash array
        StringBuffer sb = new StringBuffer(input.length * 2);

        // retrieve the byte array data, convert it to hex
        // and add it to the StringBuffer
        for (int i = 0; i < input.length; i++) {
            sb.append(HEX_TABLE[(input[i] >> 4) & 0xf]);
            sb.append(HEX_TABLE[input[i] & 0xf]);
        }
        return sb.toString();
    }


//  ----------------------------------------------------------------------------

    public String PutVisaTrait(Map<String,String> request,Map<String,String> localrequest,String vpcURL)
    {



        // *******************************************
        // START OF MAIN PROGRAM
        // *******************************************

        // The Page does a transaction using the Virtual Payment Client

        // retrieve all the parameters into a hash map
        Map fields = new HashMap();
       /* Enumeration e = request.getParameterNames();

        while (e.hasMoreElements()) {
            String fieldName = (String) e.nextElement();
            String fieldValue = request.getParameter(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }*/
        Set<Map.Entry<String,String>> st=request.entrySet();
        for (Map.Entry<String,String> me:st)
        {
            if ((me.getValue() != null) && (me.getValue().length() > 0))
          {
                fields.put(me.getKey(), me.getValue());
            }
        }

        // Create SHA256 secure hash and insert it into the hash map if it was created
        // created. Remember if SECURE_SECRET = "" it will not be created
        if (SECURE_SECRET != null && SECURE_SECRET.length() > 0) {
            String secureHash = SHAhashAllFields(localrequest);
            fields.put("vpc_SecureHash", secureHash);
            fields.put("vpc_SecureHashType", "SHA256");
        }

        // Create a redirection URL
        StringBuffer buf = new StringBuffer();
        buf.append(vpcURL).append('?');
        appendQueryFields(buf, fields);
        String query = buf.toString();

        // Redirect to Virtual PaymentClient
        return query;
    }
}
