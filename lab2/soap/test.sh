#!/bin/bash

echo "Packaging application..."
mvn clean package
echo
echo
echo "Sending request: request.xml"
echo
curl -v --header "content-type: text/xml" -d @request.xml http://localhost:8080/ws | xmllint --format -
echo
echo
echo "Sending request: requestbiggest.xml"
echo
curl -v --header "content-type: text/xml" -d @requestbiggest.xml http://localhost:8080/ws | xmllint --format -
echo
echo
echo "Sending request: requestcheapest.xml"
echo
curl -v --header "content-type: text/xml" -d @requestcheapest.xml http://localhost:8080/ws | xmllint --format -
echo
echo
echo "Trying to create new order: createorder.xml"
echo
curl -v --header "content-type: text/xml" -d @createorder.xml http://localhost:8080/ws | xmllint --format -
