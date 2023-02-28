
For task 1 implementation, I have used the Spring Framework and the io.jsonwebtoken library to parse the JWT token and extract its claims.
The getValuesFromJwt method is mapped to the /api/values endpoint and takes the JWT token as an authorization header. It first extracts the token from the header and removes the "Bearer " prefix.
Then, it parses the JWT token and gets the claims (i.e., payload) using the provided secret key. It extracts the username and email values from the claims and formats the expiration time using the DateTimeFormatter class.
Finally, it returns a string that contains the extracted values and the formatted expiration time.
You can use this API by generating a JWT token with the desired values and expiration time, and passing it as a Bearer token in the Authorization header of a GET request to the /api/values endpoint.

