
Readme for location suggestions

==================================

This work is uploaded to the following public github repository: https://github.com/souvikseal/location-suggest.git.

The Data is provided by http://download.geonames.org/export/dump/. A condensed version of the data for Canadian and US cities are captured in the Cities.csv file.

Minimum System Requirement:
    1. Java version: 8
    2. Maven version: Apache Maven 3.5.2

How to run:
	1. clone the repo to your workspace from the above github location git clone https://github.com/souvikseal/location-suggest.git.
	2. go to the workspace and navigate to the project location-suggest.
	3. build the project; e.g. with maven you can use, mvn clean install.
	4. the above file will create a jar file in the target location-suggest-1.0.0-SNAPSHOT.jar.
	5. run the jar file with jave; e.g. java -jar location-suggest-1.0.0-SNAPSHOT.jar.
	6. the application will run at localhost:8080. 
	7. go to the web browser and execute the API; e.g. http://localhost:8080/suggest?q=London&latitude=42.98&longitude=-81.23.
	8. if user enters correct inputs a list of suggested locations in JSON will be displayed; for incorrect inputs) say ddd for latitude an error message will be shown.


Input Data

--------------------------------------------------
Cities.csv        : features the city information

Fields            Description
ID	              : unique row id, int
City              : name of the city, String	
Latitude          : latitude of the location, double	
Longitude         : longitude of the location, double	
Country	          : name of the country - CA/US, String
State             : province code/ state name, String


Canadian Province Codes:

1                 : Alberta (AL)
2                 : British Columbia (BC) 
3                 : Manitoba (MN) 
4                 : New Brunswick (NB)
5	                : Newfoundland and Labrador (NL)
7	                : Nova Scotia (NS)
8                 : Ontario (ON)
9	                : Prince Edward Island (PE) 
10                :	Quebec (QC) 
11                : Saskatchewan (SK)
12                : Yukon (YT)
13                :	Northwest Territories (NT) 
14                : Nunavut (NV) 

Scoring Algorithm:
	1. The score of a suggested location ranges from 0 to 1, with an incremental scale of 0.1.
	2. If only query for the location name is provided, all the locations that start with the input name are qualified; the score is computed as rounded-up (# characters of the input location/# characters of the matched location); e.g. http://localhost:8080/suggest?q=Londo will give the score of 0.9 to the city London, ON, CA.
	3. If only one of the latitude and longitude params is given, it is ignored and the locations are suggested on input name only.
	4. If all the query parameters are present, a 0.5 weight is given to the name matching as done in (2), and the other 0.5 weight to distance matching.
	5. The distance between the input location (lat1,lon1) and matched location from the repo (lat2, lon2) is computed. If the distance is more than 200 mi, the distance matching score will be 0; otherwise we use this formula: distance matching score = (1 - calculated distance / 200) * 0.5; e.g. a place 40 miles away from the matched location will get a score of 0.4.
	6. The query matching and distance matching scores are now added to provide the final score.

Possible Improvements:
	1. We can include country, population , etc. in the query parameters to narrow down our search further. The scoring algorithm should adjust the weights assigned to each such category; e.g. highly populated cities may score better than the less dense cities. 
	2. At present, the application outputs only Canadian and US cities; other geographic forms, such as counties, landmarks, water areas, etc. can also be returned as suggestions.

API Design, Billing, and Scalability:
	1. This API can be available to 2 user groups, 1. Individual user (B2C), and 2. Corporations (B2B).
	2. The billing takes into account of the #of active users and the consumption rate. The corporate user can be charged by the #of API hits/min. E.g. a contracted user can execute 100 calls per minute; if the load goes beyond that, the API could throw an exception that wraps HTTP error code 429 (Too Many Requests) and ask the user to try later.
	3. The user will be granted access to the API through an authentication token based security such as OAuth; A consumer key is used at the organization level and a token key at the user level.
  4. A load balancer could be used to serve the # of allowed user hits, especially during the peak-time. The application can use caching mechanisms to improve the performance on repeated queries. 
  5. The location data may be updated periodically; we might think of an event based queuing system that can listen to the changes in the location data and update the internal database accordingly. To make the application more fault-tolerant, a good level of data replication is needed.


Data Source:
http://www.geonames.org/data-sources.html

