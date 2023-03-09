package edu.cscc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import org.json.*;

/**
 * Find out who's in space JSON library from:
 * https://github.com/stleary/JSON-java
 * 
 * @author Aidan McBride 3/9/23 JSON Project
 *
 */
public class Main {

	public static void main(String[] args) throws UnknownHostException, MalformedURLException {
		final String theURLString = "http://api.open-notify.org/astros.json";

		URL url = new URL(theURLString);
		try {
			URLConnection connection = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder data = new StringBuilder();
			data.append(reader.readLine());

			JSONObject object = new JSONObject(data.toString());
			int number = object.getInt("number");
			JSONArray astronauts = new JSONArray(object.getJSONArray("people"));

			System.out.println(data);
			System.out.println("Ther are " + number + " people in space");

			for (int i = 0; i < astronauts.length(); i++) {
				JSONObject entry = astronauts.getJSONObject(i);
				String craft = entry.getString("craft");
				String name = entry.getString("name");
				System.out.println(name + " is onboard: " + craft);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Connection Error");
			e.printStackTrace();
		}

	}
}
