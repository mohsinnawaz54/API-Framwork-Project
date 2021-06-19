package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestData {
	public AddPlace addplacePayLoad(String name, String language, String address) {
		AddPlace p = new AddPlace();
		p.setAddress(address);
		p.setAccuracy(50);
		p.setLanguage(language);
		p.setWebsite("http://google.com");
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		Location l = new Location();
		l.setLat(-38.38);
		l.setLng(33.347);
		p.setLocation(l);
		p.setTypes(myList);
		return p;

	}

	public String deletePlacePayload(String place_id) {
		// TODO Auto-generated method stub
		return "{\\r\\n    \\\"place_id\\\":\\\"" + place_id + "\\\"\\r\\n}\\r\\n";

	}
}
