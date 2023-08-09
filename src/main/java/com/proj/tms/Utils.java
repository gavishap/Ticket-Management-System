package com.proj.tms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {
	public String BASE_URL = "http://localhost:8081/api/";

	public boolean postRequestCreateTicket(String POST_PARAMS_USER, String POST_PARAMS_TICKET, String POST_PARAMS_SEATS,
			String endpoint) throws IOException {
		URL obj = new URL(BASE_URL + endpoint);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		byte[] inputUser = POST_PARAMS_USER.getBytes("utf-8");
		byte[] inputTicket = POST_PARAMS_TICKET.getBytes("utf-8");
		byte[] inputSeats = POST_PARAMS_SEATS.getBytes("utf-8");

		os.write(inputUser, 0, inputUser.length);
		os.write(inputTicket, 0, inputTicket.length);
		os.write(inputSeats, 0, inputSeats.length);

		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_CREATED) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
			return true;
		} else {
			System.out.println("POST request did not work.");
			return false;
		}

	}

	public String sendPOST(String POST_PARAMS, String endpoint) throws IOException {
		URL obj = new URL(BASE_URL + endpoint);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		byte[] input = POST_PARAMS.getBytes("utf-8");
		os.write(input, 0, input.length);
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_CREATED) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
			return response.toString();
		} else {
			System.out.println("POST request did not work.");
			return null;
		}

	}
}
