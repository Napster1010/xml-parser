package http;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dto.LoginBody;
import dto.ParsedXmlData;
import dto.Read;
import dto.ReadMasterKW;
import dto.ReadMasterPF;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequestsHandler {
	
	private static OkHttpClient httpClient;	
	private static final String userAgentHeader = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";
	private static final String loginApi = "https://ngbtest.mpwin.co.in/mppkvvcl/nextgenbilling/backend/api/v1/authentication/login";
	private static final String consumerMeterMappingApi = "https://ngbtest.mpwin.co.in/mppkvvcl/nextgenbilling/backend/api/v1/consumer/meter/mapping/identifier/$meterIdentifier$/status/$status$";
	private static final String readMasterApi = "https://ngbtest.mpwin.co.in/mppkvvcl/nextgenbilling/backend/api/v1/consumer/meter/read";
	private static final SimpleDateFormat oldDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	private static final SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void initializeHttpRequestHandler() {
		httpClient = new OkHttpClient();
	}
		
	public static void sendParsedDataToServer(ParsedXmlData parsedXmlData) throws IOException, ParseException{
		
		//Login user and get the auth token
		final String authToken = loginUser();
		
		//Get the consumer number corresponding to that particular meter
		final String meterIdentifier = "AEW".concat(parsedXmlData.getMeterNumber());
		final String consumerNumber = getConsumerNumber(authToken, meterIdentifier);
		
		//Finally send the read master data to server
		final String readMasterResponse = sendReadMasterRequest(authToken, consumerNumber, parsedXmlData);
	}
	
	private static String sendReadMasterRequest(String authToken, String consumerNumber, ParsedXmlData parsedXmlData) throws IOException, ParseException{
		final String meterIdentifier = "AEW".concat(parsedXmlData.getMeterNumber());
		ReadMasterKW readMasterKW = new ReadMasterKW(new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("1"));
		ReadMasterPF readMasterPf = new ReadMasterPF(new BigDecimal("0"), new BigDecimal("1"));
		Read read = new Read(consumerNumber, meterIdentifier, newDateFormat.format(oldDateFormat.parse("03-02-2020")),
				"NORMAL", "WORKING", "NR", "PMR_FILE", new BigDecimal("1"), new BigDecimal("0"), new BigDecimal("1"), 
				new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), false, readMasterKW, readMasterPf);
		
		String readJson = new Gson().toJson(read);
		RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), readJson);
				
		Request request = new Request.Builder()
									.url(readMasterApi)
									.addHeader("Authorization", authToken)
									.post(requestBody)
									.build();
		
		Call call = httpClient.newCall(request);
		Response response = call.execute();
		String responseJson = response.body().string();
		System.out.println(responseJson);
		return responseJson;
	}
	
	private static String getConsumerNumber(String authToken, String meterIdentifier) throws IOException{
		String url = buildConsumerMeterMappingApiUrl(meterIdentifier);
		Request request = new Request.Builder()
									.url(url)
									.addHeader("Authorization", authToken)
									.build();
		
		Call call = httpClient.newCall(request);
		Response response = call.execute();
		
		JsonArray responseJsonArray = new Gson().fromJson(response.body().string(), JsonArray.class);
		JsonObject consumerMeterMappingJson = responseJsonArray.get(0).getAsJsonObject();		
		return consumerMeterMappingJson.get("consumerNo").getAsString();		 		
	}
	
	private static String buildConsumerMeterMappingApiUrl(String meterIdentifier) {
		String url = consumerMeterMappingApi.replace("$meterIdentifier$", meterIdentifier).replace("$status$", "ACTIVE");
		return url;
	}
		
	
	private static String loginUser() throws IOException{
		LoginBody loginBody = new LoginBody("ngb_jwd", "ngb.test");
		String loginBodyJson = new Gson().toJson(loginBody);

		RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), loginBodyJson);
				
		Request request = new Request.Builder()
									.url(loginApi)
									.post(requestBody)
									.addHeader("User-Agent", userAgentHeader)
									.build();
		
		Call call = httpClient.newCall(request);
		Response response = call.execute();
		return response.headers().get("authorization");
	}
	
}
