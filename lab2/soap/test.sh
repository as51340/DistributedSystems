#!/bin/bash

echo "Sending request: request.xml"
echo
curl -v --header "content-type: text/xml" -d @request.xml http://localhost:8080/ws -w "\n time: %{time_total}\n"
echo
echo
echo "Sending request: requestbiggest.xml"
echo
curl -v --header "content-type: text/xml" -d @requestbiggest.xml http://localhost:8080/ws -w "\n time: %{time_total}\n"
echo
echo
echo "Sending request: requestcheapest.xml"
echo
curl -v --header "content-type: text/xml" -d @requestcheapest.xml http://localhost:8080/ws -w "\n time: %{time_total}\n"
echo
echo
echo "Trying to create new order: createorder.xml"
echo
curl -v --header "content-type: text/xml" -d @createorder.xml http://localhost:8080/ws -w "\n time: %{time_total}\n"
