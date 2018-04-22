package com.example.wangmengyun.MongoLab;


public class QueryBuilder {
	
	/**
	 * Specify your database name here
	 * @return
	 */
	public String getDatabaseName() {
		return "mongo_connect";
	}

	/**
	 * Specify your MongoLab API here
	 * @return
	 */
	public String getApiKey() {
		return "l2vM_qRqK1SfqIbQsV9zq4PJVINybEvN";
	}
	
	/**
	 * This constructs the URL that allows you to manage your database, 
	 * collections and documents
	 * @return
	 */
	public String getBaseUrl()
	{
		return "https://api.mongolab.com/api/1/databases/"+getDatabaseName()+"/collections/";
	}
	
	/**
	 * Completes the formating of your URL and adds your API key at the end
	 * @return
	 */
	public String docApiKeyUrl()
	{
		return "?apiKey="+getApiKey();
	}
	
	/**
	 * Get a specified document
	 * @param docid
	 * @return
	 */
	public String docApiKeyUrl(String docid)
	{
		return "/"+docid+"?apiKey="+getApiKey();
	}
	
	/**
	 * Returns the docs101 collection
	 * @return
	 */
	public String documentRequest()
	{
		return "flights";
	}
	
	/**
	 * Builds a complete URL using the methods specified above
	 * @return
	 */
	public String buildContactsSaveURL()
	{
		return getBaseUrl()+documentRequest()+docApiKeyUrl();
	}
	
	/**
	 * This method is identical to the one above. 
	 * @return
	 */
	public String buildContactsGetURL()
	{
		return getBaseUrl()+documentRequest()+docApiKeyUrl();
	}
	
	/**
	 * Get a Mongodb document that corresponds to the given object id
	 * @param doc_id
	 * @return
	 */
	public String buildContactsUpdateURL(String doc_id)
	{
		return getBaseUrl()+documentRequest()+docApiKeyUrl(doc_id);
	}


	public String createArrayList(MyFlight flight)
	{
		return String
				.format("{\"flight_id\": \"%s\", "
								+ "\"Departure\": \"%s\", \"Arrival\": \"%s\", "
								+ "\"Ticket_price\": \"%s\"}",
						flight._id, flight.departure,flight.arrival, flight.ticket_price);
	}

	/**
	 * Update a given contact record
	 * @param flight
	 * @return
	 */
	public String setData(MyFlight flight) {
		return String.format("{ \"$set\" : " 
				+ "{\"flight_id\" : \"%s\", "
				+ "\"Departure\" : \"%s\", "
				+ "\"Arrival\" : \"%s\", "
				+ "\"Ticket_price\" : \"%s\" }" + "}",
				flight.get_id(),
				flight.getDeparture(), flight.getArrival(),
				flight.getTicket_price());
	}
	
}
