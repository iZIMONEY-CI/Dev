package com.imoney.payementmotor.motors;

import com.fasterxml.jackson.databind.JsonNode;
import com.imoney.payementmotor.utilitaires.convertisseur;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;


public class mtnci {

    private static  String url="https://billmap.mtn.ci:8443/WebServices/BillPayment.asmx/ProcessOnlinePayment_V1.4";
    private static String code = "GPAY2";
    private static String password = "Gs2e@2019AgP#";
    private static String metadata = "AC:1558";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String process(String reference,int montant,String numero){

        try{

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
           // headers.add("Authorization",token);

            JSONObject personJsonObject = new JSONObject();
            personJsonObject.put("Code", this.code);
            personJsonObject.put("Password", this.password);
            personJsonObject.put("Reference", reference);
            personJsonObject.put("MetaData", this.metadata);
            personJsonObject.put("Amount", String.valueOf(montant));
            personJsonObject.put("MSISDN", numero);

            System.out.println(personJsonObject);

            HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);

            String personResultAsJsonStr = restTemplate.postForObject(this.url, request, String.class);
/*
            JsonObject jsonObject = new Gson().fromJson(personResultAsJsonStr, JsonObject.class);
            Gson gson = new Gson();
            String json = gson.toJson(jsonObject);*/
            //this.clientTokendest=jsonObject.get("accessToken").toString().replace("\"","");

            return personResultAsJsonStr;
            // System.out.println( json);
           // this.SetOpererationClientatraiter(operation.getId());
        }catch (HttpStatusCodeException e) {

            System.out.println(e.getRawStatusCode());
            System.out.println(e.getStatusCode().getReasonPhrase());
            return e.getStatusCode().getReasonPhrase();

        }
    }

    //MTN CI 16/02/2020
    public String MTNCI1(String reference,int montant,String numero) throws MalformedURLException, ProtocolException, IOException
    {
        //VARIABLES LOCALES
        URL url=new URL(this.url);

        //METHODE POST
        HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                          connection.setRequestMethod("POST");
                          connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

                          //  BODY PARAMETERS
        Map<String,String>params=new HashMap<>();
                          params.put("Code", this.code);
                          params.put("Password", this.password);
                          params.put("Reference", reference);
                          params.put("MetaData", this.metadata);
                          params.put("Amount", String.valueOf(montant));
                          params.put("MSISDN", numero);
        StringBuilder postData=new StringBuilder();

        for (Map.Entry<String,String> param:params.entrySet())
        {
            if(postData.length()!=0){
                postData.append("&");
            }
            postData.append(URLEncoder.encode(param.getKey(),"UTF-8"));
            postData.append("=");
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),"UTF-8"));
        }

        //OUTPUT
        byte[] postDataBytes=postData.toString().getBytes("UTF-8");
                connection.setDoOutput(true);
                try(DataOutputStream writer=new DataOutputStream(connection.getOutputStream())){
                    writer.write(postDataBytes);
                    writer.flush();
                    writer.close();

                    StringBuilder content;

                    try(BufferedReader in=new BufferedReader(new InputStreamReader((connection.getInputStream())))){
                        String line;
                        content=new StringBuilder();
                        while ((line=in.readLine())!=null){
                            content.append(line);
                            content.append(System.lineSeparator());
                        }
                        System.out.println(content.toString());
                    } finally {
                        connection.disconnect();
                    }

                }

                try(BufferedReader in=new BufferedReader(new InputStreamReader(connection.getInputStream()) )){
                    String ligne;
                }


        return "";
    }
    public String MTNCI(String reference,int montant,String numero)
    {
        try{
            URL url=new URL(this.url);

            //  BODY PARAMETERS
            Map<String,String>params=new HashMap<>();
            params.put("Code", this.code);
            params.put("Password", this.password);
            params.put("Reference", reference);
            params.put("MetaData", this.metadata);
            params.put("Amount", String.valueOf(montant));
            params.put("MSISDN", numero);

            StringBuilder postData=new StringBuilder();

            for (Map.Entry<String,String> param:params.entrySet())
            {
                if(postData.length()!=0){
                    postData.append("&");
                }
                postData.append(URLEncoder.encode(param.getKey(),"UTF-8"));
                postData.append("=");
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()),"UTF-8"));
            }
            byte[] postDataBytes=postData.toString().getBytes("UTF-8");
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length",String.valueOf(postDataBytes.length));
            connection.setDoOutput(true);
            connection.getOutputStream().write(postDataBytes);

            Reader in=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));

            StringBuilder sb=new StringBuilder();
            for(int c;(c=in.read())>0;)
            {
                System.out.print((char)c);
                sb.append((char)c);
                String response=sb.toString();
            }

            convertisseur.convertStringToXMLDocument(sb.toString());
            //Use method to convert XML string content to XML Document object
            Document doc = convertisseur.convertStringToXMLDocument(sb.toString());

            //Verify XML document is build correctly
            System.out.println(doc.getElementsByTagName("ResponseCode").item(0).getTextContent());
            System.out.println(doc.getElementsByTagName("ResponseMessage").item(0).getTextContent());
            System.out.println(doc.getElementsByTagName("BillMapTransactionId").item(0).getTextContent());
            System.out.println(doc.getElementsByTagName("EWPTransactionId").item(0).getTextContent());


        return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
}
